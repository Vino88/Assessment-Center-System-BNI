package com.dlabs.acs.controller.admin.assessor.participant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pages/admin/assessor/participant/reviewer")
public class AssessorParticipantReviewerController extends AssessorParticipantIntegrationGridController{
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id")Long id) {
		super.view(model, id);
		return "/pages/admin/assessor/participant/reviewer/view";
	}
}
