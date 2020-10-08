package com.dlabs.acs.controller.admin.assessor.participant;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pages/admin/assessor/participant/history")
public class AsessorParticipantHistoryController {
	private Logger logger =Logger.getLogger(AsessorParticipantHistoryController.class);
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		
		logger.debug("END");
		return "/pages/admin/assessor/participant/history/view";
	}
}
