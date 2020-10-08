package com.dlabs.acs.service.intf.assessement;

import java.util.List;

import com.dlabs.acs.entity.assessement.ParticipantCap;
import com.dlabs.acs.service.intf.IAbstractParticipantService;

public interface IParticipantCapService  extends IAbstractParticipantService<ParticipantCap,Long >{
	
	public Long countBySearch(String search);
	
	public List<ParticipantCap> getBySearch(String search, int start, int num);
	
	public ParticipantCap getByParticipantIdAndCapId(Long participantId, Long capId);
}
