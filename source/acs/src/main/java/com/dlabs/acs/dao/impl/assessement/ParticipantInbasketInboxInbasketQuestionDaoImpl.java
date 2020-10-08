package com.dlabs.acs.dao.impl.assessement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractParticipantDaoImpl;
import com.dlabs.acs.dao.intf.assessement.IParticipantInbasketInboxInbasketQuestionDao;
import com.dlabs.acs.entity.assessement.ParticipantInbasketInboxInbasketQuestion;

@Repository
public class ParticipantInbasketInboxInbasketQuestionDaoImpl extends AbstractParticipantDaoImpl<ParticipantInbasketInboxInbasketQuestion, Long> implements IParticipantInbasketInboxInbasketQuestionDao {
	private Logger log = Logger.getLogger(ParticipantInbasketInboxInbasketQuestionDaoImpl.class);
	
	protected ParticipantInbasketInboxInbasketQuestionDaoImpl() {
		super(ParticipantInbasketInboxInbasketQuestion.class);
	}

	private static final String GET_BY_SEARCH = "ParticipantInbasketInboxInbasketQuestion.getBySearch";
	private static final String COUNT_BY_SEARCH = "ParticipantInbasketInboxInbasketQuestion.countBySearch";
	
	
	public List<ParticipantInbasketInboxInbasketQuestion> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<ParticipantInbasketInboxInbasketQuestion> result = query.list();
		if (result == null) {
			result = new ArrayList<ParticipantInbasketInboxInbasketQuestion>();
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
	
	
	private static final String GET_BY_PARTID_AND_IIIBID = "ParticipantInbasketInboxInbasketQuestion.getByPartIdAndIiibId";
	
	
	public ParticipantInbasketInboxInbasketQuestion getByPartIdAndIiibId(Long participantId, Long iiibId) {
		if(participantId == null || iiibId == null)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_PARTID_AND_IIIBID);
		query.setLong("participantId", participantId);
		query.setLong("iiibId", iiibId);
		query.setMaxResults(1);
		List<ParticipantInbasketInboxInbasketQuestion> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	private static final String COUNT_MEMO_BY_PART_ID = "ParticipantInbasketInboxInbasketQuestion.countMemoByPartId";
	public Long countMemoByPartId(Long participantId) {
		if(participantId == 0L)
		{
			return 0L;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_MEMO_BY_PART_ID);
		query.setLong("participantId", participantId);

		List result = query.list();
		if(result != null && result.size() > 0 )
		{
			return Integer.valueOf(result.size()).longValue();
		}
		return 0L;
	}
}
