package com.dlabs.acs.service.impl.assessement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.dao.intf.assessement.IParticipantCapDao;
import com.dlabs.acs.entity.assessement.ParticipantCap;
import com.dlabs.acs.service.impl.AbstractParticipantServiceImpl;
import com.dlabs.acs.service.intf.assessement.IParticipantCapService;

@Service
@Transactional(readOnly = true)
public class ParticipantCapServiceImpl extends AbstractParticipantServiceImpl<ParticipantCap, Long> implements IParticipantCapService{
	private Logger log = Logger.getLogger(ParticipantCapServiceImpl.class);
	
	@Autowired
	private IParticipantCapDao participantcapDao;

	@Override
	public IAbstractParticipantDao<ParticipantCap, Long> getDao() {
		// TODO Auto-generated method stub
		return participantcapDao;
	}
	
	public Long countBySearch(String search){
		return participantcapDao.countBySearch(search);
	}
	
	public List<ParticipantCap> getBySearch(String search, int start, int num){
		return participantcapDao.getBySearch(search, start, num);
	}
	
	public ParticipantCap getByParticipantIdAndCapId(Long participantId, Long capId) {
		return participantcapDao.getByParticipantIdAndCapId(participantId, capId);
	}
}
