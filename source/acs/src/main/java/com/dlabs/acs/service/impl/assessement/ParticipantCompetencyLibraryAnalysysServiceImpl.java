package com.dlabs.acs.service.impl.assessement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.assessement.IParticipantCompetencyLibraryAnalysysDao;
import com.dlabs.acs.entity.assessement.ParticipantCompetencyLibraryAnalysys;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.assessement.IParticipantCompetencyLibraryAnalysysService;

@Service
@Transactional(readOnly = true)
public class ParticipantCompetencyLibraryAnalysysServiceImpl extends AbstractServiceImpl<ParticipantCompetencyLibraryAnalysys, Long> implements IParticipantCompetencyLibraryAnalysysService{
	private Logger log = Logger.getLogger(ParticipantCompetencyLibraryAnalysysServiceImpl.class);
	
	@Autowired
	private IParticipantCompetencyLibraryAnalysysDao participantcompetencylibraryanalysysDao;

	@Override
	public IAbstractDao<ParticipantCompetencyLibraryAnalysys, Long> getDao() {
		// TODO Auto-generated method stub
		return participantcompetencylibraryanalysysDao;
	}
	
	public Long countBySearch(String search){
		return participantcompetencylibraryanalysysDao.countBySearch(search);
	}
	
	public List<ParticipantCompetencyLibraryAnalysys> getBySearch(String search, int start, int num){
		return participantcompetencylibraryanalysysDao.getBySearch(search, start, num);
	}
	public ParticipantCompetencyLibraryAnalysys getByPartIdAndClaId(Long participantId, Long claId){
		return participantcompetencylibraryanalysysDao.getByPartIdAndClaId(participantId, claId);
	}
}
