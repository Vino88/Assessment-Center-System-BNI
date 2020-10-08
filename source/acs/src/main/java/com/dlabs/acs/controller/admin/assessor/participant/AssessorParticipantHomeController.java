package com.dlabs.acs.controller.admin.assessor.participant;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.participant.ParticipantLogPost;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;

@Controller
@RequestMapping("/pages/admin/assessor/participant/home")
public class AssessorParticipantHomeController {
	private Logger logger =Logger.getLogger(AssessorParticipantHomeController.class);
	
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantLogPostService participantLogPostService;
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		ParticipantLogPost participantLogPost = participantLogPostService.getByParticipantIdFirst(participant.getId());
		model.addAttribute("participantLogPost", participantLogPost);
		logger.debug("END");
		return "/pages/admin/assessor/participant/home/view";
	}
}
