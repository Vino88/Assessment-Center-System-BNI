package com.dlabs.acs.service.intf.assessement;

import java.util.List;

import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.service.intf.IAbstractParticipantService;

public interface IParticipantLogDuringService  extends IAbstractParticipantService<ParticipantLogDuring,Long >{
	
	public Long countBySearch(String search);
	
	public List<ParticipantLogDuring> getBySearch(String search, int start, int num);
}
