package com.dlabs.acs.service.impl.assessement;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.assessement.IParticipantDao;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.assessement.IParticipantService;

@Service
@Transactional(readOnly = true)
public class ParticipantServiceImpl extends AbstractServiceImpl<Participant, Long> implements IParticipantService{
	private Logger log = Logger.getLogger(ParticipantServiceImpl.class);
	
	@Autowired
	private IParticipantDao participantDao;

	@Override
	public IAbstractDao<Participant, Long> getDao() {
		// TODO Auto-generated method stub
		return participantDao;
	}
	
	public Long countBySearch(String search){
		return participantDao.countBySearch(search);
	}
	
	public List<Participant> getBySearch(String search, int start, int num){
		return participantDao.getBySearch(search, start, num);
	}
	public Participant login(String email, String password)
	{
		return participantDao.login(email, password);
	}
	
	public Profile getProfileByParticipantId(Long id){
		return participantDao.getProfileByParticipantId(id);
	}

	@Override
	public List<Map<String,Object>> getByBatchBySearch(Long batchId,String search, int start, int num) {
		return participantDao.getByBatchBySearch(batchId, search, start, num);
	}

	@Override
	public Long countByBatchBySearch(Long batchId,String search) {
		return participantDao.countByBatchBySearch(batchId, search);
	}
	
	public List<Map<String,Object>> getAllBySearch(String search, int start, int num) {
		return participantDao.getAllBySearch(search,start,num);
	}
	public Long countAllBySearch(String search){
		return participantDao.countAllBySearch(search);
	}
	
	public Participant getByNikAndActive(String nik){
		return participantDao.getByNikAndActive(nik);
	}
	
	public Batch getBatchByParticipantId(Long id){
		return participantDao.getBatchByParticipantId(id);
	}
	
	public List<Participant> getParticipantFinish(String search, int start, int num)
	{
		return participantDao.getParticipantFinish(search, start, num);
	}
	public Long countParticipantFinish(String search)
	{
		return participantDao.countParticipantFinish(search);
	}
	
	public Long countAssessementFinishByBatchId(Long batchId)
	{
		return participantDao.countAssessementFinishByBatchId(batchId);
	}
}
