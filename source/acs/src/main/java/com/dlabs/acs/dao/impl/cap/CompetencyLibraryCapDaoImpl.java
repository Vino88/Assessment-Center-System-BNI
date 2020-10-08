package com.dlabs.acs.dao.impl.cap;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.cap.ICompetencyLibraryCapDao;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.entity.cap.CompetencyLibraryCap;
import com.dlabs.acs.entity.competency.CompetencyLibrary;

@Repository
public class CompetencyLibraryCapDaoImpl extends AbstractDaoImpl<CompetencyLibraryCap, Long> implements ICompetencyLibraryCapDao {
	private Logger log = Logger.getLogger(CompetencyLibraryCapDaoImpl.class);
	
	protected CompetencyLibraryCapDaoImpl() {
		super(CompetencyLibraryCap.class);
	}

	private static final String GET_BY_SEARCH = "CompetencyLibraryCap.getBySearch";
	private static final String COUNT_BY_SEARCH = "CompetencyLibraryCap.countBySearch";
	
	
	public List<CompetencyLibraryCap> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<CompetencyLibraryCap> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibraryCap>();
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
	
	

	private static final String GET_DISTINCT_COMPETENCY_LIBRARY = "CompetencyLibraryCap.getDistinctCompetencyLibrary";

	public List<CompetencyLibrary> getDistinctCompetencyLibrary() {
	
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_DISTINCT_COMPETENCY_LIBRARY);
		
		List<CompetencyLibrary> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibrary>();
		}
		return result;
	}
	
	private static final String GET_CAP_BY_COMPETENCY_LIBRARY_ID = "CompetencyLibraryCap.getCapByCompetencyId";

	public Cap getCapByCompetencyId(Long competencyLibraryId) {
	
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_CAP_BY_COMPETENCY_LIBRARY_ID);
		query.setLong("competencyLibraryId", competencyLibraryId);
		
		query.setMaxResults(1);
		
		List<Cap> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	
	private static final String GET_COMPETENCIES_BY_CAP_ID = "CompetencyLibraryCap.getCompetenciesByCapId";

	public List<CompetencyLibrary> getCompetenciesByCapId(Long capId) {
	
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_COMPETENCIES_BY_CAP_ID);
		query.setLong("capId", capId);
		
		List<CompetencyLibrary> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibrary>();
		}
		return result;
	}
}
