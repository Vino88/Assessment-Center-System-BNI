package com.dlabs.acs.controller.admin.assessor.home;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pages/admin/assessor/home")
public class AssessorHomeController {
	private Logger logger =Logger.getLogger(AssessorHomeController.class);
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		
		logger.debug("END");
		return "assessor-home";
	}
}
