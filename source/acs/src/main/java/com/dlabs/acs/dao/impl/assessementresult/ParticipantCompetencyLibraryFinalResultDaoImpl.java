package com.dlabs.acs.dao.impl.assessementresult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractParticipantDaoImpl;
import com.dlabs.acs.dao.intf.assessementresult.IParticipantCompetencyLibraryFinalResultDao;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;

@Repository
public class ParticipantCompetencyLibraryFinalResultDaoImpl extends AbstractParticipantDaoImpl<ParticipantCompetencyLibraryFinalResult, Long> implements IParticipantCompetencyLibraryFinalResultDao {
	private Logger log = Logger.getLogger(ParticipantCompetencyLibraryFinalResultDaoImpl.class);
	
	protected ParticipantCompetencyLibraryFinalResultDaoImpl() {
		super(ParticipantCompetencyLibraryFinalResult.class);
	}

	private static final String GET_BY_SEARCH = "ParticipantCompetencyLibraryFinalResult.getBySearch";
	private static final String COUNT_BY_SEARCH = "ParticipantCompetencyLibraryFinalResult.countBySearch";
	
	
	public List<ParticipantCompetencyLibraryFinalResult> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<ParticipantCompetencyLibraryFinalResult> result = query.list();
		if (result == null) {
			result = new ArrayList<ParticipantCompetencyLibraryFinalResult>();
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
	
	
	private static final String COUNT_BY_PARTICIPANTID_ORDERBY_COMPETENCYORDER = "ParticipantCompetencyLibraryFinalResult.getByParticipantIdOrderByCompetencyOrder";

	public List<ParticipantCompetencyLibraryFinalResult> getByParticipantIdOrderByCompetencyOrder(Long participantId) {
		if(participantId == null )
		{
			return Collections.EMPTY_LIST;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_BY_PARTICIPANTID_ORDERBY_COMPETENCYORDER);
		query.setLong("participantId", participantId);
		
		
		List<ParticipantCompetencyLibraryFinalResult> result = query.list();
		if (result == null) {
			result = new ArrayList<ParticipantCompetencyLibraryFinalResult>();
		}
		return result;
	}
	
	private static final String GET_BY_PARTICIPANT_ID_AND_COMPETENCY_ID = "ParticipantCompetencyLibraryFinalResult.getByParticipantIdAndCompetencyLibraryId";
	public ParticipantCompetencyLibraryFinalResult getByParticipantIdAndCompetencyLibraryId(Long participantId, Long competencyLibraryId) {
		if(participantId == null || competencyLibraryId == null)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_PARTICIPANT_ID_AND_COMPETENCY_ID);
		query.setLong("participantId", participantId);
		query.setLong("competencyLibraryId", competencyLibraryId);
		
		query.setMaxResults(1);
		
		List<ParticipantCompetencyLibraryFinalResult> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	
	private static final String GET_REPORT_BATCH_MEET = "ParticipantCompetencyLibraryFinalResult.getReportBatchMeet";
	private static final String GET_REPORT_BATCH_ABOVE = "ParticipantCompetencyLibraryFinalResult.getReportBatchAbove";
	private static final String GET_REPORT_BATCH_BELOW = "ParticipantCompetencyLibraryFinalResult.getReportBatchBelow";
	
	public List<Map<String,Object>> getReportBatchMeet(Long batchId) {
		if(batchId == null || batchId < 1)
		{
			return new ArrayList<Map<String,Object>>();
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_REPORT_BATCH_MEET);
		
		query.setLong("batchId", batchId);

		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String,Object>> result = query.list();
		if (result == null) {
			result = new ArrayList<Map<String,Object>>();
		}
		return result;
	}
	
	public List<Map<String,Object>> getReportBatchAbove(Long batchId) {
		if(batchId == null || batchId < 1)
		{
			return new ArrayList<Map<String,Object>>();
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_REPORT_BATCH_ABOVE);
		
		query.setLong("batchId", batchId);

		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String,Object>> result = query.list();
		if (result == null) {
			result = new ArrayList<Map<String,Object>>();
		}
		return result;
	}
	
	public List<Map<String,Object>> getReportBatchBelow(Long batchId) {
		if(batchId == null || batchId < 1)
		{
			return new ArrayList<Map<String,Object>>();
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_REPORT_BATCH_BELOW);
		
		query.setLong("batchId", batchId);

		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String,Object>> result = query.list();
		if (result == null) {
			result = new ArrayList<Map<String,Object>>();
		}
		return result;
	}
	
	private static final String GET_REPORT_GROUP = "ParticipantCompetencyLibraryFinalResult.getReportGroup";
	public List<Map<String,Object>> getReportGroup(Long batchId) {
		if(batchId == null || batchId < 1)
		{
			return new ArrayList<Map<String,Object>>();
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_REPORT_GROUP);
		
		query.setLong("batchId", batchId);

		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String,Object>> result = query.list();
		if (result == null) {
			result = new ArrayList<Map<String,Object>>();
		}
		return result;
	}
}
