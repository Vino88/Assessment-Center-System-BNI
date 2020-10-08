package com.dlabs.acs.service.intf;

import java.util.List;

import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.lcb.enumeration.Role;
import com.dlabs.acs.service.IService;

public interface IUserAdminService extends IService<UserAdmin, Long>
{
	public List<UserAdmin> getBySearch(String search, int start, int num);
	public Long countBySearch(String search);
	
	public List<UserAdmin> getByRoleAndUsername(Role role, String username, int start, int num);
	public Long countByRoleAndUsername(Role role, String username);
	
	public boolean login(String username, String password);
	
	UserAdmin getByUsername(String username);
	
	public UserAdmin getByEmail(String email);
	
	public Long countAssessorNumberOfTask( String search);
	public List getAssessorNumberOfTask( String search, int start, int num);
	
	public int executeUpdate(String sql);
}
