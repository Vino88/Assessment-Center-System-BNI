package com.dlabs.acs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.lcb.enumeration.Role;

@NamedQueries({ 
	@NamedQuery(name = "UserAdmin.getByUsername", query = "from UserAdmin ua where ua.commonFields.deleted = false AND ua.username = :username"),
	@NamedQuery(name = "UserAdmin.getByEmail", query = "from UserAdmin ua where ua.commonFields.deleted = false AND ua.email = :email"),
	@NamedQuery(name = "UserAdmin.login", query = "select count(*) from UserAdmin ua where ua.commonFields.deleted = false AND ua.username = :username and password=:password"),
	
	@NamedQuery(name = "UserAdmin.getByRoleAndUsername", query = "from UserAdmin ua where ua.commonFields.deleted = false AND ua.role = :role AND LOWER(ua.username) like :username ORDER BY ua.username ASC"),
	@NamedQuery(name = "UserAdmin.countByRoleAndUsername", query = "select count(*) from UserAdmin ua where ua.commonFields.deleted = false AND ua.role = :role AND LOWER(ua.username) like :username"),
	
	@NamedQuery(name="UserAdmin.getBySearch", query="FROM UserAdmin where commonFields.deleted = false AND ( LOWER(username) like :search OR LOWER(fullname) like :search OR LOWER(email) like :search  OR LOWER(role) like :search) order by id asc"),
	@NamedQuery(name="UserAdmin.countBySearch", query="select count(*) FROM UserAdmin where commonFields.deleted = false AND ( LOWER(username) like :search OR LOWER(fullname) like :search OR LOWER(email) like :search OR LOWER(role) like :search) "),
	})

	@NamedNativeQueries({
	@NamedNativeQuery(name="UserAdmin.countAssessorNumberOfTask", query="SELECT count(*) FROM vw_assessor_task_stat_summary v LEFT JOIN UserAdmin u ON u.id=v.adminId WHERE u.deleted=false AND LOWER (u.username) like :search"),
	@NamedNativeQuery(name="UserAdmin.getAssessorNumberOfTask", query="SELECT u.id, u.fullname, u.email, u.username, v.* FROM vw_assessor_task_stat_summary v LEFT JOIN UserAdmin u ON u.id=v.adminId WHERE u.deleted=false AND LOWER (u.username) like :search ORDER BY u.username ASC"),
	
	
})
@Entity
public class UserAdmin extends AbstractEntity
{
	@GeneratedValue
	@Id
	private Long id;

	private CommonFields commonFields;

	@Column(nullable = false)
	private String username;
	
	private String fullname;
	
	private String email;
	
	private String password;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean disabled = false;
	
	private int failedLoginAttempt = 0;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public CommonFields getCommonFields()
	{
		return commonFields;
	}

	public void setCommonFields(CommonFields commonFields)
	{
		this.commonFields = commonFields;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public int getFailedLoginAttempt() {
		return failedLoginAttempt;
	}

	public void setFailedLoginAttempt(int failedLoginAttempt) {
		this.failedLoginAttempt = failedLoginAttempt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserAdmin [id=" + id + ", commonFields=" + commonFields + ", username=" + username + ", fullname="
				+ fullname + ", email=" + email + ", password=" + password + ", role=" + role + ", disabled=" + disabled
				+ ", failedLoginAttempt=" + failedLoginAttempt + "]";
	}
	
	

}
