package com.dlabs.acs.dao.impl.inbasket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.inbasket.IInbasketInboxInbasketQuestionDao;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;

@Repository
public class InbasketInboxInbasketQuestionDaoImpl extends AbstractDaoImpl<InbasketInboxInbasketQuestion, Long> implements IInbasketInboxInbasketQuestionDao {
	private Logger log = Logger.getLogger(InbasketInboxInbasketQuestionDaoImpl.class);
	
	protected InbasketInboxInbasketQuestionDaoImpl() {
		super(InbasketInboxInbasketQuestion.class);
	}

	private static final String GET_BY_SEARCH = "InbasketInboxInbasketQuestion.getBySearch";
	private static final String COUNT_BY_SEARCH = "InbasketInboxInbasketQuestion.countBySearch";
	
	
	public List<InbasketInboxInbasketQuestion> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<InbasketInboxInbasketQuestion> result = query.list();
		if (result == null) {
			result = new ArrayList<InbasketInboxInbasketQuestion>();
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
	
	
	private static final String GET_BY_INBOXQNUM_AND_QUESTQNUM = "InbasketInboxInbasketQuestion.getByInboxQnumAndQuestionQnum";
	public InbasketInboxInbasketQuestion getByInboxQnumAndQuestionQnum(int inboxQnum, int questionQnum) {
		if(inboxQnum < 0 || questionQnum < 0)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_INBOXQNUM_AND_QUESTQNUM);
		query.setInteger("inboxNumber",  inboxQnum);
		query.setInteger("questionNumber",  questionQnum);
		
		query.setMaxResults(1);
		
		List<InbasketInboxInbasketQuestion> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	private static final String GET_BY_INBOXID = "InbasketInboxInbasketQuestion.getByInboxId";
	public List<InbasketInboxInbasketQuestion> getByInboxId(Long inboxId) {
		if(inboxId == null)
		{
			return Collections.EMPTY_LIST;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_INBOXID);
		query.setLong("inboxId", inboxId);
	
		List<InbasketInboxInbasketQuestion> result = query.list();
		if (result == null) {
			result = new ArrayList<InbasketInboxInbasketQuestion>();
		}
		return result;
	}
}
