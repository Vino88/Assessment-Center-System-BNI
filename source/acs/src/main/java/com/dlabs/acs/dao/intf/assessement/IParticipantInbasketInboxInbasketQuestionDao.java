package com.dlabs.acs.dao.intf.assessement;

import java.util.List;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.entity.assessement.ParticipantInbasketInboxInbasketQuestion;


public interface IParticipantInbasketInboxInbasketQuestionDao extends IAbstractParticipantDao <ParticipantInbasketInboxInbasketQuestion,Long>{
	
	public Long countBySearch(String search);
	
	public List<ParticipantInbasketInboxInbasketQuestion> getBySearch(String search, int start, int num);
	
	public ParticipantInbasketInboxInbasketQuestion getByPartIdAndIiibId(Long participantId, Long iiibId);
	
	public Long countMemoByPartId(Long participantId);
	
}
