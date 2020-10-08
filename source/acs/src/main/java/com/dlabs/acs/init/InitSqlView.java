package com.dlabs.acs.init;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import com.dlabs.acs.service.intf.IUserAdminService;

@Configuration
public class InitSqlView {
	@Autowired
	private IUserAdminService userAdminService;
	
	@Autowired
	@Qualifier("sqlView")
	private ArrayList<String> sqlView;
	
	@PostConstruct
	public void initializeView()
	{
		for(String sql : sqlView)
		{
			userAdminService.executeUpdate(sql);
		}
	}
}
