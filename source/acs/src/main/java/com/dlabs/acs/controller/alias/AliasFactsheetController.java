package com.dlabs.acs.controller.alias;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.controller.view.FactsheetController;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.factsheet.IProfileService;
import com.dlabs.acs.service.intf.masterdata.IEmployeeService;

@Controller
@RequestMapping("/pages/alias/factsheet")
public class AliasFactsheetController {
private Logger logger =Logger.getLogger(FactsheetController.class);
	
	@Autowired
	private IParticipantService participantService;
	
	@Autowired
	private IProfileService profileService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		Profile profile = participantService.getProfileByParticipantId(participant.getId());
		model.addAttribute("profile", profile);
		
		Employee employee =employeeService.getByNik(participant.getNik());
		model.addAttribute("employee", employee);
		
		logger.debug("END");
		return "/alias/factsheet/view";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String post(Model model, HttpServletRequest httpRequest) {
		logger.debug("Alias:factsheet:post:BEGIN");
		
		try
		{
			logger.debug("FactSheetController:doPost:BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		
		Profile profile = participantService.getProfileByParticipantId(participant.getId());
		
		/** Aspirasi karir & Main Responsibility */
		String careerAspr = (String) httpRequest.getParameter("careerAspiration");
		String mainResponsibility = (String) httpRequest.getParameter("mainResponsibility");
		profile.setCareerAspiration(careerAspr);
		profile.setMainResponsibility(mainResponsibility);
		profile.getCommonFields().setLastModifiedDate(new Date());
		
		profileService.saveOrUpdate(profile);
		
		/** Employee Master Data - Phone Number */
		Employee employee =employeeService.getByNik(participant.getNik());
		String phoneNumber = (String) httpRequest.getParameter("phoneNumber");
		employee.setPhone(phoneNumber);
		employee.getCommonFields().setLastModifiedDate(new Date());
		employeeService.saveOrUpdate(employee);
		
		logger.debug("Alias:FactSheetController:doPost:END");
		}catch(Exception e)
		{
			logger.debug(e.getMessage());
			logger.debug(e);
			logger.debug(e.getCause());
			e.printStackTrace();
		}
		
		
		logger.debug("END");
		
		return "redirect:/controller/pages/alias/cap";
	}
}
