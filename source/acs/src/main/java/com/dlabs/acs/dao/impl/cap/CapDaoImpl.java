package com.dlabs.acs.dao.impl.cap;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.cap.ICapDao;
import com.dlabs.acs.entity.cap.Cap;

@Repository
public class CapDaoImpl extends AbstractDaoImpl<Cap, Long> implements ICapDao {
	private Logger log = Logger.getLogger(CapDaoImpl.class);
	
	protected CapDaoImpl() {
		super(Cap.class);
	}

	private static final String GET_BY_SEARCH = "Cap.getBySearch";
	private static final String COUNT_BY_SEARCH = "Cap.countBySearch";
	
	
	public List<Cap> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Cap> result = query.list();
		if (result == null) {
			result = new ArrayList<Cap>();
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
	
	private static final String GET_BY_QUESTION_NUMBER = "Cap.getByQuestNumber";
	public Cap getByQuestNumber(int number) {
		if(number < 0)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_QUESTION_NUMBER);
		query.setInteger("number",  number);
		
		query.setMaxResults(1);
		
		List<Cap> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
