package com.dlabs.acs.dao.impl.assessement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.assessement.IParticipantDao;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.util.Validator;

@Repository
public class ParticipantDaoImpl extends AbstractDaoImpl<Participant, Long> implements IParticipantDao {
	private Logger log = Logger.getLogger(ParticipantDaoImpl.class);
	
	protected ParticipantDaoImpl() {
		super(Participant.class);
	}

	private static final String GET_BY_SEARCH = "Participant.getBySearch";
	private static final String COUNT_BY_SEARCH = "Participant.countBySearch";
	
	
	public List<Participant> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Participant> result = query.list();
		if (result == null) {
			result = new ArrayList<Participant>();
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
	
	
	private static final String GET_BY_BATCH_BY_SEARCH = "Participant.getByBatchBySearch";
	private static final String COUNT_BY_BATCH_BY_SEARCH = "Participant.countByBatchBySearch";
	
	
	public List<Map<String,Object>> getByBatchBySearch(Long batchId, String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_BATCH_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setLong("batchId", batchId);
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String,Object>> result = query.list();
		if (result == null) {
			result = new ArrayList<Map<String,Object>>();
		}
		return result;
	}
	
	public Long countByBatchBySearch(Long batchId, String search) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_BY_BATCH_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setLong("batchId", batchId);

		List<Long> result = query.list();
		
		return result.get(0);
	}
	
	
	
	
	
	private static final String GET_ALL_BY_SEARCH = "Participant.getAllBySearch";
	private static final String COUNT_ALL_BY_SEARCH = "Participant.countAllBySearch";
	
	
	public List<Map<String,Object>> getAllBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_ALL_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String,Object>> result = query.list();
		if (result == null) {
			result = new ArrayList<Map<String,Object>>();
		}
		return result;
	}
	
	public Long countAllBySearch(String search) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_ALL_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");

		List<Long> result = query.list();
		
		return result.get(0);
	}
	
	private static final String LOGIN = "Participant.login";
	public Participant login(String nik, String password) {
		if(Validator.isNull(nik) || Validator.isNull(password))
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(LOGIN);
		query.setString("nik", nik.toLowerCase());
		query.setString("password", password);
		
		query.setMaxResults(1);
		
		List<Participant> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	
	private static final String GET_PROFILE_BY_PARTICIPANT_ID = "Participant.getProfileByParticipantId";
	public Profile getProfileByParticipantId(Long id) {
		if(id == null)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_PROFILE_BY_PARTICIPANT_ID);
		query.setLong("id", id);
		
		query.setMaxResults(1);
		
		List<Profile> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	private static final String GET_BY_NIK_AND_ACTIVE = "Participant.getByNikAndActive";
	public Participant getByNikAndActive(String nik) {
		if(Validator.isNull(nik))
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_NIK_AND_ACTIVE);
		query.setString("nik", nik.toLowerCase());
		
		query.setMaxResults(1);
		
		List<Participant> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	private static final String GET_BATCH_BY_PARTICIPANTID= "Participant.getBatchByParticipantId";
	public Batch getBatchByParticipantId(Long id) {
		if(id == null)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BATCH_BY_PARTICIPANTID);
		query.setLong("id", id);
		
		query.setMaxResults(1);
		
		List<Batch> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	
	private static final String GET_PARTICIPANT_FINISH = "Participant.getParticipantFinish";
	public List<Participant> getParticipantFinish(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_PARTICIPANT_FINISH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Participant> result = query.list();
		if (result == null) {
			result = new ArrayList<Participant>();
		}
		return result;
	}
	
	private static final String COUNT_PARTICIPANT_FINISH = "Participant.countParticipantFinish";
	public Long countParticipantFinish(String search) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_PARTICIPANT_FINISH);
		query.setString("search", "%"+search.toLowerCase()+"%");

		List<Long> result = query.list();
		
		return result.get(0);
	}
	
	
	
	private static final String COUNT_ASSESSEMENT_FINISH_BY_BATCHID = "Participant.countAssessementFinishByBatchId";
	public Long countAssessementFinishByBatchId(Long batchId) {
		if(batchId == null )
		{
			return 0L;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_ASSESSEMENT_FINISH_BY_BATCHID);
		query.setLong("batchId", batchId);

		List<Long> result = query.list();
		
		return result.get(0);
	}
}
