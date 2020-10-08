package com.dlabs.acs.dao.impl.inbasket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.inbasket.IInbasketInboxDao;
import com.dlabs.acs.entity.inbasket.InbasketInbox;
import com.dlabs.acs.entity.inbasket.enumeration.InboxType;

@Repository
public class InbasketInboxDaoImpl extends AbstractDaoImpl<InbasketInbox, Long> implements IInbasketInboxDao {
	private Logger log = Logger.getLogger(InbasketInboxDaoImpl.class);
	
	protected InbasketInboxDaoImpl() {
		super(InbasketInbox.class);
	}

	private static final String GET_BY_SEARCH = "InbasketInbox.getBySearch";
	private static final String COUNT_BY_SEARCH = "InbasketInbox.countBySearch";
	
	
	public List<InbasketInbox> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<InbasketInbox> result = query.list();
		if (result == null) {
			result = new ArrayList<InbasketInbox>();
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
	
	private static final String GET_BY_QUESTION_NUMBER = "InbasketInbox.getByQuestNumber";
	public InbasketInbox getByQuestNumber(int number) {
		if(number < 0)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_QUESTION_NUMBER);
		query.setInteger("number",  number);
		
		query.setMaxResults(1);
		
		List<InbasketInbox> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	
	private static final String GET_BY_INBOX_TYPE = "InbasketInbox.getByInboxType";
	public List<InbasketInbox> getByInboxType(InboxType inboxType) {
		if(inboxType == null)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_INBOX_TYPE);
		query.setString("inboxType", inboxType.toString());
		
		List<InbasketInbox> result = query.list();
		if (result == null || result.size() == 0) {
			return Collections.EMPTY_LIST;
		}
		return result;
	}
}
