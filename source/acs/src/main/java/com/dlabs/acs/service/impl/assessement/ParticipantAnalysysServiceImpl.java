package com.dlabs.acs.service.impl.assessement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.dao.intf.assessement.IParticipantAnalysysDao;
import com.dlabs.acs.entity.assessement.ParticipantAnalysys;
import com.dlabs.acs.service.impl.AbstractParticipantServiceImpl;
import com.dlabs.acs.service.intf.assessement.IParticipantAnalysysService;

@Service
@Transactional(readOnly = true)
public class ParticipantAnalysysServiceImpl extends AbstractParticipantServiceImpl<ParticipantAnalysys, Long> implements IParticipantAnalysysService{
	private Logger log = Logger.getLogger(ParticipantAnalysysServiceImpl.class);
	
	@Autowired
	private IParticipantAnalysysDao participantcompetencylibraryanalysysDao;

	@Override
	public IAbstractParticipantDao<ParticipantAnalysys, Long> getDao() {
		// TODO Auto-generated method stub
		return participantcompetencylibraryanalysysDao;
	}
	
	public Long countBySearch(String search){
		return participantcompetencylibraryanalysysDao.countBySearch(search);
	}
	
	public List<ParticipantAnalysys> getBySearch(String search, int start, int num){
		return participantcompetencylibraryanalysysDao.getBySearch(search, start, num);
	}
	public ParticipantAnalysys getByPartIdAndAnalysysId(Long participantId, Long claId){
		return participantcompetencylibraryanalysysDao.getByPartIdAndAnalysysId(participantId, claId);
	}
}
