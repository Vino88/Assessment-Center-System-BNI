package com.dlabs.acs.dao.intf.assessement;

import java.util.List;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.entity.assessement.ParticipantCap;


public interface IParticipantCapDao extends IAbstractParticipantDao <ParticipantCap,Long>{
	
	public Long countBySearch(String search);
	
	public List<ParticipantCap> getBySearch(String search, int start, int num);
	
	public ParticipantCap getByParticipantIdAndCapId(Long participantId, Long capId);
}
