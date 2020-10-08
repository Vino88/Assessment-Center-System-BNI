package com.dlabs.acs.dao.impl.assessementresult;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.assessementresult.IParticipantLcbCompetencyLibraryLcbDao;
import com.dlabs.acs.entity.assessementresult.ParticipantLcbCompetencyLibraryLcb;

@Repository
public class ParticipantLcbCompetencyLibraryLcbDaoImpl extends AbstractDaoImpl<ParticipantLcbCompetencyLibraryLcb, Long> implements IParticipantLcbCompetencyLibraryLcbDao {
	private Logger log = Logger.getLogger(ParticipantLcbCompetencyLibraryLcbDaoImpl.class);
	
	protected ParticipantLcbCompetencyLibraryLcbDaoImpl() {
		super(ParticipantLcbCompetencyLibraryLcb.class);
	}

	private static final String GET_BY_SEARCH = "ParticipantLcbCompetencyLibraryLcb.getBySearch";
	private static final String COUNT_BY_SEARCH = "ParticipantLcbCompetencyLibraryLcb.countBySearch";
	
	
	public List<ParticipantLcbCompetencyLibraryLcb> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<ParticipantLcbCompetencyLibraryLcb> result = query.list();
		if (result == null) {
			result = new ArrayList<ParticipantLcbCompetencyLibraryLcb>();
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
