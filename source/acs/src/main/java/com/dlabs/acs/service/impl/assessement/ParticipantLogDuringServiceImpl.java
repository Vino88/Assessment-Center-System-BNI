package com.dlabs.acs.service.impl.assessement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.dao.intf.assessement.IParticipantLogDuringDao;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.service.impl.AbstractParticipantServiceImpl;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;

@Service
@Transactional(readOnly = true)
public class ParticipantLogDuringServiceImpl extends AbstractParticipantServiceImpl<ParticipantLogDuring, Long> implements IParticipantLogDuringService{
	private Logger log = Logger.getLogger(ParticipantLogDuringServiceImpl.class);
	
	@Autowired
	private IParticipantLogDuringDao participantlogduringDao;

	@Override
	public IAbstractParticipantDao<ParticipantLogDuring, Long> getDao() {
		// TODO Auto-generated method stub
		return participantlogduringDao;
	}
	
	public Long countBySearch(String search){
		return participantlogduringDao.countBySearch(search);
	}
	
	public List<ParticipantLogDuring> getBySearch(String search, int start, int num){
		return participantlogduringDao.getBySearch(search, start, num);
	}
}
