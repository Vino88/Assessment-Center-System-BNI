package com.dlabs.acs.dao.impl.inbasket;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.inbasket.ICompetencyLibraryInbasketInboxInbasketQuestionDao;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.inbasket.CompetencyLibraryInbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;

@Repository
public class CompetencyLibraryInbasketInboxInbasketQuestionDaoImpl extends AbstractDaoImpl<CompetencyLibraryInbasketInboxInbasketQuestion, Long> implements ICompetencyLibraryInbasketInboxInbasketQuestionDao {
	private Logger log = Logger.getLogger(CompetencyLibraryInbasketInboxInbasketQuestionDaoImpl.class);
	
	protected CompetencyLibraryInbasketInboxInbasketQuestionDaoImpl() {
		super(CompetencyLibraryInbasketInboxInbasketQuestion.class);
	}

	private static final String GET_BY_SEARCH = "CompetencyLibraryInbasketInboxInbasketQuestion.getBySearch";
	private static final String COUNT_BY_SEARCH = "CompetencyLibraryInbasketInboxInbasketQuestion.countBySearch";
	
	
	public List<CompetencyLibraryInbasketInboxInbasketQuestion> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<CompetencyLibraryInbasketInboxInbasketQuestion> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibraryInbasketInboxInbasketQuestion>();
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
	
	
	private static final String GET_DISTINCT_COMPETENCY_LIBRARY = "CompetencyLibraryInbasketInboxInbasketQuestion.getDistinctCompetencyLibrary";
	
	public List<CompetencyLibrary> getDistinctCompetencyLibrary() {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_DISTINCT_COMPETENCY_LIBRARY);
		
		
		List<CompetencyLibrary> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibrary>();
		}
		return result;
	}
	
	
	private static final String GET_IIIQ_BY_COMPETENCY_ID = "CompetencyLibraryInbasketInboxInbasketQuestion.getIiiqByCompetencyId";
	public List<InbasketInboxInbasketQuestion> getIiiqByCompetencyId(Long competencyLibraryId) {
		if(competencyLibraryId == null )
		{
			return java.util.Collections.EMPTY_LIST;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_IIIQ_BY_COMPETENCY_ID);
		
		query.setLong("competencyLibraryId", competencyLibraryId);
		
		List<InbasketInboxInbasketQuestion> result = query.list();
		if (result == null) {
			result = new ArrayList<InbasketInboxInbasketQuestion>();
		}
		return result;
	}
}
