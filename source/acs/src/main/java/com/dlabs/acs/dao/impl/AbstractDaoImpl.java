package com.dlabs.acs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.dlabs.acs.dao.IAbstractDao;


public abstract class AbstractDaoImpl<E, I extends Serializable> implements IAbstractDao<E, I>
{

	protected Class<E> entityClass;

	protected AbstractDaoImpl(Class<E> entityClass)
	{
		this.entityClass = entityClass;
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	@Override
	public E findById(I id)
	{
		return (E) getCurrentSession().get(entityClass, id);
	}

	@Override
	public void saveOrUpdate(E e)
	{
		getCurrentSession().saveOrUpdate(e);
	}

	@Override
	public void save(E e)
	{
		getCurrentSession().save(e);
	}

	@Override
	public void update(E e)
	{
		getCurrentSession().update(e);
	}

	public void save(List<E> listE)
	{
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			for (int i = 0; i < listE.size(); i++)
			{
				session.save(listE.get(i));
				if (i % 20 == 0)
				{
					// flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		}
		finally
		{
			session.close();
		}

	}

	@Override
	public void remove(E e)
	{
		getCurrentSession().delete(e);
		getCurrentSession().flush();
	}

	@Override
	public void remove(I id)
	{
		E e = findById(id);
		getCurrentSession().delete(e);
	}

	@Override
	public void delete(I id)
	{
		Query query = getCurrentSession().createQuery("UPDATE " + entityClass.getName() + " e SET e.commonFields.deleted = true where e.id = :id");
		if (id instanceof Long)
		{
			query.setLong("id", (Long) id);
		}
		else if (id instanceof String)
		{
			query.setString("id", (String) id);
		}
		else
		{
			return;
		}
		query.executeUpdate();
	}

	@Override
	public void delete(E e)
	{
		getCurrentSession().update(e);
	}

	@Override
	public Long count()
	{
		List list = getCurrentSession().createQuery("select count(*) from " + entityClass.getName() + " x").list();
		Long count = (Long) list.get(0);
		return count;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getAll()
	{
		return getCurrentSession().createQuery("from " + entityClass.getName()).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getAll(int start, int num)
	{
		return getCurrentSession().createQuery("from " + entityClass.getName()).setFirstResult(start).setMaxResults(num).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getAllActive()
	{
		return getCurrentSession().createQuery("from " + entityClass.getName() + " e WHERE e.commonFields.deleted = false ORDER BY e.id DESC").list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getAllActive(int start, int num)
	{
		return getCurrentSession().createQuery("from " + entityClass.getName() + " e WHERE e.commonFields.deleted = false ORDER BY e.id DESC").setFirstResult(start).setMaxResults(num).list();
	}
	
}
