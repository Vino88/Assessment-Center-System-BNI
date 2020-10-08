package com.dlabs.acs.service.impl.assessement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.dao.intf.assessement.IParticipantLcbDao;
import com.dlabs.acs.entity.assessement.ParticipantLcb;
import com.dlabs.acs.service.impl.AbstractParticipantServiceImpl;
import com.dlabs.acs.service.intf.assessement.IParticipantLcbService;

@Service
@Transactional(readOnly = true)
public class ParticipantLcbServiceImpl extends AbstractParticipantServiceImpl<ParticipantLcb, Long> implements IParticipantLcbService{
	private Logger log = Logger.getLogger(ParticipantLcbServiceImpl.class);
	
	@Autowired
	private IParticipantLcbDao participantlcbDao;

	@Override
	public IAbstractParticipantDao<ParticipantLcb, Long> getDao() {
		// TODO Auto-generated method stub
		return participantlcbDao;
	}
	
	public Long countBySearch(String search){
		return participantlcbDao.countBySearch(search);
	}
	
	public List<ParticipantLcb> getBySearch(String search, int start, int num){
		return participantlcbDao.getBySearch(search, start, num);
	}
}
