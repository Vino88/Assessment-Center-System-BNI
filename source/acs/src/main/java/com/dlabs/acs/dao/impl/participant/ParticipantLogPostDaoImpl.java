package com.dlabs.acs.dao.impl.participant;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractParticipantDaoImpl;
import com.dlabs.acs.dao.intf.participant.IParticipantLogPostDao;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.participant.ParticipantLogPost;

@Repository
public class ParticipantLogPostDaoImpl extends AbstractParticipantDaoImpl<ParticipantLogPost, Long> implements IParticipantLogPostDao {
	private Logger log = Logger.getLogger(ParticipantLogPostDaoImpl.class);
	
	protected ParticipantLogPostDaoImpl() {
		super(ParticipantLogPost.class);
	}

	private static final String GET_BY_SEARCH = "ParticipantLogPost.getBySearch";
	private static final String COUNT_BY_SEARCH = "ParticipantLogPost.countBySearch";
	
	
	public List<ParticipantLogPost> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<ParticipantLogPost> result = query.list();
		if (result == null) {
			result = new ArrayList<ParticipantLogPost>();
		}
		return result;
	}
	
	public Long countBySearch(String search) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");

		List<Long> result = query.list();
		
		return result.get(0);
	}
	
	
	
	private static final String GET_PARTICIPANT_ASSIGN_TO_ME = "ParticipantLogPost.getParticipantAssignToMe";
	
	
	
	public List<Participant> getParticipantAssignToMe(Long userAdminId,String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_PARTICIPANT_ASSIGN_TO_ME);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setLong("userAdminId", userAdminId);
		
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Participant> result = query.list();
		if (result == null) {
			result = new ArrayList<Participant>();
		}
		return result;
	}
	
	private static final String COUNT_PARTICIPANT_ASSIGN_TO_ME = "ParticipantLogPost.countParticipantAssignToMe";
	public Long countParticipantAssignToMe(Long userAdminId, String search) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_PARTICIPANT_ASSIGN_TO_ME);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setLong("userAdminId", userAdminId);

		List<Long> result = query.list();
		
		return result.get(0);
	}
	
	
	private static final String COUNT_ASSESSOR_DETAIL = "ParticipantLogPost.countAssessorDetail";
	@Override
	public Long countAssessorDetail() {
		
		Query query = getCurrentSession().getNamedQuery(COUNT_ASSESSOR_DETAIL);
		
		List<BigInteger> result = query.list();
		
		return result.get(0).longValue();
	}
	
	private static final String GET_ASSESSOR_DETAIL = "ParticipantLogPost.getAssessorDetail";
	@Override
	public List getAssessorDetail(int start, int num)
	{
		Query query = getCurrentSession().getNamedQuery(GET_ASSESSOR_DETAIL);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Map<String,Object>> list = query.list();
		
		if(list == null)
		{
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
	
	
	
	
	
	private static final String COUNT_ASSESSOR_DETAIL_BY_ASSESSOR_ID = "ParticipantLogPost.countAssessorDetailByAssessorId";
	@Override
	public Long countAssessorDetailByAssessorId(List<Long> assessorIds) {
		
		Query query = getCurrentSession().getNamedQuery(COUNT_ASSESSOR_DETAIL_BY_ASSESSOR_ID);
		query.setParameterList("assessorIds", assessorIds);
		List<BigInteger> result = query.list();
		
		return result.get(0).longValue();
	}
	
	private static final String GET_ASSESSOR_DETAIL_BY_ASSESSOR_ID = "ParticipantLogPost.getAssessorDetailByAssessorId";
	@Override
	public List getAssessorDetailByAssessorId(List<Long> assessorIds, int start, int num)
	{
		Query query = getCurrentSession().getNamedQuery(GET_ASSESSOR_DETAIL_BY_ASSESSOR_ID);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		query.setParameterList("assessorIds", assessorIds);
		
		List<Map<String,Object>> list = query.list();
		
		if(list == null)
		{
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
	
	
	
	
	private static final String COUNT_ASSESSOR_DETAIL_BY_BATCH_TIME = "ParticipantLogPost.countAssessorDetailByBatchTime";
	@Override
	public Long countAssessorDetailByBatchTime(Date startTime, Date endTime) {
		
		Query query = getCurrentSession().getNamedQuery(COUNT_ASSESSOR_DETAIL_BY_BATCH_TIME);
		query.setTimestamp("startTime", startTime);
		query.setTimestamp("endTime", endTime);
		
		List<BigInteger> result = query.list();
		
		return result.get(0).longValue();
	}
	
	private static final String GET_ASSESSOR_DETAIL_BY_BATCH_TIME = "ParticipantLogPost.getAssessorDetailByBatchTime";
	@Override
	public List getAssessorDetailByBatchTime(Date startTime, Date endTime, int start, int num)
	{
		Query query = getCurrentSession().getNamedQuery(GET_ASSESSOR_DETAIL_BY_BATCH_TIME);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(num);
		query.setTimestamp("startTime", startTime);
		query.setTimestamp("endTime", endTime);
		List<Map<String,Object>> list = query.list();
		
		if(list == null)
		{
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
	
	
	
	private static final String COUNT_ASSESSOR_DETAIL_BY_ASSESSORID_AND_BATCH_TIME = "ParticipantLogPost.countAssessorDetailByAssessorIdAndBatchTime";
	@Override
	public Long countAssessorDetailByAssessorIdAndBatchTime(List<Long> assessorIds,Date startTime, Date endTime) {
		
		Query query = getCurrentSession().getNamedQuery(COUNT_ASSESSOR_DETAIL_BY_ASSESSORID_AND_BATCH_TIME);
		query.setTimestamp("startTime", startTime);
		query.setTimestamp("endTime", endTime);
		query.setParameterList("assessorIds", assessorIds);
		List<BigInteger> result = query.list();
		
		return result.get(0).longValue();
	}
	
	private static final String GET_ASSESSOR_DETAIL_BY_ASSESSORID_AND_BATCH_TIME = "ParticipantLogPost.getAssessorDetailByAssessorIdAndBatchTime";
	@Override
	public List getAssessorDetailByAssessorIdAndBatchTime(List<Long> assessorIds,Date startTime, Date endTime,int start, int num)
	{
		Query query = getCurrentSession().getNamedQuery(GET_ASSESSOR_DETAIL_BY_ASSESSORID_AND_BATCH_TIME);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(num);
		query.setTimestamp("startTime", startTime);
		query.setTimestamp("endTime", endTime);
		query.setParameterList("assessorIds", assessorIds);
		List<Map<String,Object>> list = query.list();
		
		if(list == null)
		{
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
	
	
	
	
	
	private static final String COUNT_ASSESSOR_DETAIL_BY_DONE = "ParticipantLogPost.countAssessorDetailByDone";
	@Override
	public Long countAssessorDetailByDone(int assessDone) {
		
		Query query = getCurrentSession().getNamedQuery(COUNT_ASSESSOR_DETAIL_BY_DONE);
		query.setInteger("assessDone", assessDone);
		List<BigInteger> result = query.list();
		
		return result.get(0).longValue();
	}
	
	private static final String GET_ASSESSOR_DETAIL_BY_DONE = "ParticipantLogPost.getAssessorDetailByDone";
	@Override
	public List getAssessorDetailByDone(int assessDone,int start, int num)
	{
		Query query = getCurrentSession().getNamedQuery(GET_ASSESSOR_DETAIL_BY_DONE);
		query.setInteger("assessDone", assessDone);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Map<String,Object>> list = query.list();
		
		if(list == null)
		{
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
	
	
	
	
	
	
	private static final String COUNT_ASSESSOR_DETAIL_BY_ASSESSOR_ID_BY_DONE = "ParticipantLogPost.countAssessorDetailByAssessorIdByDone";
	@Override
	public Long countAssessorDetailByAssessorIdByDone(List<Long> assessorIds,int assessDone) {
		
		Query query = getCurrentSession().getNamedQuery(COUNT_ASSESSOR_DETAIL_BY_ASSESSOR_ID_BY_DONE);
		query.setInteger("assessDone", assessDone);
		query.setParameterList("assessorIds", assessorIds);
		List<BigInteger> result = query.list();
		
		return result.get(0).longValue();
	}
	
	private static final String GET_ASSESSOR_DETAIL_BY_ASSESSOR_ID_BY_DONE = "ParticipantLogPost.getAssessorDetailByAssessorIdByDone";
	@Override
	public List getAssessorDetailByAssessorIdByDone(List<Long> assessorIds,int assessDone,int start, int num)
	{
		Query query = getCurrentSession().getNamedQuery(GET_ASSESSOR_DETAIL_BY_ASSESSOR_ID_BY_DONE);
		query.setInteger("assessDone", assessDone);
		query.setParameterList("assessorIds", assessorIds);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Map<String,Object>> list = query.list();
		
		if(list == null)
		{
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
	
	
	
	private static final String COUNT_ASSESSOR_DETAIL_BY_BATCH_TIME_BY_DONE = "ParticipantLogPost.countAssessorDetailByBatchTimeByDone";
	@Override
	public Long countAssessorDetailByBatchTimeByDone(Date startTime, Date endTime,int assessDone) {
		
		Query query = getCurrentSession().getNamedQuery(COUNT_ASSESSOR_DETAIL_BY_BATCH_TIME_BY_DONE);
		query.setInteger("assessDone", assessDone);
		query.setTimestamp("startTime", startTime);
		query.setTimestamp("endTime", endTime);
		List<BigInteger> result = query.list();
		
		return result.get(0).longValue();
	}
	
	private static final String GET_ASSESSOR_DETAIL_BY_BATCH_TIME_BY_DONE = "ParticipantLogPost.getAssessorDetailByBatchTimeByDone";
	@Override
	public List getAssessorDetailByBatchTimeByDone(Date startTime, Date endTime,int assessDone,int start, int num)
	{
		Query query = getCurrentSession().getNamedQuery(GET_ASSESSOR_DETAIL_BY_BATCH_TIME_BY_DONE);
		query.setInteger("assessDone", assessDone);
		query.setTimestamp("startTime", startTime);
		query.setTimestamp("endTime", endTime);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Map<String,Object>> list = query.list();
		
		if(list == null)
		{
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
	
	
	private static final String COUNT_ASSESSOR_DETAIL_BY_ASSESSOR_ID_BY_BATCH_TIME_BY_DONE = "ParticipantLogPost.countAssessorDetailByAssessorIdAndBatchTimeByDone";
	@Override
	public Long countAssessorDetailByAssessorIdAndBatchTimeByDone(List<Long> assessorIds,Date startTime, Date endTime,int assessDone) {
		
		Query query = getCurrentSession().getNamedQuery(COUNT_ASSESSOR_DETAIL_BY_ASSESSOR_ID_BY_BATCH_TIME_BY_DONE);
		query.setInteger("assessDone", assessDone);
		query.setTimestamp("startTime", startTime);
		query.setTimestamp("endTime", endTime);
		query.setParameterList("assessorIds", assessorIds);
		List<BigInteger> result = query.list();
		
		return result.get(0).longValue();
	}
	
	private static final String GET_ASSESSOR_DETAIL_BY_ASSESSOR_ID_BY_BATCH_TIME_BY_DONE = "ParticipantLogPost.getAssessorDetailByAssessorIdAndBatchTimeByDone";
	@Override
	public List getAssessorDetailByAssessorIdAndBatchTimeByDone(List<Long> assessorIds,Date startTime, Date endTime,int assessDone,int start, int num)
	{
		Query query = getCurrentSession().getNamedQuery(GET_ASSESSOR_DETAIL_BY_ASSESSOR_ID_BY_BATCH_TIME_BY_DONE);
		query.setInteger("assessDone", assessDone);
		query.setTimestamp("startTime", startTime);
		query.setTimestamp("endTime", endTime);
		query.setParameterList("assessorIds", assessorIds);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Map<String,Object>> list = query.list();
		
		if(list == null)
		{
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	private static final String COUNT_PARTICIPANT_HISTORY = "ParticipantLogPost.countParticipantHistory";
	@Override
	public Long countParticipantHistory(String search) {
		if(search == null)
		{
			search ="";
		}
		Query query = getCurrentSession().getNamedQuery(COUNT_PARTICIPANT_HISTORY);
		query.setString("search", "%"+search.toLowerCase()+"%");
		List<Long> result = query.list();
		
		return result.get(0).longValue();
	}
	
	
	private static final String GET_PARTICIPANT_HISTORY = "ParticipantLogPost.getParticipantHistory";
	@Override
	public List getParticipantHistory(String search, int start, int num)
	{
		if(search == null)
		{
			search ="";
		}
		Query query = getCurrentSession().getNamedQuery(GET_PARTICIPANT_HISTORY);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Map<String,Object>> list = query.list();
		
		if(list == null)
		{
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
}
