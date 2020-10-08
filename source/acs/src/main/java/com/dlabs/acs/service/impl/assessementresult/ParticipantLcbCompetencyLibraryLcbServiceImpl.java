package com.dlabs.acs.service.impl.assessementresult;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.assessementresult.IParticipantLcbCompetencyLibraryLcbDao;
import com.dlabs.acs.entity.assessementresult.ParticipantLcbCompetencyLibraryLcb;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.assessementresult.IParticipantLcbCompetencyLibraryLcbService;

@Service
@Transactional(readOnly = true)
public class ParticipantLcbCompetencyLibraryLcbServiceImpl extends AbstractServiceImpl<ParticipantLcbCompetencyLibraryLcb, Long> implements IParticipantLcbCompetencyLibraryLcbService{
	private Logger log = Logger.getLogger(ParticipantLcbCompetencyLibraryLcbServiceImpl.class);
	
	@Autowired
	private IParticipantLcbCompetencyLibraryLcbDao participantlcbcompetencylibrarylcbDao;

	@Override
	public IAbstractDao<ParticipantLcbCompetencyLibraryLcb, Long> getDao() {
		// TODO Auto-generated method stub
		return participantlcbcompetencylibrarylcbDao;
	}
	
	public Long countBySearch(String search){
		return participantlcbcompetencylibrarylcbDao.countBySearch(search);
	}
	
	public List<ParticipantLcbCompetencyLibraryLcb> getBySearch(String search, int start, int num){
		return participantlcbcompetencylibrarylcbDao.getBySearch(search, start, num);
	}
}
