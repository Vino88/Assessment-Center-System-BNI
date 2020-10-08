package com.dlabs.acs.controller.view;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.constants.SystemConfigConstants;
import com.dlabs.acs.dto.assessement.ParticipantAnalysysDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.AnalysysType;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantAnalysys;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.enumeration.LogDuringStatus;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.service.intf.analysys.IAnalysysService;
import com.dlabs.acs.service.intf.assessement.IParticipantAnalysysService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.util.DateUtil;

@Controller
@RequestMapping("/pages/view/analysys")
public class AnalysysController {
	private Logger logger =Logger.getLogger(AnalysysController.class);
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	@Autowired
	private IAnalysysService analysysService;
	@Autowired
	private IParticipantAnalysysService participantAnalysysService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		if(participantLogDuring.getAnalysysStartTime() == null)
		{
			Map<String, String> mapTimerSystemConfig = (Map<String, String>) httpRequest.getSession().getAttribute(SessionConstants.SYSTEM_CONFIG_TIMER);
			
			String liTimerStr = mapTimerSystemConfig.get(SystemConfigConstants.TIMER.TIMER_ANALYSYS_MINUTES);
			int liTimerInt = SystemConfigConstants.TIMER.DEFAULT_ANALYSYS_MINUTES;
			try
			{
				liTimerInt = Integer.parseInt(liTimerStr);
			}catch(Exception e){ liTimerInt = SystemConfigConstants.TIMER.DEFAULT_ANALYSYS_MINUTES;}
			
			
			
			Calendar endTime = Calendar.getInstance();
			endTime.add(Calendar.MINUTE, liTimerInt);
			
			participantLogDuring.setAnalysysEndTime(endTime.getTime());
			
			participantLogDuring.setAnalysysStartTime(new Date());
			participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
			participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
			
			participantLogDuringService.saveOrUpdate(participantLogDuring);
			httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		}
		
		List<Analysys> list = (List<Analysys>) httpRequest.getSession().getAttribute(SessionConstants.ANALYSIS_LIST);
		
		
		for(Analysys analysys : list)
		{
			ParticipantAnalysys participantAnalysys = participantAnalysysService.getByPartIdAndAnalysysId(participant.getId(), analysys.getId());
			
			if(participantAnalysys == null)
			{
				Date now = new Date();
				CommonFields commonFields = new CommonFields();
				commonFields.setCreatedBy(participant.getNik());
				commonFields.setCreatedDate(now);
				commonFields.setLastModifiedBy(participant.getNik());
				commonFields.setLastModifiedDate(now);
				
				participantAnalysys = new ParticipantAnalysys();
				participantAnalysys.setCommonFields(commonFields);
				
				participantAnalysys.setParticipant(participant);
				participantAnalysys.setAnalysys(analysys);
				
				participantAnalysysService.save(participantAnalysys);
			}
		}
		model.addAttribute("list", list);
		Map<Long, Boolean> map = new HashMap<Long, Boolean>();
		
		for(Analysys analysys : list)
		{
			map.put(analysys.getId(), Boolean.FALSE);
		}
		
		List<ParticipantAnalysys> listParticipantAnalysis = participantAnalysysService.getByParticipantId(participant.getId());
		for(ParticipantAnalysys participantAnalysys : listParticipantAnalysis)
		{
			if(participantAnalysys.getAnswer() != null )
			{
				map.put(participantAnalysys.getAnalysys().getId(), Boolean.TRUE);
			}else
			{
				map.put(participantAnalysys.getAnalysys().getId(), Boolean.FALSE);
			}
			
		}
		
		model.addAttribute("map", map);
		
		
		int remainingTime = DateUtil.calculateRemainingTime(participantLogDuring.getAnalysysEndTime());
		model.addAttribute("remainingTime", remainingTime);
		
