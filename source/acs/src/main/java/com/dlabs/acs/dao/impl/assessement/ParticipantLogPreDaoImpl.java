package com.dlabs.acs.dao.impl.assessement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractParticipantDaoImpl;
import com.dlabs.acs.dao.intf.assessement.IParticipantLogPreDao;
import com.dlabs.acs.entity.assessement.ParticipantLogPre;

@Repository
public class ParticipantLogPreDaoImpl extends AbstractParticipantDaoImpl<ParticipantLogPre, Long> implements IParticipantLogPreDao {
	private Logger log = Logger.getLogger(ParticipantLogPreDaoImpl.class);
	
	protected ParticipantLogPreDaoImpl() {
		super(ParticipantLogPre.class);
	}

	private static final String GET_BY_SEARCH = "ParticipantLogPre.getBySearch";
	private static final String COUNT_BY_SEARCH = "ParticipantLogPre.countBySearch";
	
	
	public List<ParticipantLogPre> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<ParticipantLogPre> result = query.list();
		if (result == null) {
			result = new ArrayList<ParticipantLogPre>();
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
	
	
	private static final String GET_INVITATIONSENTTIME_NULL_BY_BATCHID = "ParticipantLogPre.getInvitationSentTimeNullByBatchId";
	
	public List<Long> getInvitationSentTimeNullByBatchId(Long batchId) {
		if(batchId == null || batchId.intValue() == 0)
		{
			return new ArrayList<Long>();
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_INVITATIONSENTTIME_NULL_BY_BATCHID);
		query.setLong("batchId", batchId);
	
		
		List<Long> result = query.list();
		if (result == null) {
			result = new ArrayList<Long>();
		}
		return result;
	}
}
