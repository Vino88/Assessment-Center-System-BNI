package com.dlabs.acs.dao.intf.participant;

import java.util.Date;
import java.util.List;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.participant.ParticipantLogPost;


public interface IParticipantLogPostDao extends IAbstractParticipantDao <ParticipantLogPost,Long>{
	
	public Long countBySearch(String search);
	
	public List<ParticipantLogPost> getBySearch(String search, int start, int num);
	
	public Long countParticipantAssignToMe(Long userAdminId, String search);
	public List<Participant> getParticipantAssignToMe(Long userAdminId,String search, int start, int num);
	
	
	public Long countAssessorDetail();
	public List getAssessorDetail(int start, int num);
	
	public Long countAssessorDetailByAssessorId(List<Long> assessorIds);
	public List getAssessorDetailByAssessorId(List<Long> assessorIds, int start, int num);
	
	public Long countAssessorDetailByBatchTime(Date startTime, Date endTime);
	public List getAssessorDetailByBatchTime(Date startTime, Date endTime, int start, int num);
	
	public Long countAssessorDetailByAssessorIdAndBatchTime(List<Long> assessorIds,Date startTime, Date endTime);
	public List getAssessorDetailByAssessorIdAndBatchTime(List<Long> assessorIds,Date startTime, Date endTime,int start, int num);
	
	
	public Long countAssessorDetailByDone(int assessDone);
	public List getAssessorDetailByDone(int assessDone,int start, int num);
	
	public Long countAssessorDetailByAssessorIdByDone(List<Long> assessorIds,int assessDone);
	public List getAssessorDetailByAssessorIdByDone(List<Long> assessorIds,int assessDone,int start, int num);
	
	public Long countAssessorDetailByBatchTimeByDone(Date startTime, Date endTime,int assessDone);
	public List getAssessorDetailByBatchTimeByDone(Date startTime, Date endTime,int assessDone,int start, int num);
	
	public Long countAssessorDetailByAssessorIdAndBatchTimeByDone(List<Long> assessorIds,Date startTime, Date endTime,int assessDone);
	public List getAssessorDetailByAssessorIdAndBatchTimeByDone(List<Long> assessorIds,Date startTime, Date endTime,int assessDone,int start, int num);
	
	public Long countParticipantHistory(String search);
	public List getParticipantHistory(String search, int start, int num);
}
