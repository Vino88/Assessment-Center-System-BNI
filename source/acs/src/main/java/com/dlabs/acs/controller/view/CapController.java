package com.dlabs.acs.controller.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.constants.SystemConfigConstants;
import com.dlabs.acs.dto.assessement.CapDto;
import com.dlabs.acs.dto.assessement.ParticipantCapDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantCap;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.enumeration.LogDuringStatus;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.service.intf.assessement.IParticipantCapService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.cap.ICapService;
import com.dlabs.acs.util.DateUtil;

@Controller
@RequestMapping("/pages/view/cap")
public class CapController {
	private Logger logger =Logger.getLogger(FactsheetController.class);
	
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	
	@Autowired
	private ICapService capService;
	
	@Autowired
	private IParticipantCapService participantCapService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest httpRequest)
	{
		return view(model, 0, httpRequest);
	}
	@RequestMapping(value = "/{currentIndex}", method = RequestMethod.GET)
	public String view(Model model,@PathVariable("currentIndex") int currentIndex, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		if(participantLogDuring.getCapFinishTime() != null || !ParticipantStatus.SESSION_I.equals(participant.getParticipantStatus()))
		{
			return "redirect:/controller/pages/view/home";
		}
		
		model.addAttribute("currentIndex", currentIndex);
		if(participantLogDuring.getCapStartTime() == null)
		{
			Map<String, String> mapTimerSystemConfig = (Map<String, String>) httpRequest.getSession().getAttribute(SessionConstants.SYSTEM_CONFIG_TIMER);
			
			String capTimerStr = mapTimerSystemConfig.get(SystemConfigConstants.TIMER.TIMER_CAP_MINUTES);
			int capTimerInt = SystemConfigConstants.TIMER.DEFAULT_CAP_MINUTES;
			try
			{
				capTimerInt = Integer.parseInt(capTimerStr);
			}catch(Exception e){ capTimerInt = SystemConfigConstants.TIMER.DEFAULT_CAP_MINUTES;}
			
			
			
			Calendar endTime = Calendar.getInstance();
			endTime.add(Calendar.MINUTE, capTimerInt);
			
			participantLogDuring.setCapEndTime(endTime.getTime());
			participantLogDuring.setCapStartTime(new Date());
			participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
			participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
			
			participantLogDuringService.saveOrUpdate(participantLogDuring);
			httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		}
		
		List<Cap> listCap  = capService.getAll();
		List<ParticipantCap> listParticipantCap = participantCapService.getByParticipantId(participant.getId());
		if(listParticipantCap == null || listParticipantCap.size() == 0)
		{
			Date now = new Date();
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(participant.getNik());
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(participant.getNik());
			commonFields.setLastModifiedDate(now);
			
			listParticipantCap = new ArrayList<ParticipantCap>();
			ParticipantCap participantCap = null;
			for(Cap cap : listCap)
			{
				participantCap = new ParticipantCap();
				participantCap.setCap(cap);
				participantCap.setParticipant(participant);
				participantCap.setCommonFields(commonFields);
				
				listParticipantCap.add(participantCap);
			}
			participantCapService.save(listParticipantCap);
		}
		
		model.addAttribute("listParticipantCap", listParticipantCap);
		model.addAttribute("listSize", listParticipantCap.size());
		
		int remainingTime = DateUtil.calculateRemainingTime(participantLogDuring.getCapEndTime());
		model.addAttribute("remainingTime", remainingTime);
		
		
		logger.debug("END");
		return "cap-view";
	}
	
	
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	public String finish(@ModelAttribute("capDto") CapDto capDto, Model model, HttpServletRequest httpRequest, final RedirectAttributes redirectAttributes)
	{
logger.debug("BEGIN post");
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		Date now = new Date();
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(participant.getNik());
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(participant.getNik());
		commonFields.setLastModifiedDate(now);
		
		ParticipantCap participantCap = null;
		for(ParticipantCapDto participantCapDto : capDto.getListParticipantCapDto())
		{
			participantCap = null;
			if(participantCapDto.getParticipantCapId() != null)
			{
				participantCap = participantCapService.findById(participantCapDto.getParticipantCapId());
			}
			
			if(participantCap == null)
			{
				participantCap = new ParticipantCap();
				participantCap.setCommonFields(commonFields);
			}
			
			Cap cap = capService.findById(participantCapDto.getCapId());
			
			participantCap.setCap(cap);

			participantCap.setAnsSituation(participantCapDto.getAnsSituation());
			participantCap.setAnsAction(participantCapDto.getAnsAction());
			participantCap.setAnsResult(participantCapDto.getAnsResult());
			
			participantCap.getCommonFields().setLastModifiedBy(participant.getNik());
			participantCap.getCommonFields().setLastModifiedDate(now);
			participantCap.setParticipant(participant);
			participantCapService.saveOrUpdate(participantCap);
		}
		
		participantLogDuring.setCapFinishTime(new Date());
		participantLogDuring.setLogDuringStatus(LogDuringStatus.LCB);
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		logger.debug("END post");
		return "redirect:/controller/pages/view/lcb/home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		if(participantLogDuring.getCapStartTime() == null)
		{
			return "cap-home";
		}
		logger.debug("END");
		return "redirect:/controller/pages/view/cap";
	}
}
