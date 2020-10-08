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
import com.dlabs.acs.entity.inbasket.InbasketInbox;
import com.dlabs.acs.entity.inbasket.enumeration.InboxType;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.service.intf.analysys.IAnalysysService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.config.ISystemConfigService;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxService;
import com.dlabs.acs.util.Validator;

@Controller
@RequestMapping("/pages/open/auth/alias/login")
public class AliasLoginController {
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
		String result = "/alias/login";
		if(request.getSession().getAttribute(SessionConstants.ALIAS) != null){
			result= "redirect:/controller/pages/alias/home";
		}
		logger.debug("END");
		return result;
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String login(String nik, String password, HttpServletRequest httpRequest, final RedirectAttributes redirectAttributes) {
		logger.debug("BEGIN");
		if(Validator.isNull(nik) || Validator.isNull(password) || !"admin123".equals(password))
		{
			redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.nullorempty");
			return "redirect:/controller/pages/open/auth/alias/login";
		}
		
		
		Participant participant = participantService.getByNikAndActive(nik);
		if(participant == null)
		{
			redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.invalid");
			return "redirect:/controller/pages/open/auth/alias/login";
		}
		
		Batch batch = participantService.getBatchByParticipantId(participant.getId());
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
		
		List<InbasketInbox> inbasketInboxList = inbasketInboxService.getByInboxType(InboxType.VIDEO);
		httpRequest.getSession().setAttribute(SessionConstants.INBASKET_VIDEO_LIST, inbasketInboxList);
		
		List<Analysys> visionSpeechList = analysysService.getByAnalysysType(AnalysysType.VIDEO);
		httpRequest.getSession().setAttribute(SessionConstants.VISION_SPEECH_LIST, visionSpeechList);
		httpRequest.getSession().setAttribute(SessionConstants.VISION_SPEECH_FIRST, visionSpeechList.get(0));
		
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT, participant);
		httpRequest.getSession().setAttribute(SessionConstants.ALIAS, participant);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		Map<String, String> mapTimerSystemConfig = systemConfigService.getMapByModule(SystemConfigConstants.TIMER.MODULE_NAME);
		httpRequest.getSession().setAttribute(SessionConstants.SYSTEM_CONFIG_TIMER, mapTimerSystemConfig);
		
		logger.debug("END");
		return "redirect:/controller/pages/alias/home";
	}
}
