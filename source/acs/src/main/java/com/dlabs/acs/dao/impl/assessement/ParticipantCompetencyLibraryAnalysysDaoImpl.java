package com.dlabs.acs.dao.impl.assessement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.assessement.IParticipantCompetencyLibraryAnalysysDao;
import com.dlabs.acs.entity.assessement.ParticipantCompetencyLibraryAnalysys;

@Repository
public class ParticipantCompetencyLibraryAnalysysDaoImpl extends AbstractDaoImpl<ParticipantCompetencyLibraryAnalysys, Long> implements IParticipantCompetencyLibraryAnalysysDao {
	private Logger log = Logger.getLogger(ParticipantCompetencyLibraryAnalysysDaoImpl.class);
	
	protected ParticipantCompetencyLibraryAnalysysDaoImpl() {
		super(ParticipantCompetencyLibraryAnalysys.class);
	}

	private static final String GET_BY_SEARCH = "ParticipantCompetencyLibraryAnalysys.getBySearch";
	private static final String COUNT_BY_SEARCH = "ParticipantCompetencyLibraryAnalysys.countBySearch";
	
	
	public List<ParticipantCompetencyLibraryAnalysys> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<ParticipantCompetencyLibraryAnalysys> result = query.list();
		if (result == null) {
			result = new ArrayList<ParticipantCompetencyLibraryAnalysys>();
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
	
	private static final String GET_BY_PARTID_AND_CLAID = "ParticipantCompetencyLibraryAnalysys.getByPartIdAndClaId";
	public ParticipantCompetencyLibraryAnalysys getByPartIdAndClaId(Long participantId, Long claId) {
		if(participantId == null || claId == null)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_PARTID_AND_CLAID);
		query.setLong("participantId", participantId);
		query.setLong("claId", claId);
		query.setMaxResults(1);
		List<ParticipantCompetencyLibraryAnalysys> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
