package com.dlabs.acs.dao.impl.factsheet;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.factsheet.IAbstractProfileDao;

public class AbstractProfileDaoImpl <E, I extends Serializable> extends AbstractDaoImpl<E, I> implements IAbstractProfileDao<E,I>{

	protected AbstractProfileDaoImpl(Class<E> entityClass) {
		super(entityClass);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public E getByProfileIdFirst(Long profileId)
	{
		Session session = getCurrentSession();
		Query query = session.createQuery("from " + entityClass.getName() + " x WHERE x.profile.id = :profileId");
		query.setLong("profileId", profileId);
		
		query.setMaxResults(1);
		
		List<E> list = query.list();
		if(list == null || list.size() == 0)
		{
			return null;
		}
		return list.get(0);
	}

}
