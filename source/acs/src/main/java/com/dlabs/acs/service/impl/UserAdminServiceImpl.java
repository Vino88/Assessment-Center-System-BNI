package com.dlabs.acs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.IUserAdminDao;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.lcb.enumeration.Role;
import com.dlabs.acs.service.intf.IUserAdminService;

@Service()
@Transactional(readOnly = true)
public class UserAdminServiceImpl extends AbstractServiceImpl<UserAdmin, Long> implements IUserAdminService
{
	@Autowired
	private IUserAdminDao userAdminDao;
	
	@Override
	public IAbstractDao<UserAdmin, Long> getDao() {
		return userAdminDao;
	}

	@Override
	public List<UserAdmin> getBySearch(String search, int start, int num) {
		// TODO Auto-generated method stub
		return userAdminDao.getBySearch(search, start, num);
	}


	@Override
	public Long countBySearch(String search) {
		// TODO Auto-generated method stub
		return userAdminDao.countBySearch(search);
	}
	
	
	public List<UserAdmin> getByRoleAndUsername(Role role, String username, int start, int num) {
		return userAdminDao.getByRoleAndUsername(role, username, start, num);
	}
	
	public Long countByRoleAndUsername(Role role, String username){
		return userAdminDao.countByRoleAndUsername(role, username);
	}
	
	public boolean login(String username, String password)
	{
		return userAdminDao.login(username, password);
	}

	@Override
	public UserAdmin getByUsername(String username)
	{
		return userAdminDao.getByUsername(username);
	}
	public UserAdmin getByEmail(String email)
	{
		return userAdminDao.getByEmail(email);
	}
	
	
	public Long countAssessorNumberOfTask( String search)
	{
		return userAdminDao.countAssessorNumberOfTask(search);
	}
	public List getAssessorNumberOfTask( String search, int start, int num)
	{
		return userAdminDao.getAssessorNumberOfTask(search, start, num);
	}
	
	@Transactional(readOnly = false)
	public int executeUpdate(String sql){
		return userAdminDao.executeUpdate(sql);
	}

}
