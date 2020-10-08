package com.dlabs.acs.controller.admin.administrator.assessement;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pages/admin/administrator/assessement/assessor")
public class AdminAssessementAssessorController {
	private Logger logger = Logger.getLogger(AdminAssessementAssessorController.class);
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		return "/pages/admin/administrator/assessement/assessor/view";
	}
}
