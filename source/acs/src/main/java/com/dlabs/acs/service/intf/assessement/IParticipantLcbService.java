package com.dlabs.acs.service.intf.assessement;

import java.util.List;

import com.dlabs.acs.entity.assessement.ParticipantLcb;
import com.dlabs.acs.service.intf.IAbstractParticipantService;

public interface IParticipantLcbService  extends IAbstractParticipantService<ParticipantLcb,Long >{
	
	public Long countBySearch(String search);
	
	public List<ParticipantLcb> getBySearch(String search, int start, int num);
}
