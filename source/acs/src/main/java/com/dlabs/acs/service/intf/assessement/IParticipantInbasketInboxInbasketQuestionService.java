package com.dlabs.acs.service.intf.assessement;

import java.util.List;

import com.dlabs.acs.entity.assessement.ParticipantInbasketInboxInbasketQuestion;
import com.dlabs.acs.service.intf.IAbstractParticipantService;

public interface IParticipantInbasketInboxInbasketQuestionService  extends IAbstractParticipantService<ParticipantInbasketInboxInbasketQuestion,Long >{
	
	public Long countBySearch(String search);
	
	public List<ParticipantInbasketInboxInbasketQuestion> getBySearch(String search, int start, int num);
	
	public ParticipantInbasketInboxInbasketQuestion getByPartIdAndIiibId(Long participantId, Long iiibId);
	
	public Long countMemoByPartId(Long participantId);
}
