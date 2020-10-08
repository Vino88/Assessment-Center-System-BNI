package com.dlabs.acs.service.intf;

import java.io.Serializable;
import java.util.List;

import com.dlabs.acs.service.IService;

public interface IAbstractParticipantService <E, I extends Serializable> extends IService<E, I>{
	public Long countByParticipantId(Long participantId);
	public List<E> getByParticipantId(Long participantId);
	public List<E> getByParticipantId(Long participantId, int start, int num);
	public E getByParticipantIdFirst(Long participantId);
}
