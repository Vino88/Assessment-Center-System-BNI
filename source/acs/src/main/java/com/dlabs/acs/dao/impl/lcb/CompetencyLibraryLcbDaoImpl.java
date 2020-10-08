package com.dlabs.acs.dao.impl.lcb;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.lcb.ICompetencyLibraryLcbDao;
import com.dlabs.acs.entity.lcb.CompetencyLibraryLcb;

@Repository
public class CompetencyLibraryLcbDaoImpl extends AbstractDaoImpl<CompetencyLibraryLcb, Long> implements ICompetencyLibraryLcbDao {
	private Logger log = Logger.getLogger(CompetencyLibraryLcbDaoImpl.class);
	
	protected CompetencyLibraryLcbDaoImpl() {
		super(CompetencyLibraryLcb.class);
	}

	private static final String GET_BY_SEARCH = "CompetencyLibraryLcb.getBySearch";
	private static final String COUNT_BY_SEARCH = "CompetencyLibraryLcb.countBySearch";
	
	
	public List<CompetencyLibraryLcb> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<CompetencyLibraryLcb> result = query.list();
		if (result == null) {
			result = new ArrayList<CompetencyLibraryLcb>();
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
	
	private static final String GET_BY_COMPETENCYID_AND_LCBID = "CompetencyLibraryLcb.getByCompetencyIdAndLcbId";
	public CompetencyLibraryLcb getByCompetencyIdAndLcbId(Long competencyId, Long lcbId) {
		if(competencyId == null || lcbId == null || lcbId.intValue() < 0 || competencyId.intValue() < 0)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_COMPETENCYID_AND_LCBID);
		query.setLong("competencyId", competencyId);
		query.setLong("lcbId", lcbId);
		
		query.setMaxResults(1);
		
		List<CompetencyLibraryLcb> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
