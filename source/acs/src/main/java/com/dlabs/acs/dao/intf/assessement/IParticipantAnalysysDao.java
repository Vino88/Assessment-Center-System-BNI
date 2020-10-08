package com.dlabs.acs.dao.intf.assessement;

import java.util.List;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.entity.assessement.ParticipantAnalysys;


public interface IParticipantAnalysysDao extends IAbstractParticipantDao <ParticipantAnalysys,Long>{
	
	public Long countBySearch(String search);
	
	public List<ParticipantAnalysys> getBySearch(String search, int start, int num);
	
	public ParticipantAnalysys getByPartIdAndAnalysysId(Long participantId, Long claId);
}
