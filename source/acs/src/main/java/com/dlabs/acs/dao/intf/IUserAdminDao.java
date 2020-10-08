package com.dlabs.acs.dao.intf;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.lcb.enumeration.Role;


public interface IUserAdminDao extends IAbstractDao<UserAdmin, Long>
{
	public List<UserAdmin> getBySearch(String search, int start, int num);
	public Long countBySearch(String search);
	
	public List<UserAdmin> getByRoleAndUsername(Role role, String username, int start, int num);
	public Long countByRoleAndUsername(Role role, String username);
	
	UserAdmin getByUsername(String username);
	public UserAdmin getByEmail(String email);
	
	boolean login(String username, String password);
	
	public Long countAssessorNumberOfTask( String search);
	public List getAssessorNumberOfTask( String search, int start, int num);
	public int executeUpdate(String sql);
}
