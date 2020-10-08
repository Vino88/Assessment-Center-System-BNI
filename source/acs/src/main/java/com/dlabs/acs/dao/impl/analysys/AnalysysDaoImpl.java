package com.dlabs.acs.dao.impl.analysys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.analysys.IAnalysysDao;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.AnalysysType;

@Repository
public class AnalysysDaoImpl extends AbstractDaoImpl<Analysys, Long> implements IAnalysysDao {
	private Logger log = Logger.getLogger(AnalysysDaoImpl.class);
	
	protected AnalysysDaoImpl() {
		super(Analysys.class);
	}

	private static final String GET_BY_SEARCH = "Analysys.getBySearch";
	private static final String COUNT_BY_SEARCH = "Analysys.countBySearch";
	
	
	public List<Analysys> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Analysys> result = query.list();
		if (result == null) {
			result = new ArrayList<Analysys>();
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
	
	private static final String GET_BY_ANALYSYS_TYPE = "Analysys.getByAnalysysType";	
	
	public List<Analysys> getByAnalysysType(AnalysysType analysysType) {
		if(analysysType == null)
		{
			return Collections.EMPTY_LIST;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_ANALYSYS_TYPE);
		query.setString("analysysType", analysysType.toString());
		
		List<Analysys> result = query.list();
		if (result == null) {
			result = Collections.EMPTY_LIST;
		}
		return result;
	}
	
	
	private static final String GET_BY_QUESTION_NUMBER = "Analysys.getByQuestNumber";
	public Analysys getByQuestNumber(int number) {
		if(number < 0)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_QUESTION_NUMBER);
		query.setInteger("number",  number);
		
		query.setMaxResults(1);
		
		List<Analysys> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
