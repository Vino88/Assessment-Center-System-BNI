package com.dlabs.acs.dao.impl.competency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.competency.ICompetencyLibraryWritingAssistanceDao;
import com.dlabs.acs.entity.competency.CompetencyLibraryWritingAssistance;

@Repository
public class CompetencyLibraryWritingAssistanceDaoImpl extends AbstractDaoImpl<CompetencyLibraryWritingAssistance, Long> implements ICompetencyLibraryWritingAssistanceDao {
	private Logger log = Logger.getLogger(CompetencyLibraryWritingAssistanceDaoImpl.class);
	
	protected CompetencyLibraryWritingAssistanceDaoImpl() {
		super(CompetencyLibraryWritingAssistance.class);
	}

	private static final String GET_BY_SEARCH = "CompetencyLibraryWritingAssistance.getBySearch";
	private static final String COUNT_BY_SEARCH = "CompetencyLibraryWritingAssistance.countBySearch";
	
	
	public List<CompetencyLibraryWritingAssistance> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<CompetencyLibraryWritingAssistance> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibraryWritingAssistance>();
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
	
	
	
	private static final String COUNT_BY_COMPETENCY_LIBRARY_ID = "CompetencyLibraryWritingAssistance.countByCompetencyLibraryId";
	public Long countByCompetencyLibraryId(Long competencyLibraryId) {
		if(competencyLibraryId == null )
		{
			return 0L;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_BY_COMPETENCY_LIBRARY_ID);
		query.setLong("competencyLibraryId", competencyLibraryId);

		List<Long> result = query.list();
		
		return result.get(0);
	}
	
	
	private static final String GET_BY_COMPETENCY_LIBRARY_ID = "CompetencyLibraryWritingAssistance.getByCompetencyLibraryId";
	public List<CompetencyLibraryWritingAssistance> getByCompetencyLibraryId(Long competencyLibraryId, int start, int num) {
		if(competencyLibraryId == null )
		{
			return Collections.EMPTY_LIST;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_COMPETENCY_LIBRARY_ID);
		query.setLong("competencyLibraryId", competencyLibraryId);
		
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<CompetencyLibraryWritingAssistance> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibraryWritingAssistance>();
		}
		return result;
	}
	
	
	
	private static final String COUNT_BY_LEVEL = "CompetencyLibraryWritingAssistance.countByLevel";
	public Long countByLevel(int level) {
		
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_BY_LEVEL);
		query.setInteger("level", level);

		List<Long> result = query.list();
		
		return result.get(0);
	}
	
	
	private static final String GET_BY_LEVEL = "CompetencyLibraryWritingAssistance.getByLevel";
	public List<CompetencyLibraryWritingAssistance> getByLevel(int level, int start, int num) {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_LEVEL);
		query.setInteger("level", level);
		
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<CompetencyLibraryWritingAssistance> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibraryWritingAssistance>();
		}
		return result;
	}
	
	
	private static final String COUNT_BY_COMPETENCYLIBRARYID_AND_LEVEL = "CompetencyLibraryWritingAssistance.countByCompetencyLibraryIdAndLevel";
	public Long countByCompetencyLibraryIdAndLevel(Long competencyLibraryId, int level) {
		if(competencyLibraryId == null )
		{
			return 0L;
		}
		
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_BY_COMPETENCYLIBRARYID_AND_LEVEL);
		query.setInteger("level", level);
		query.setLong("competencyLibraryId", competencyLibraryId);

		List<Long> result = query.list();
		
		return result.get(0);
	}
	
	private static final String GET_BY_COMPETENCY_LIBRARY_ID_AND_LEVEL = "CompetencyLibraryWritingAssistance.getByCompetencyLibraryIdAndLevel";
	public List<CompetencyLibraryWritingAssistance> getByCompetencyLibraryIdAndLevel(Long competencyLibraryId, int level, int start, int num) {
		if(competencyLibraryId == null )
		{
			return Collections.EMPTY_LIST;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_COMPETENCY_LIBRARY_ID_AND_LEVEL);
		query.setLong("competencyLibraryId", competencyLibraryId);
		query.setInteger("level", level);
		
		List<CompetencyLibraryWritingAssistance> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibraryWritingAssistance>();
		}
		return result;
	}
}
