package com.dlabs.acs.service.impl.participant;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.dao.intf.participant.IParticipantLogPostDao;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.participant.ParticipantLogPost;
import com.dlabs.acs.service.impl.AbstractParticipantServiceImpl;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;

@Service
@Transactional(readOnly = true)
public class ParticipantLogPostServiceImpl extends AbstractParticipantServiceImpl<ParticipantLogPost, Long> implements IParticipantLogPostService{
	private Logger log = Logger.getLogger(ParticipantLogPostServiceImpl.class);
	
	@Autowired
	private IParticipantLogPostDao participantlogpostDao;

	@Override
	public IAbstractParticipantDao<ParticipantLogPost, Long> getDao() {
		// TODO Auto-generated method stub
		return participantlogpostDao;
	}
	
	public Long countBySearch(String search){
		return participantlogpostDao.countBySearch(search);
	}
	
	public List<ParticipantLogPost> getBySearch(String search, int start, int num){
		return participantlogpostDao.getBySearch(search, start, num);
	}
	
	public Long countParticipantAssignToMe(Long userAdminId, String search)
	{
		return participantlogpostDao.countParticipantAssignToMe(userAdminId, search);
	}
	public List<Participant> getParticipantAssignToMe(Long userAdminId,String search, int start, int num)
	{
		return participantlogpostDao.getParticipantAssignToMe(userAdminId, search, start, num);
	}

	@Override
	public Long countAssessorDetail() {
		// TODO Auto-generated method stub
		return participantlogpostDao.countAssessorDetail();
	}

	@Override
	public List getAssessorDetail(int start, int num) {
		// TODO Auto-generated method stub
		return participantlogpostDao.getAssessorDetail(start, num);
	}

	@Override
	public Long countAssessorDetailByAssessorId(List<Long> assessorIds) {
		// TODO Auto-generated method stub
		return participantlogpostDao.countAssessorDetailByAssessorId(assessorIds);
	}

	@Override
	public List getAssessorDetailByAssessorId(List<Long> assessorIds, int start, int num) {
		// TODO Auto-generated method stub
		return participantlogpostDao.getAssessorDetailByAssessorId(assessorIds, start, num);
	}

	@Override
	public Long countAssessorDetailByBatchTime(Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		return participantlogpostDao.countAssessorDetailByBatchTime(startTime, endTime);
	}

	@Override
	public List getAssessorDetailByBatchTime(Date startTime, Date endTime, int start, int num) {
		// TODO Auto-generated method stub
		return participantlogpostDao.getAssessorDetailByBatchTime(startTime, endTime, start, num);
	}

	@Override
	public Long countAssessorDetailByAssessorIdAndBatchTime(List<Long> assessorIds, Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		return participantlogpostDao.countAssessorDetailByAssessorIdAndBatchTime(assessorIds, startTime, endTime);
	}

	@Override
	public List getAssessorDetailByAssessorIdAndBatchTime(List<Long> assessorIds, Date startTime, Date endTime,
			int start, int num) {
		// TODO Auto-generated method stub
		return participantlogpostDao.getAssessorDetailByAssessorIdAndBatchTime(assessorIds, startTime, endTime, start, num);
	}

	@Override
	public Long countAssessorDetailByDone(int assessDone) {
		// TODO Auto-generated method stub
		return participantlogpostDao.countAssessorDetailByDone(assessDone);
	}

	@Override
	public List getAssessorDetailByDone(int assessDone, int start, int num) {
		// TODO Auto-generated method stub
		return participantlogpostDao.getAssessorDetailByDone(assessDone, start, num);
	}

	@Override
	public Long countAssessorDetailByAssessorIdByDone(List<Long> assessorIds, int assessDone) {
		// TODO Auto-generated method stub
		return participantlogpostDao.countAssessorDetailByAssessorIdByDone(assessorIds, assessDone);
	}

	@Override
	public List getAssessorDetailByAssessorIdByDone(List<Long> assessorIds, int assessDone, int start, int num) {
		// TODO Auto-generated method stub
		return participantlogpostDao.getAssessorDetailByAssessorIdByDone(assessorIds, assessDone, start, num);
	}

	@Override
	public Long countAssessorDetailByBatchTimeByDone(Date startTime, Date endTime, int assessDone) {
		// TODO Auto-generated method stub
		return participantlogpostDao.countAssessorDetailByBatchTimeByDone(startTime, endTime, assessDone);
	}

	@Override
	public List getAssessorDetailByBatchTimeByDone(Date startTime, Date endTime, int assessDone, int start, int num) {
		// TODO Auto-generated method stub
		return participantlogpostDao.getAssessorDetailByBatchTimeByDone(startTime, endTime, assessDone, start, num);
	}

	@Override
	public Long countAssessorDetailByAssessorIdAndBatchTimeByDone(List<Long> assessorIds, Date startTime, Date endTime,
			int assessDone) {
		// TODO Auto-generated method stub
		return participantlogpostDao.countAssessorDetailByAssessorIdAndBatchTimeByDone(assessorIds, startTime, endTime, assessDone);
	}

	@Override
	public List getAssessorDetailByAssessorIdAndBatchTimeByDone(List<Long> assessorIds, Date startTime, Date endTime,
			int assessDone, int start, int num) {
		// TODO Auto-generated method stub
		return participantlogpostDao.getAssessorDetailByAssessorIdAndBatchTimeByDone(assessorIds, startTime, endTime, assessDone, start, num);
	}

	@Override
	public Long countParticipantHistory(String search) {
		// TODO Auto-generated method stub
		return participantlogpostDao.countParticipantHistory(search);
	}

	@Override
	public List getParticipantHistory(String search, int start, int num) {
		// TODO Auto-generated method stub
		return participantlogpostDao.getParticipantHistory(search, start, num);
	}
	
}
