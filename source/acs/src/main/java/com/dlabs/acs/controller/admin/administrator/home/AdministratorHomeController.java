package com.dlabs.acs.controller.admin.administrator.home;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pages/admin/administrator/home")
public class AdministratorHomeController {
	private Logger logger =Logger.getLogger(AdministratorHomeController.class);
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		
		logger.debug("END");
		return "admin-home";
	}
}
