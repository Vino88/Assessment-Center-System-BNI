package com.dlabs.acs.controller.open.auth;

import java.util.Date;
import java.util.List;
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
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.AnalysysType;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.enumeration.LogDuringStatus;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.inbasket.InbasketInbox;
import com.dlabs.acs.entity.inbasket.enumeration.InboxType;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.service.intf.analysys.IAnalysysService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.config.ISystemConfigService;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxService;
import com.dlabs.acs.util.DateUtil;
import com.dlabs.acs.util.Validator;

@Controller
@RequestMapping("/pages/open/auth/login")
public class ViewAuthController {
	private Logger logger = Logger.getLogger(ViewAuthController.class);
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	@Autowired
	private IInbasketInboxService  inbasketInboxService;
	@Autowired
	private IAnalysysService analysysService;
	@Autowired
	private ISystemConfigService systemConfigService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest request) {
		logger.debug("BEGIN");
		
		String result = "login";
		if(request.getSession().getAttribute(SessionConstants.PARTICIPANT) != null){
			result= "redirect:/controller/pages/view/home";
		}
		
		logger.debug("END");
		return result;
	}
	
	
	@RequestMapping(value = "/firsthalf", method = RequestMethod.GET)
	public String firsthalf(Model model, HttpServletRequest request) {
		logger.debug("BEGIN");
		
		String result = "firsthalf";
		if(request.getSession().getAttribute(SessionConstants.PARTICIPANT) != null){
			result= "redirect:/controller/pages/view/home";
		}
		
		logger.debug("END");
		return result;
	}
	
	@RequestMapping(value = "/vs", method = RequestMethod.GET)
	public String vs(Model model, HttpServletRequest request) {
		logger.debug("BEGIN");
		
		String result = "vs";
		if(request.getSession().getAttribute(SessionConstants.PARTICIPANT) != null){
			result= "redirect:/controller/pages/view/home";
		}
		
		logger.debug("END");
		return result;
	}
	
	@RequestMapping(value = "/secondhalf", method = RequestMethod.GET)
	public String secondhalf(Model model, HttpServletRequest request) {
		logger.debug("BEGIN");
		
		String result = "secondhalf";
		if(request.getSession().getAttribute(SessionConstants.PARTICIPANT) != null){
			result= "redirect:/controller/pages/view/home";
		}
		
		logger.debug("END");
		return result;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String login(String nik, String password, HttpServletRequest httpRequest, final RedirectAttributes redirectAttributes) {
		logger.debug("BEGIN");
		if(Validator.isNull(nik) || Validator.isNull(password))
		{
			redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.nullorempty");
			return "redirect:/controller/pages/open/auth/login";
		}
		
		
		Participant participant = participantService.login(nik, password);
		if(participant == null || !password.equals(participant.getPassword()))
		{
			redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.invalid");
			return "redirect:/controller/pages/open/auth/login";
		}
		
		Batch batch = participantService.getBatchByParticipantId(participant.getId());
		if(batch == null)
		{
			redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.batch.null");
			return "redirect:/controller/pages/open/auth/login";
		}
		
		
		
		// FIRST HALF NOT FINISHED
		if(! DateUtil.isPastDateTime(batch.getAssessementFirstHalfEndTime()))
		{
			if(!batch.isAssessementFirstHalfStart())
			{
				redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.batch.firsthalf.operatornotstart");
				return "redirect:/controller/pages/open/auth/login";
			}
			
			if(!DateUtil.isPastDateTime(batch.getAssessementFirstHalfStartTime()))
			{
				redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.batch.firsthalf.notinperiod");
				return "redirect:/controller/pages/open/auth/login";
			}
			if(! ParticipantStatus.SESSION_I.equals(participant.getParticipantStatus()))
			{
				participant.setParticipantStatus(ParticipantStatus.SESSION_I);
				participant.getCommonFields().setLastModifiedDate(new Date());
				participant.getCommonFields().setLastModifiedBy(participant.getNik());
				
				participantService.saveOrUpdate(participant);
			}
			
		}
		// FIRST HALF FINISHED, SECOND HALF NOT FINISHED
		else if(! DateUtil.isPastDateTime(batch.getAssessementFinishTime()))
		{
			if(!batch.isAssessementFirstHalfStart())
			{
				redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.batch.secondhalf.operatornotstart");
				return "redirect:/controller/pages/open/auth/login";
			}
			if(!DateUtil.isPastDateTime(batch.getAssessementSecondHalfStartTime()))
			{
				redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.batch.secondhalf.notinperiod");
				return "redirect:/controller/pages/open/auth/login";
			}
			
			if(! ParticipantStatus.SESSION_II.equals(participant.getParticipantStatus()))
			{
				participant.setParticipantStatus(ParticipantStatus.SESSION_II);
				participant.getCommonFields().setLastModifiedDate(new Date());
				participant.getCommonFields().setLastModifiedBy(participant.getNik());
				
				participantService.saveOrUpdate(participant);
			}
			
		}
		// ASSESSEMENT FINISHED
		else
		{
			redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.batch.finish");
			return "redirect:/controller/pages/open/auth/login";
		}
		
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_BATCH, batch);
		
		ParticipantLogDuring participantLogDuring = participantLogDuringService.getByParticipantIdFirst(participant.getId());
		if(participantLogDuring == null)
		{
			Date now = new Date();
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(participant.getNik());
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(participant.getNik());
			commonFields.setLastModifiedDate(now);
			
			participantLogDuring = new ParticipantLogDuring();
			participantLogDuring.setCommonFields(commonFields);
			
			participantLogDuring.setParticipant(participant);
		}
		else if(LogDuringStatus.FINISH.equals(participantLogDuring.getLogDuringStatus()))
		{
			redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.during.finish");
			return "redirect:/controller/pages/open/auth/login";
		}
		
		if(participantLogDuring.getFistTimeLogin() == null)
		{
			participantLogDuring.setFistTimeLogin(new Date());
			participantLogDuring.setLogDuringStatus(LogDuringStatus.FACTSHEET);
		}
		
		if(ParticipantStatus.SESSION_I.equals(participant.getParticipantStatus()) && LogDuringStatus.WAITING_SECOND_SESSION.equals(participantLogDuring.getLogDuringStatus()))
		{
			redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.batch.secondhalf.waiting");
			return "redirect:/controller/pages/open/auth/login";
		}
		
		if (ParticipantStatus.SESSION_II.equals(participant.getParticipantStatus()) && 
				(
				participantLogDuring.getLogDuringStatus() == null 
				|| LogDuringStatus.FACTSHEET.equals(participantLogDuring.getLogDuringStatus()) 
				|| LogDuringStatus.CAP.equals(participantLogDuring.getLogDuringStatus()) 
				|| LogDuringStatus.LCB.equals(participantLogDuring.getLogDuringStatus())
				|| LogDuringStatus.ASPIRATION.equals(participantLogDuring.getLogDuringStatus())
				|| LogDuringStatus.WAITING_SECOND_SESSION.equals(participantLogDuring.getLogDuringStatus())
				) 
			)
		{
			participantLogDuring.setLogDuringStatus(LogDuringStatus.INBASKET);
		}
		
		
		
		if(ParticipantStatus.SESSION_II.equals(participant.getParticipantStatus()) && participantLogDuring.getSecondSessionLoginTime() == null)
		{
			participantLogDuring.setSecondSessionLoginTime(new Date());
		}
		
		
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		
		
		List<InbasketInbox> inbasketInboxList = inbasketInboxService.getByInboxType(InboxType.VIDEO);
		httpRequest.getSession().setAttribute(SessionConstants.INBASKET_VIDEO_LIST, inbasketInboxList);
		
		
		List<Analysys> analysysList = analysysService.getByAnalysysType(AnalysysType.TEXT);
		httpRequest.getSession().setAttribute(SessionConstants.ANALYSIS_LIST, analysysList);
		
		List<Analysys> visionSpeechList = analysysService.getByAnalysysType(AnalysysType.VIDEO);
		httpRequest.getSession().setAttribute(SessionConstants.VISION_SPEECH_LIST, visionSpeechList);
		httpRequest.getSession().setAttribute(SessionConstants.VISION_SPEECH_FIRST, visionSpeechList.get(0));
		
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT, participant);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		Map<String, String> mapTimerSystemConfig = systemConfigService.getMapByModule(SystemConfigConstants.TIMER.MODULE_NAME);
		httpRequest.getSession().setAttribute(SessionConstants.SYSTEM_CONFIG_TIMER, mapTimerSystemConfig);
		
		logger.debug("END");
		return "redirect:/controller/pages/view/home";
	}
}
