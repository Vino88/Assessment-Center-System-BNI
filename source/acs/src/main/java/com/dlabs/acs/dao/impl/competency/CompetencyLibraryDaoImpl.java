package com.dlabs.acs.dao.impl.competency;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.competency.ICompetencyLibraryDao;
import com.dlabs.acs.entity.competency.CompetencyLibrary;

@Repository
public class CompetencyLibraryDaoImpl extends AbstractDaoImpl<CompetencyLibrary, Long> implements ICompetencyLibraryDao {
	private Logger log = Logger.getLogger(CompetencyLibraryDaoImpl.class);
	
	protected CompetencyLibraryDaoImpl() {
		super(CompetencyLibrary.class);
	}

	private static final String GET_BY_SEARCH = "CompetencyLibrary.getBySearch";
	private static final String COUNT_BY_SEARCH = "CompetencyLibrary.countBySearch";
	
	
	public List<CompetencyLibrary> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<CompetencyLibrary> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibrary>();
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
	
	private static final String GET_BY_COMPETENCY_NAME = "CompetencyLibrary.getByCompetencyName";
	public CompetencyLibrary getByCompetencyName(String competencyName) {
		if(competencyName == null || competencyName.trim().length() == 0)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_COMPETENCY_NAME);
		query.setString("competencyName", competencyName.toLowerCase());
		
		query.setMaxResults(1);
		
		List<CompetencyLibrary> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	private static final String GET_ALL_ACTIVE = "CompetencyLibrary.getAllActive";
	@Override
	public List<CompetencyLibrary> getAllActive() {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_ALL_ACTIVE);
		
		List<CompetencyLibrary> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibrary>();
		}
		return result;
	}
}
