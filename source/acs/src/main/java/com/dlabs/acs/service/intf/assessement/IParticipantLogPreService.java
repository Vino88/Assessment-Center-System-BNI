package com.dlabs.acs.service.intf.assessement;

import java.util.List;

import com.dlabs.acs.entity.assessement.ParticipantLogPre;
import com.dlabs.acs.service.intf.IAbstractParticipantService;

public interface IParticipantLogPreService  extends IAbstractParticipantService<ParticipantLogPre,Long >{
	
	public Long countBySearch(String search);
	
	public List<ParticipantLogPre> getBySearch(String search, int start, int num);
	
	public List<Long> getInvitationSentTimeNullByBatchId(Long batchId);
}
