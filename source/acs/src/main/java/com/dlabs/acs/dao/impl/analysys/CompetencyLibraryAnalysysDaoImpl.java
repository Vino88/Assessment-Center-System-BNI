package com.dlabs.acs.dao.impl.analysys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.analysys.ICompetencyLibraryAnalysysDao;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.CompetencyLibraryAnalysys;
import com.dlabs.acs.entity.competency.CompetencyLibrary;

@Repository
public class CompetencyLibraryAnalysysDaoImpl extends AbstractDaoImpl<CompetencyLibraryAnalysys, Long> implements ICompetencyLibraryAnalysysDao {
	private Logger log = Logger.getLogger(CompetencyLibraryAnalysysDaoImpl.class);
	
	protected CompetencyLibraryAnalysysDaoImpl() {
		super(CompetencyLibraryAnalysys.class);
	}

	private static final String GET_BY_SEARCH = "CompetencyLibraryAnalysys.getBySearch";
	private static final String COUNT_BY_SEARCH = "CompetencyLibraryAnalysys.countBySearch";
	
	
	public List<CompetencyLibraryAnalysys> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<CompetencyLibraryAnalysys> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibraryAnalysys>();
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
	
	
	
	
	
	private static final String GET_DISTINCT_COMPETENCY_LIBRARY = "CompetencyLibraryAnalysys.getDistinctCompetencyLibrary";

	public List<CompetencyLibrary> getDistinctCompetencyLibrary() {
	
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_DISTINCT_COMPETENCY_LIBRARY);
		
		List<CompetencyLibrary> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibrary>();
		}
		return result;
	}
	
	private static final String GET_ANALYSIS_BY_COMPETENCY_LIBRARY_ID = "CompetencyLibraryAnalysys.getAnalysisByCompetencyId";

	public List<Analysys> getAnalysisByCompetencyId(Long competencyLibraryId) {
	
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_ANALYSIS_BY_COMPETENCY_LIBRARY_ID);
		query.setLong("competencyLibraryId", competencyLibraryId);

		
		List<Analysys> result = query.list();
		if (result == null || result.size() == 0) {
			return Collections.EMPTY_LIST;
		}
		return result;
	}
}
