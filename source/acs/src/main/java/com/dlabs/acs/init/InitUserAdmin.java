package com.dlabs.acs.init;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.service.intf.IUserAdminService;
import com.dlabs.acs.util.excel.ExcelUserAdmin;

@Component
public class InitUserAdmin {
	@Autowired
	private IUserAdminService userAdminService;
	
	public void generate(){
		if( userAdminService.count() == 0)
		{
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/UserAdmin.xlsx");
			
			List<UserAdmin> list = ExcelUserAdmin.parse(is);
			
			userAdminService.save(list);
		}
	}
}
