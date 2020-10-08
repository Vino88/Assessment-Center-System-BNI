package com.dlabs.acs.controller.view;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.constants.SystemConfigConstants;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.enumeration.LogDuringStatus;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.factsheet.IProfileService;
import com.dlabs.acs.service.intf.masterdata.IEmployeeService;
import com.dlabs.acs.util.DateUtil;

@Controller
@RequestMapping("/pages/view/aspiration")
public class AspirationController {
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
		
		if(participantLogDuring.getAspirationStartTime() == null)
		{
			Map<String, String> mapTimerSystemConfig = (Map<String, String>) httpRequest.getSession().getAttribute(SessionConstants.SYSTEM_CONFIG_TIMER);
			
			String capTimerStr = mapTimerSystemConfig.get(SystemConfigConstants.TIMER.TIMER_ASPIRATION_MINUTES);
			int capTimerInt = SystemConfigConstants.TIMER.DEFAULT_ASPIRATION_MINUTES;
			try
			{
				capTimerInt = Integer.parseInt(capTimerStr);
			}catch(Exception e){ capTimerInt = SystemConfigConstants.TIMER.DEFAULT_ASPIRATION_MINUTES;}
			
			
			
			Calendar endTime = Calendar.getInstance();
			endTime.add(Calendar.MINUTE, capTimerInt);
			
			participantLogDuring.setAspirationEndTime(endTime.getTime());
			participantLogDuring.setAspirationStartTime(new Date());
			participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
			participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
			
			participantLogDuringService.saveOrUpdate(participantLogDuring);
			httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		}
		int remainingTime = DateUtil.calculateRemainingTime(participantLogDuring.getAspirationEndTime());
		model.addAttribute("remainingTime", remainingTime);
		logger.debug("END");
		return "aspiration-view";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String post(Model model, HttpServletRequest httpRequest, final RedirectAttributes redirectAttributes) {
		logger.debug("BEGIN");
		ParticipantLogDuring participantLogDuring = null;
		try
		{
			logger.debug("FactSheetController:doPost:BEGIN");
			
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantService.saveOrUpdate(participant);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT, participant);
		
		Profile profile = participantService.getProfileByParticipantId(participant.getId());
		
		/** Aspirasi karir & Main Responsibility */
		String careerAspr = (String) httpRequest.getParameter("careerAspiration");
		String mainResponsibility = (String) httpRequest.getParameter("mainResponsibility");
		profile.setCareerAspiration(careerAspr);
		profile.setMainResponsibility(mainResponsibility);
		profile.setCurrentResponsibility(mainResponsibility);
		profile.setCurrentDirectSupervisor((String) httpRequest.getParameter("currentDirectSupervisor"));
		profile.setCurrentPeriode((String) httpRequest.getParameter("currentPeriode"));
		profile.setCurrentPositionName((String) httpRequest.getParameter("currentPositionName"));
		profile.setCurrentSupervisorEmail((String) httpRequest.getParameter("currentSupervisorEmail"));
		
		profile.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		profileService.saveOrUpdate(profile);
		
		/** Employee Master Data - Phone Number, Main Responsibility */
		Employee employee =employeeService.getByNik(participant.getNik());
		
		employee.getCommonFields().setLastModifiedDate(new Date());
		employee.setCurrentResponsibility(mainResponsibility);
		
		employeeService.saveOrUpdate(employee);
		
		participantLogDuring.setLogDuringStatus(LogDuringStatus.ASPIRATION);
		participantLogDuring.setFactsheetLastDraftTime(new Date());
		participantLogDuring.setAspirationLastDraftTime(new Date());
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
		return "redirect:/controller/pages/view/aspiration";
	}
	
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	public String finish(Model model, HttpServletRequest httpRequest, final RedirectAttributes redirectAttributes) {
		logger.debug("BEGIN");
		post(model, httpRequest,redirectAttributes);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setLogDuringStatus(LogDuringStatus.WAITING_SECOND_SESSION);
		participantLogDuring.setFactsheetFinishTime(new Date());
		participantLogDuring.setFirstSessionFinishTime(new Date());
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.UNAUTHORIZED_MESSAGE, "message.simulation.firsthalf.finish");
		return "redirect:/controller/pages/view/logout/firsthalf";
	}
}
