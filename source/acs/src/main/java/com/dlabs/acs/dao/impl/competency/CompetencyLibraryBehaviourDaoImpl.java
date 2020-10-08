package com.dlabs.acs.dao.impl.competency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.competency.ICompetencyLibraryBehaviourDao;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;

@Repository
public class CompetencyLibraryBehaviourDaoImpl extends AbstractDaoImpl<CompetencyLibraryBehaviour, Long> implements ICompetencyLibraryBehaviourDao {
	private Logger log = Logger.getLogger(CompetencyLibraryBehaviourDaoImpl.class);
	
	protected CompetencyLibraryBehaviourDaoImpl() {
		super(CompetencyLibraryBehaviour.class);
	}

	private static final String GET_BY_SEARCH = "CompetencyLibraryBehaviour.getBySearch";
	private static final String COUNT_BY_SEARCH = "CompetencyLibraryBehaviour.countBySearch";
	
	
	public List<CompetencyLibraryBehaviour> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<CompetencyLibraryBehaviour> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibraryBehaviour>();
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
	
	
	private static final String GET_BY_COMPETENCY_LIBRARY_ID = "CompetencyLibraryBehaviour.getByCompetencyLibraryId";
	public List<CompetencyLibraryBehaviour> getByCompetencyLibraryId(Long competencyLibraryId) {
		if(competencyLibraryId == null )
		{
			return Collections.EMPTY_LIST;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_COMPETENCY_LIBRARY_ID);
		query.setLong("competencyLibraryId", competencyLibraryId);
		
		List<CompetencyLibraryBehaviour> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibraryBehaviour>();
		}
		return result;
	}
}
