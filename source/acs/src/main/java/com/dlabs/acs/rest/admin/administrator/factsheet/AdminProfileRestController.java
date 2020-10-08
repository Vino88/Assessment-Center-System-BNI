package com.dlabs.acs.rest.admin.administrator.factsheet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.factsheet.IProfileService;

@RestController()
@RequestMapping("rest/admin/administrator/factsheet/profile")
public class AdminProfileRestController extends AbstractRest{
	private Logger log = Logger.getLogger(AdminProfileRestController.class);
	@Autowired
	private IProfileService profileService;
	
	
	
}
