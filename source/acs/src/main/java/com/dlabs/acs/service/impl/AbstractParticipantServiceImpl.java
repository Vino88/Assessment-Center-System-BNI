package com.dlabs.acs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.service.intf.IAbstractParticipantService;
@Transactional(readOnly = true)
public abstract class AbstractParticipantServiceImpl <E, I extends Serializable> extends AbstractServiceImpl<E,I> implements IAbstractParticipantService<E, I>{
	public abstract IAbstractParticipantDao<E, I> getDao();
	@Override
	public Long countByParticipantId(Long participantId) {
		// TODO Auto-generated method stub
		return getDao().countByParticipantId(participantId);
	}

	@Override
	public List<E> getByParticipantId(Long participantId) {
		// TODO Auto-generated method stub
		return getDao().getByParticipantId(participantId);
	}

	@Override
	public List<E> getByParticipantId(Long participantId, int start, int num) {
		// TODO Auto-generated method stub
		return getDao().getByParticipantId(participantId, start, num);
	}

	@Override
	public E getByParticipantIdFirst(Long participantId) {
		// TODO Auto-generated method stub
		return getDao().getByParticipantIdFirst(participantId);
	}

}
