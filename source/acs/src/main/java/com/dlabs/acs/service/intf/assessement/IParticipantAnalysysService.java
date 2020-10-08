package com.dlabs.acs.service.intf.assessement;

import java.util.List;

import com.dlabs.acs.entity.assessement.ParticipantAnalysys;
import com.dlabs.acs.service.intf.IAbstractParticipantService;

public interface IParticipantAnalysysService  extends IAbstractParticipantService<ParticipantAnalysys,Long >{
	
	public Long countBySearch(String search);
	
	public List<ParticipantAnalysys> getBySearch(String search, int start, int num);
	
	public ParticipantAnalysys getByPartIdAndAnalysysId(Long participantId, Long claId);
}
