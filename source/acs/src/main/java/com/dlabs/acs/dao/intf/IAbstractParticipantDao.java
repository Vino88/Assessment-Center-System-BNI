package com.dlabs.acs.dao.intf;

import java.io.Serializable;
import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;

public interface IAbstractParticipantDao<E, I extends Serializable> extends IAbstractDao<E,I>  {
	public Long countByParticipantId(Long participantId);
	public List<E> getByParticipantId(Long participantId);
	public List<E> getByParticipantId(Long participantId, int start, int num);
	public E getByParticipantIdFirst(Long participantId);
}
