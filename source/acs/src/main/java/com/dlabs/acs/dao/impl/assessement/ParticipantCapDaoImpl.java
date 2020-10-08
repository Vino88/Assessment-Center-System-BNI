package com.dlabs.acs.dao.impl.assessement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractParticipantDaoImpl;
import com.dlabs.acs.dao.intf.assessement.IParticipantCapDao;
import com.dlabs.acs.entity.assessement.ParticipantCap;

@Repository
public class ParticipantCapDaoImpl extends AbstractParticipantDaoImpl<ParticipantCap, Long> implements IParticipantCapDao {
	private Logger log = Logger.getLogger(ParticipantCapDaoImpl.class);
	
	protected ParticipantCapDaoImpl() {
		super(ParticipantCap.class);
	}

	private static final String GET_BY_SEARCH = "ParticipantCap.getBySearch";
	private static final String COUNT_BY_SEARCH = "ParticipantCap.countBySearch";
	
	
	public List<ParticipantCap> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<ParticipantCap> result = query.list();
		if (result == null) {
			result = new ArrayList<ParticipantCap>();
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
	
	
	private static final String GET_BY_PARTICIPANT_ID_AND_CAP_ID = "ParticipantCap.getByParticipantIdAndCapId";
	
	public ParticipantCap getByParticipantIdAndCapId(Long participantId, Long capId) {
		if(participantId == null || capId == null)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_PARTICIPANT_ID_AND_CAP_ID);
		query.setLong("participantId", participantId);
		query.setLong("capId", capId);
		
		query.setMaxResults(1);
		
		List<ParticipantCap> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
