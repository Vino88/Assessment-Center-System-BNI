package com.dlabs.acs.dao.impl.lcb;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.lcb.ILcbDao;
import com.dlabs.acs.entity.lcb.Lcb;

@Repository
public class LcbDaoImpl extends AbstractDaoImpl<Lcb, Long> implements ILcbDao {
	private Logger log = Logger.getLogger(LcbDaoImpl.class);
	
	protected LcbDaoImpl() {
		super(Lcb.class);
	}

	private static final String GET_BY_SEARCH = "Lcb.getBySearch";
	private static final String COUNT_BY_SEARCH = "Lcb.countBySearch";
	
	
	public List<Lcb> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Lcb> result = query.list();
		if (result == null) {
			result = new ArrayList<Lcb>();
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
	
	
	private static final String GET_BY_QUESTION_NUMBER = "Lcb.getByQuestNumber";
	public Lcb getByQuestNumber(int number) {
		if(number < 0)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_QUESTION_NUMBER);
		query.setInteger("number",  number);
		
		query.setMaxResults(1);
		
		List<Lcb> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
