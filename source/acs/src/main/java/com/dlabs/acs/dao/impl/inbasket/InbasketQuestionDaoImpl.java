package com.dlabs.acs.dao.impl.inbasket;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.inbasket.IInbasketQuestionDao;
import com.dlabs.acs.entity.inbasket.InbasketQuestion;

@Repository
public class InbasketQuestionDaoImpl extends AbstractDaoImpl<InbasketQuestion, Long> implements IInbasketQuestionDao {
	private Logger log = Logger.getLogger(InbasketQuestionDaoImpl.class);
	
	protected InbasketQuestionDaoImpl() {
		super(InbasketQuestion.class);
	}

	private static final String GET_BY_SEARCH = "InbasketQuestion.getBySearch";
	private static final String COUNT_BY_SEARCH = "InbasketQuestion.countBySearch";
	
	
	public List<InbasketQuestion> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<InbasketQuestion> result = query.list();
		if (result == null) {
			result = new ArrayList<InbasketQuestion>();
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
	
	private static final String GET_BY_QUESTION_NUMBER = "InbasketQuestion.getByQuestNumber";
	public InbasketQuestion getByQuestNumber(int number) {
		if(number < 0)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_QUESTION_NUMBER);
		query.setInteger("number",  number);
		
		query.setMaxResults(1);
		
		List<InbasketQuestion> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
