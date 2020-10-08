package com.dlabs.acs.dao.impl.assessement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractParticipantDaoImpl;
import com.dlabs.acs.dao.intf.assessement.IParticipantLogDuringDao;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;

@Repository
public class ParticipantLogDuringDaoImpl extends AbstractParticipantDaoImpl<ParticipantLogDuring, Long> implements IParticipantLogDuringDao {
	private Logger log = Logger.getLogger(ParticipantLogDuringDaoImpl.class);
	
	protected ParticipantLogDuringDaoImpl() {
		super(ParticipantLogDuring.class);
	}

	private static final String GET_BY_SEARCH = "ParticipantLogDuring.getBySearch";
	private static final String COUNT_BY_SEARCH = "ParticipantLogDuring.countBySearch";
	
	
	public List<ParticipantLogDuring> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<ParticipantLogDuring> result = query.list();
		if (result == null) {
			result = new ArrayList<ParticipantLogDuring>();
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
}