		logger.debug("END");
		return "analysys-view";
	}
	
	@RequestMapping(value = "/detail/{analysysId}", method = RequestMethod.GET)
	public String viewDetail(@PathVariable("analysysId") Long analysysId, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		Analysys analysys = analysysService.findById(analysysId);
		model.addAttribute("analysys", analysys);
		
		ParticipantAnalysys participantAnalysys = participantAnalysysService.getByPartIdAndAnalysysId(participant.getId(), analysysId);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		if(participantAnalysys == null)
		{
			Date now = new Date();
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(participant.getNik());
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(participant.getNik());
			commonFields.setLastModifiedDate(now);
			
			participantAnalysys = new ParticipantAnalysys();
			participantAnalysys.setCommonFields(commonFields);
			
			participantAnalysys.setParticipant(participant);
			participantAnalysys.setAnalysys(analysys);
			
			participantAnalysysService.save(participantAnalysys);
		}
	
		model.addAttribute("participantAnalysys", participantAnalysys);
		
		
		List<Analysys> list = (List<Analysys>) httpRequest.getSession().getAttribute(SessionConstants.ANALYSIS_LIST);
		
		Long prevId = 0L;
		Long nextId = 0L;
		boolean isCurrent = false;
		for(Analysys cl : list)
		{
			nextId = cl.getId();
			if(isCurrent)
			{
				break;
			}
			if(cl.getId().equals(analysysId))
			{
				isCurrent = true;
			}
			
		}
		isCurrent = false;
		for(Analysys cl : list)
		{
			
			if(cl.getId().equals(analysysId))
			{
				isCurrent = true;
			}
			
			if(isCurrent)
			{
				break;
			}
			
			prevId=cl.getId();
		}
		
		if(nextId.equals(analysysId))
		{
			list = (List<Analysys>) httpRequest.getSession().getAttribute(SessionConstants.VISION_SPEECH_LIST);
			nextId = list.get(0).getId();
		}
		
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		
		int remainingTime = DateUtil.calculateRemainingTime(participantLogDuring.getAnalysysEndTime());
		model.addAttribute("remainingTime", remainingTime);
		
		logger.debug("END");
		return "analysys-view-detail";
	}
	
	@RequestMapping(value = "/detail/visionspeech/{analysysId}", method = RequestMethod.GET)
	public String viewDetailVisionSpeech(@PathVariable("analysysId") Long analysysId, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		List<Analysys> list = (List<Analysys>) httpRequest.getSession().getAttribute(SessionConstants.VISION_SPEECH_LIST);
		model.addAttribute("analysys", list.get(0));
		
		
		ParticipantAnalysys participantAnalysys =  null;
		ParticipantAnalysys participantAnalysysFirst =  null;
		int i=0;
		for(Analysys analysys : list)
		{
			participantAnalysys = participantAnalysysService.getByPartIdAndAnalysysId(participant.getId(), analysys.getId());
			
			if(participantAnalysys == null)
			{
				Date now = new Date();
				CommonFields commonFields = new CommonFields();
				commonFields.setCreatedBy(participant.getNik());
				commonFields.setCreatedDate(now);
				commonFields.setLastModifiedBy(participant.getNik());
				commonFields.setLastModifiedDate(now);
				
				participantAnalysys = new ParticipantAnalysys();
				participantAnalysys.setCommonFields(commonFields);
				
				participantAnalysys.setParticipant(participant);
				participantAnalysys.setAnalysys(analysys);
				
				participantAnalysysService.save(participantAnalysys);
			}
			
			if(i==0)
			{
				participantAnalysysFirst = participantAnalysys;
			}
			
			i = i +1;
		}
	
		model.addAttribute("participantAnalysys", participantAnalysysFirst);
		
		list = (List<Analysys>) httpRequest.getSession().getAttribute(SessionConstants.ANALYSIS_LIST);
		
		model.addAttribute("prevId", list.get(list.size()-1).getId());
		int remainingTime = DateUtil.calculateRemainingTime(participantLogDuring.getAnalysysEndTime());
		model.addAttribute("remainingTime", remainingTime);
		logger.debug("END");
		return "analysys-view-detail-visionspeech";
	}
	
	@RequestMapping(value = "/detail/visionspeech", method = RequestMethod.POST)
	public String postVisionSpeech(@ModelAttribute("participantAnalysysDto") ParticipantAnalysysDto participantAnalysysDto, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		List<Analysys> list = (List<Analysys>) httpRequest.getSession().getAttribute(SessionConstants.VISION_SPEECH_LIST);
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		
		Date now = new Date();
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(participant.getNik());
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(participant.getNik());
		commonFields.setLastModifiedDate(now);
		
		ParticipantAnalysys participantAnalysys = null;
		
		for(Analysys analysys: list)
		{
			if(participantAnalysysDto.getParticipantAnalysysId() != null)
			{
				participantAnalysys = participantAnalysysService.getByPartIdAndAnalysysId(participant.getId(), analysys.getId());
			}
			
			if(participantAnalysys == null)
			{
				participantAnalysys = new ParticipantAnalysys();
				participantAnalysys.setCommonFields(commonFields);
			}
			
			
			participantAnalysys.setParticipant(participant);
			participantAnalysys.setAnalysys(analysys);
			participantAnalysys.setAnswer(participantAnalysysDto.getAnswer());
			
			participantAnalysysService.saveOrUpdate(participantAnalysys);
			
		}
		
		logger.debug("END");
		return "redirect:/controller/pages/view/analysys/beforefinish";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public String post(@ModelAttribute("participantAnalysysDto") ParticipantAnalysysDto participantAnalysysDto, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		Date now = new Date();
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(participant.getNik());
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(participant.getNik());
		commonFields.setLastModifiedDate(now);
		
		ParticipantAnalysys participantAnalysys = null;
		
		
		if(participantAnalysysDto.getParticipantAnalysysId() != null)
		{
			participantAnalysys = participantAnalysysService.findById(participantAnalysysDto.getParticipantAnalysysId());
		}
		
		if(participantAnalysys == null)
		{
			participantAnalysys = new ParticipantAnalysys();
			participantAnalysys.setCommonFields(commonFields);
		}
		
		Analysys analysys = analysysService.findById(participantAnalysysDto.getAnalysysId());
		participantAnalysys.setParticipant(participant);
		participantAnalysys.setAnalysys(analysys);
		participantAnalysys.setAnswer(participantAnalysysDto.getAnswer());
		
		participantAnalysysService.saveOrUpdate(participantAnalysys);
	
		
		// LOG
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setAnalysysLastDraftTime(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
			
		logger.debug("END");
		List<Analysys> list = (List<Analysys>) httpRequest.getSession().getAttribute(SessionConstants.VISION_SPEECH_LIST);
		if(list.get(0).getId().equals(participantAnalysysDto.getNextId()))
		{
			return "redirect:/controller/pages/view/analysys/detail/visionspeech/" + participantAnalysysDto.getNextId();
		}else if(participantAnalysysDto.getNextId() == null)
		{
			return "redirect:/controller/pages/view/analysys/beforefinish";
		}
		return "redirect:/controller/pages/view/analysys/detail/" + participantAnalysysDto.getNextId();
	}
	
	@RequestMapping(value = "/beforefinish", method = RequestMethod.GET)
	public String beforefinish(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		logger.debug("END");
		return "analysys-finish";
	}
	
	@RequestMapping(value = "/finish", method = RequestMethod.GET)
	public String finish(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setLogDuringStatus(LogDuringStatus.VISION_SPEECH);
		participantLogDuring.setAnalysysFinishTime(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		return "redirect:/controller/pages/view/logout/vs";
//		return "redirect:/controller/pages/view/visionspeech/home";
	}
	
	@RequestMapping(value = "/expired", method = RequestMethod.GET)
	public String expired(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setLogDuringStatus(LogDuringStatus.VISION_SPEECH);
		participantLogDuring.setAnalysysLastDraftTime(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		return "redirect:/controller/pages/view/logout/vs";
//		return "redirect:/controller/pages/view/visionspeech/home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		logger.debug("END");
		return "analysys-home";
	}
}
