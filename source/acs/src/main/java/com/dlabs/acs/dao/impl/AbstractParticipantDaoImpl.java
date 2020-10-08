package com.dlabs.acs.dao.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;

public abstract class AbstractParticipantDaoImpl<E, I extends Serializable> extends AbstractDaoImpl<E, I> implements IAbstractParticipantDao<E,I>{

	protected AbstractParticipantDaoImpl(Class<E> entityClass) {
		super(entityClass);
	}
	
	@Override
	public Long countByParticipantId(Long participantId)
	{
		Session session = getCurrentSession();
		Query query = session.createQuery("select count(*) from " + entityClass.getName() + " x WHERE x.participant.id = :participantId");
		query.setLong("participantId", participantId);
		
		List list = query.list();
		Long count = (Long) list.get(0);
		return count;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getByParticipantId(Long participantId)
	{
		Session session = getCurrentSession();
		Query query = session.createQuery("from " + entityClass.getName() + " x WHERE x.participant.id = :participantId");
		query.setLong("participantId", participantId);
		
		List list = query.list();
		if(list == null)
		{
			return Collections.EMPTY_LIST;
		}
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getByParticipantId(Long participantId, int start, int num)
	{
		Session session = getCurrentSession();
		Query query = session.createQuery("from " + entityClass.getName() + " x WHERE x.participant.id = :participantId");
		query.setLong("participantId", participantId);
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List list = query.list();
		if(list == null)
		{
			return Collections.EMPTY_LIST;
		}
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public E getByParticipantIdFirst(Long participantId)
	{
		Session session = getCurrentSession();
		Query query = session.createQuery("from " + entityClass.getName() + " x WHERE x.participant.id = :participantId");
		query.setLong("participantId", participantId);
		
		query.setMaxResults(1);
		
		List<E> list = query.list();
		if(list == null || list.size() == 0)
		{
			return null;
		}
		return list.get(0);
	}

}
