package com.dlabs.acs.service.impl.assessement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.dao.intf.assessement.IParticipantLogPreDao;
import com.dlabs.acs.entity.assessement.ParticipantLogPre;
import com.dlabs.acs.service.impl.AbstractParticipantServiceImpl;
import com.dlabs.acs.service.intf.assessement.IParticipantLogPreService;

@Service
@Transactional(readOnly = true)
public class ParticipantLogPreServiceImpl extends AbstractParticipantServiceImpl<ParticipantLogPre, Long> implements IParticipantLogPreService{
	private Logger log = Logger.getLogger(ParticipantLogPreServiceImpl.class);
	
	@Autowired
	private IParticipantLogPreDao participantlogpreDao;

	@Override
	public IAbstractParticipantDao<ParticipantLogPre, Long> getDao() {
		// TODO Auto-generated method stub
		return participantlogpreDao;
	}
	
	public Long countBySearch(String search){
		return participantlogpreDao.countBySearch(search);
	}
	
	public List<ParticipantLogPre> getBySearch(String search, int start, int num){
		return participantlogpreDao.getBySearch(search, start, num);
	}
	
	public List<Long> getInvitationSentTimeNullByBatchId(Long batchId){
		return participantlogpreDao.getInvitationSentTimeNullByBatchId(batchId);
	}
}
