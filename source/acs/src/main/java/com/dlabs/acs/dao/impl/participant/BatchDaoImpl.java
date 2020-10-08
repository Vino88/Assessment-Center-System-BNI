package com.dlabs.acs.dao.impl.participant;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.participant.IBatchDao;
import com.dlabs.acs.entity.participant.Batch;

@Repository
public class BatchDaoImpl extends AbstractDaoImpl<Batch, Long> implements IBatchDao {
	private Logger log = Logger.getLogger(BatchDaoImpl.class);
	
	protected BatchDaoImpl() {
		super(Batch.class);
	}

	private static final String GET_BY_SEARCH = "Batch.getBySearch";
	private static final String COUNT_BY_SEARCH = "Batch.countBySearch";
	
	
	public List<Batch> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Batch> result = query.list();
		if (result == null) {
			result = new ArrayList<Batch>();
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
	
	
	
	private static final String GET_INPROGRESS_BY_SEARCH = "Batch.getInprogressBySearch";
	private static final String COUNT_INPROGRESS_BY_SEARCH = "Batch.countInprogressBySearch";
	
	
	public List<Batch> getInprogressBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_INPROGRESS_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Batch> result = query.list();
		if (result == null) {
			result = new ArrayList<Batch>();
		}
		return result;
	}
	
	public Long countInprogressBySearch(String search) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_INPROGRESS_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");

		List<Long> result = query.list();
		
		return result.get(0);
	}
	
	
	
	private static final String GET_INPROGRESS_BY_USERADMINID_BY_SEARCH = "Batch.getInprogressByUserAdminIdBySearch";
	private static final String COUNT_INPROGRESS_BY_USERADMINID_BY_SEARCH = "Batch.countInprogressByUserAdminIdBySearch";
	
	
	public List<Batch> getInprogressByUserAdminIdBySearch(Long userAdminId, String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_INPROGRESS_BY_USERADMINID_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setLong("userAdminId", userAdminId);
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Batch> result = query.list();
		if (result == null) {
			result = new ArrayList<Batch>();
		}
		return result;
	}
	
	public Long countInprogressByUserAdminIdBySearch(Long userAdminId, String search) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_INPROGRESS_BY_USERADMINID_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setLong("userAdminId", userAdminId);

		List<Long> result = query.list();
		
		return result.get(0);
	}
}
