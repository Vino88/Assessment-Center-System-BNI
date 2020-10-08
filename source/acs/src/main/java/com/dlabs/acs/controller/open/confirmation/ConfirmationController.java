package com.dlabs.acs.controller.open.confirmation;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dlabs.acs.entity.assessement.ParticipantLogPre;
import com.dlabs.acs.service.intf.assessement.IParticipantLogPreService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;

@Controller
@RequestMapping("/pages/open/confirmation/confirmation")
public class ConfirmationController {
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantLogPreService participantLogPreService;
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Model model,@PathVariable("id") Long id, HttpServletRequest httpRequest) {
		model.addAttribute("id", id);
		return "confirmation-view";
	}
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String confirm(@RequestParam("id") Long id, @RequestParam("answer") String answer, @RequestParam("notes") String notes,  Model model, HttpServletRequest httpRequest) {
		ParticipantLogPre participantLogPre = participantLogPreService.getByParticipantIdFirst(id);
		
		participantLogPre.setConfirmationTime(new Date());
		participantLogPre.setConfirmationAgree(Boolean.parseBoolean(answer));
		
		if(! participantLogPre.isConfirmationAgree())
		{
			participantLogPre.setConfirmationRejectNotes(notes);
		}
		
		participantLogPreService.save(participantLogPre);
		
		return "confirmation-view";
	}
}
