package com.dlabs.acs.dao.intf.assessement;

import java.util.List;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.entity.assessement.ParticipantLogPre;


public interface IParticipantLogPreDao extends IAbstractParticipantDao <ParticipantLogPre,Long>{
	
	public Long countBySearch(String search);
	
	public List<ParticipantLogPre> getBySearch(String search, int start, int num);
	
	public List<Long> getInvitationSentTimeNullByBatchId(Long batchId);
	
}
