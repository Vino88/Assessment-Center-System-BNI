package com.dlabs.acs.controller.view;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.enumeration.LogDuringStatus;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.factsheet.IProfileService;
import com.dlabs.acs.service.intf.masterdata.IEmployeeService;

@Controller
@RequestMapping("/pages/view/factsheet")
public class FactsheetController {
	private Logger logger =Logger.getLogger(FactsheetController.class);
	
	@Autowired
	private IParticipantService participantService;
	
	@Autowired
	private IProfileService profileService;
	
	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		Profile profile = participantService.getProfileByParticipantId(participant.getId());
		System.out.println("PROFILE :: " + profile.getCareerAspiration());
		model.addAttribute("profile", profile);
		
		Employee employee =employeeService.getByNik(participant.getNik());
		model.addAttribute("employee", employee);
		
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		if(participantLogDuring.getFactsheetStartTime() == null)
		{
			participantLogDuring.setFactsheetStartTime(new Date());
			participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
			participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
			
			participantLogDuringService.saveOrUpdate(participantLogDuring);
			httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		}
		
		logger.debug("END");
		return "factsheet-view";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String post(Model model, HttpServletRequest httpRequest, final RedirectAttributes redirectAttributes) {
		logger.debug("BEGIN");
		ParticipantLogDuring participantLogDuring = null;
		try
		{
			logger.debug("FactSheetController:doPost:BEGIN");
			String email = (String) httpRequest.getParameter("participantEmail");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		participant.setEmail(email);
		participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantService.saveOrUpdate(participant);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT, participant);
		
		Profile profile = participantService.getProfileByParticipantId(participant.getId());
		
		profile.setWorkingArea((String) httpRequest.getParameter("workingArea"));
		profile.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		profileService.saveOrUpdate(profile);
		
		/** Employee Master Data - Phone Number, Main Responsibility */
		Employee employee =employeeService.getByNik(participant.getNik());
		String phoneNumber = (String) httpRequest.getParameter("participantPhone");
		
		employee.setPhone(phoneNumber);
		employee.getCommonFields().setLastModifiedDate(new Date());
		
		employee.setEmail(email);
		employeeService.saveOrUpdate(employee);
		
		participantLogDuring.setLogDuringStatus(LogDuringStatus.FACTSHEET);
		participantLogDuring.setFactsheetLastDraftTime(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		logger.debug("END");
		
		
		logger.debug("FactSheetController:doPost:END");
		redirectAttributes.addAttribute("draftSuccessMessage", "Successfully Saved");
		}catch(Exception e)
		{
			redirectAttributes.addAttribute("draftErrorMessage", "An error occured");
			redirectAttributes.addAttribute("draftErrorDescription", e.getMessage());
			logger.debug(e.getMessage());
			logger.debug(e);
			logger.debug(e.getCause());
			e.printStackTrace();
		}
		return "redirect:/controller/pages/view/factsheet";
	}
	
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	public String finish(Model model, HttpServletRequest httpRequest, final RedirectAttributes redirectAttributes) {
		logger.debug("BEGIN");
		post(model, httpRequest,redirectAttributes);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setLogDuringStatus(LogDuringStatus.CAP);
		participantLogDuring.setFactsheetFinishTime(new Date());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		return "redirect:/controller/pages/view/cap/home";
	}
}
