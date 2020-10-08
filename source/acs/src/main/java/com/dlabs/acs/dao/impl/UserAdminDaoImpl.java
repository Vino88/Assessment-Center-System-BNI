package com.dlabs.acs.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.intf.IUserAdminDao;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.lcb.enumeration.Role;


@Repository
public class UserAdminDaoImpl extends AbstractDaoImpl<UserAdmin, Long> implements IUserAdminDao
{
	protected UserAdminDaoImpl() {
		super(UserAdmin.class);
	}
	
	private static final String GET_BY_SEARCH = "UserAdmin.getBySearch";
	private static final String COUNT_BY_SEARCH = "UserAdmin.countBySearch";
	
	
	public List<UserAdmin> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<UserAdmin> result = query.list();
		if (result == null) {
			result = new ArrayList<UserAdmin>();
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
	
	
	
	private static final String GET_BY_ROLE_AND_USERNAME = "UserAdmin.getByRoleAndUsername";
	@Override
	public List<UserAdmin> getByRoleAndUsername(Role role, String username, int start, int num)
	{
		if(username == null || username.trim().length() == 0)
		{
			username="";
		}
		Query query = getCurrentSession().getNamedQuery(GET_BY_ROLE_AND_USERNAME);
		query.setString("role", role.toString());
		query.setString("username", "%" + username.toLowerCase() + "%");
		
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<UserAdmin> list = query.list();
		if(list == null)
		{
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
	
	private static final String COUNT_BY_ROLE_AND_USERNAME = "UserAdmin.countByRoleAndUsername";
	@Override
	public Long countByRoleAndUsername(Role role, String username) {
		if(username == null || username.trim().length() == 0)
		{
			username="";
		}
		
		Query query = getCurrentSession().getNamedQuery(COUNT_BY_ROLE_AND_USERNAME);
		query.setString("role", role.toString());
		query.setString("username", "%" + username.toLowerCase() + "%");
		
		List<Long> result = query.list();
		
		return result.get(0);
	}

	@Override
	public UserAdmin getByUsername(String username)
	{
		Query query = getCurrentSession().getNamedQuery("UserAdmin.getByUsername");
		query.setString("username", username);
		
		query.setMaxResults(1);
		List<UserAdmin> list = query.list();
		if(list == null || list.size() == 0)
		{
			return null;
		}
		
		
		return list.get(0);
	}
	
	
	@Override
	public UserAdmin getByEmail(String email)
	{
		Query query = getCurrentSession().getNamedQuery("UserAdmin.getByEmail");
		query.setString("email", email);
		UserAdmin userAdmin = (UserAdmin) query.uniqueResult();
		return userAdmin;
	}
	
	
	private static final String COUNT_ASSESSOR_NUMBER_OF_TASK = "UserAdmin.countAssessorNumberOfTask";
	@Override
	public Long countAssessorNumberOfTask( String search) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		
		Query query = getCurrentSession().getNamedQuery(COUNT_ASSESSOR_NUMBER_OF_TASK);
		
		query.setString("search", "%" + search.toLowerCase() + "%");
		
		List<BigInteger> result = query.list();
		
		return result.get(0).longValue();
	}
	@Override
	public List getAssessorNumberOfTask( String search, int start, int num)
	{
		Query query = getCurrentSession().getNamedQuery("UserAdmin.getAssessorNumberOfTask");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if(search == null)
		{
			search="";
		}
		query.setString("search", "%" + search.toLowerCase() + "%");
		
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Map<String,Object>> list = query.list();
		
		if(list == null)
		{
			list =Collections.EMPTY_LIST;
		}
		return list;
	}

	@Override
	public boolean login(String username, String password) {
		Query query = getCurrentSession().getNamedQuery("UserAdmin.login");
		query.setString("username", username);
		query.setString("password", password);
		Long count = (Long) query.uniqueResult();
		
		return count.intValue() > 0 ;
	}
	
	
	public int executeUpdate(String sql)
	{
		Query query = getCurrentSession().createSQLQuery(sql);
		return query.executeUpdate();
	}
}
