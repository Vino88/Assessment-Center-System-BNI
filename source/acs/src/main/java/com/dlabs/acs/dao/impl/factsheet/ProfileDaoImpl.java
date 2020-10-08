package com.dlabs.acs.dao.impl.factsheet;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.factsheet.IProfileDao;
import com.dlabs.acs.entity.factsheet.Profile;

@Repository
public class ProfileDaoImpl extends AbstractDaoImpl<Profile, Long> implements IProfileDao {
	private Logger log = Logger.getLogger(ProfileDaoImpl.class);
	
	protected ProfileDaoImpl() {
		super(Profile.class);
	}

	private static final String GET_BY_SEARCH = "Profile.getBySearch";
	private static final String COUNT_BY_SEARCH = "Profile.countBySearch";
	
	
	public List<Profile> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Profile> result = query.list();
		if (result == null) {
			result = new ArrayList<Profile>();
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
	
	private static final String GET_BY_KEY = "Profile.getByKey";
	public Profile getByKey(String key) {
		if(key == null || key.trim().length() == 0)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_KEY);
		query.setString("key", key);
		
		query.setMaxResults(1);
		
		List<Profile> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
