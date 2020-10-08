package com.dlabs.acs.dto.credential;

import com.dlabs.acs.dto.DataFormDto;
import com.dlabs.acs.entity.lcb.enumeration.Role;

public class UserAdminDto extends DataFormDto{
	private String username;
	
	private String fullname;
	
	private String email;
	
	private String password;

	private Role role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}