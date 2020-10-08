package com.dlabs.acs.service.impl.assessement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.dao.intf.assessement.IParticipantInbasketInboxInbasketQuestionDao;
import com.dlabs.acs.entity.assessement.ParticipantInbasketInboxInbasketQuestion;
import com.dlabs.acs.service.impl.AbstractParticipantServiceImpl;
import com.dlabs.acs.service.intf.assessement.IParticipantInbasketInboxInbasketQuestionService;

@Service
@Transactional(readOnly = true)
public class ParticipantInbasketInboxInbasketQuestionServiceImpl extends AbstractParticipantServiceImpl<ParticipantInbasketInboxInbasketQuestion, Long> implements IParticipantInbasketInboxInbasketQuestionService{
	private Logger log = Logger.getLogger(ParticipantInbasketInboxInbasketQuestionServiceImpl.class);
	
	@Autowired
	private IParticipantInbasketInboxInbasketQuestionDao participantinbasketinboxinbasketquestionDao;

	@Override
	public IAbstractParticipantDao<ParticipantInbasketInboxInbasketQuestion, Long> getDao() {
		// TODO Auto-generated method stub
		return participantinbasketinboxinbasketquestionDao;
	}
	
	public Long countBySearch(String search){
		return participantinbasketinboxinbasketquestionDao.countBySearch(search);
	}
	
	public List<ParticipantInbasketInboxInbasketQuestion> getBySearch(String search, int start, int num){
		return participantinbasketinboxinbasketquestionDao.getBySearch(search, start, num);
	}
	public ParticipantInbasketInboxInbasketQuestion getByPartIdAndIiibId(Long participantId, Long iiibId){
		return participantinbasketinboxinbasketquestionDao.getByPartIdAndIiibId(participantId, iiibId);
	}
	
	public Long countMemoByPartId(Long participantId){
		return participantinbasketinboxinbasketquestionDao.countMemoByPartId(participantId);
	}
}
