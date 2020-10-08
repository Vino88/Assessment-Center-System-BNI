package com.dlabs.acs.controller.view;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.constants.SystemConfigConstants;
import com.dlabs.acs.dto.assessement.InbasketDto;
import com.dlabs.acs.dto.assessement.ParticipantInbasketDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantInbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.enumeration.LogDuringStatus;
import com.dlabs.acs.entity.inbasket.InbasketInbox;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.inbasket.enumeration.InboxType;
import com.dlabs.acs.service.intf.assessement.IParticipantInbasketInboxInbasketQuestionService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxInbasketQuestionService;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxService;
import com.dlabs.acs.util.DateUtil;

@Controller
@RequestMapping("/pages/view/inbasket")
public class InbasketController {
	private Logger logger =Logger.getLogger(InbasketController.class);
	
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	
	@Autowired
	private IInbasketInboxService  inbasketInboxService;
	
	@Autowired
	private IInbasketInboxInbasketQuestionService inbasketInboxInbasketQuestionServcie;
	
	@Autowired
	private IParticipantInbasketInboxInbasketQuestionService participantInbasketInboxInbasketQuestionServcie;
	
	@RequestMapping(value = "/backgroundinfo", method = RequestMethod.GET)
	public String backgroundinfo(Model model, HttpServletRequest httpRequest) {
		return "inbasket-backgroundinfo";
	}
	@RequestMapping(value = "/background", method = RequestMethod.GET)
	public String background(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		logger.debug("END");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		List<InbasketInbox> listMemo = inbasketInboxService.getByInboxType(InboxType.MEMO);
		List<InbasketInbox> listVideo = inbasketInboxService.getByInboxType(InboxType.VIDEO);
		listMemo.addAll(listVideo);
		model.addAttribute("list", listMemo);
		
		
		if(participantLogDuring.getInbasketReadMemoStartTime() == null)
		{
			Map<String, String> mapTimerSystemConfig = (Map<String, String>) httpRequest.getSession().getAttribute(SessionConstants.SYSTEM_CONFIG_TIMER);
			
			String liTimerStr = mapTimerSystemConfig.get(SystemConfigConstants.TIMER.TIMER_READ_MEMO);
			int liTimerInt = SystemConfigConstants.TIMER.DEFAULT_READ_MEMO;
			try
			{
				liTimerInt = Integer.parseInt(liTimerStr);
			}catch(Exception e){ liTimerInt = SystemConfigConstants.TIMER.DEFAULT_READ_MEMO;}
			
			
			
			Calendar endTime = Calendar.getInstance();
			endTime.add(Calendar.MINUTE, liTimerInt);
			
			participantLogDuring.setInbasketReadMemoEndTime(endTime.getTime());
			
			participantLogDuring.setInbasketReadMemoStartTime(new Date());
			participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
			participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
			
			participantLogDuringService.saveOrUpdate(participantLogDuring);
			httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		}
		
		Long countAnswer =participantInbasketInboxInbasketQuestionServcie.countMemoByPartId(participant.getId());
		model.addAttribute("countAnswer", countAnswer);
		
		int remainingTime = DateUtil.calculateRemainingTime(participantLogDuring.getInbasketReadMemoEndTime());
		model.addAttribute("remainingTime", remainingTime);
		return "inbasket-background";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		if(participantLogDuring.getInbasketStartTime() == null)
		{
			Map<String, String> mapTimerSystemConfig = (Map<String, String>) httpRequest.getSession().getAttribute(SessionConstants.SYSTEM_CONFIG_TIMER);
			
			String liTimerStr = mapTimerSystemConfig.get(SystemConfigConstants.TIMER.TIMER_INBASKET);
			int liTimerInt = SystemConfigConstants.TIMER.DEFAULT_INBASKET_MINUTES;
			try
			{
				liTimerInt = Integer.parseInt(liTimerStr);
			}catch(Exception e){ liTimerInt = SystemConfigConstants.TIMER.DEFAULT_INBASKET_MINUTES;}
			
			
			
			Calendar endTime = Calendar.getInstance();
			endTime.add(Calendar.MINUTE, liTimerInt);
			
			participantLogDuring.setInbasketEndTime(endTime.getTime());
			
			participantLogDuring.setInbasketStartTime(new Date());
			participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
			participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
			
			participantLogDuringService.saveOrUpdate(participantLogDuring);
			httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		}
		
		List<InbasketInbox> list = inbasketInboxService.getByInboxType(InboxType.MEMO);
		model.addAttribute("list", list);
		
		Map<Long, Boolean> map = new HashMap<Long, Boolean>();
		
		for(InbasketInbox inbasketInbox : list)
		{
			map.put(inbasketInbox.getId(), Boolean.FALSE);
		}
		
		
		Long countAnswer =participantInbasketInboxInbasketQuestionServcie.countMemoByPartId(participant.getId());
		model.addAttribute("countAnswer", countAnswer);
		
		List<ParticipantInbasketInboxInbasketQuestion> listParticipantIiiq =participantInbasketInboxInbasketQuestionServcie.getByParticipantId(participant.getId());
		for(ParticipantInbasketInboxInbasketQuestion participantInbasketInboxInbasketQuestion : listParticipantIiiq)
		{
			System.out.println(participantInbasketInboxInbasketQuestion.getInbasketInboxInbasketQuestion().getInbasketInbox().getTitle() +"mmmm");
			map.put(participantInbasketInboxInbasketQuestion.getInbasketInboxInbasketQuestion().getInbasketInbox().getId(), Boolean.TRUE);
		}
		model.addAttribute("map", map);
		
		
		int remainingTime = DateUtil.calculateRemainingTime(participantLogDuring.getInbasketEndTime());
		model.addAttribute("remainingTime", remainingTime);
		logger.debug("END");
		return "inbasket-view";
	}
	
	@RequestMapping(value = "/detail/{inboxId}", method = RequestMethod.GET)
	public String viewDetail(@PathVariable("inboxId") Long inboxId, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		InbasketInbox inbasketInbox = inbasketInboxService.findById(inboxId);
		model.addAttribute("inbasketInbox", inbasketInbox);
		
		List<InbasketInboxInbasketQuestion> list =inbasketInboxInbasketQuestionServcie.getByInboxId(inboxId);
		
		ParticipantInbasketInboxInbasketQuestion participantInbasketInboxInbasketQuestion = null;
		List<ParticipantInbasketInboxInbasketQuestion> listParticipantIiiq = new ArrayList<ParticipantInbasketInboxInbasketQuestion>();
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		Long countAnswer =participantInbasketInboxInbasketQuestionServcie.countMemoByPartId(participant.getId());
		model.addAttribute("countAnswer", countAnswer);
		
		if(participantLogDuring.getInbasketStartTime() == null)
		{
			Map<String, String> mapTimerSystemConfig = (Map<String, String>) httpRequest.getSession().getAttribute(SessionConstants.SYSTEM_CONFIG_TIMER);
			
			String liTimerStr = mapTimerSystemConfig.get(SystemConfigConstants.TIMER.TIMER_INBASKET);
			int liTimerInt = SystemConfigConstants.TIMER.DEFAULT_INBASKET_MINUTES;
			try
			{
				liTimerInt = Integer.parseInt(liTimerStr);
			}catch(Exception e){ liTimerInt = SystemConfigConstants.TIMER.DEFAULT_INBASKET_MINUTES;}
			
			
			
			Calendar endTime = Calendar.getInstance();
			endTime.add(Calendar.MINUTE, liTimerInt);
			
			participantLogDuring.setInbasketEndTime(endTime.getTime());
			
			participantLogDuring.setInbasketStartTime(new Date());
			participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
			participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
			
			participantLogDuringService.saveOrUpdate(participantLogDuring);
			httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		}
		
		
		for(InbasketInboxInbasketQuestion iiiq : list)
		{
			participantInbasketInboxInbasketQuestion=participantInbasketInboxInbasketQuestionServcie.getByPartIdAndIiibId(participant.getId(), iiiq.getId());
			if(participantInbasketInboxInbasketQuestion == null)
			{
				Date now = new Date();
				CommonFields commonFields = new CommonFields();
				commonFields.setCreatedBy(participant.getNik());
				commonFields.setCreatedDate(now);
				commonFields.setLastModifiedBy(participant.getNik());
				commonFields.setLastModifiedDate(now);
				
				participantInbasketInboxInbasketQuestion = new ParticipantInbasketInboxInbasketQuestion();
				participantInbasketInboxInbasketQuestion.setInbasketInboxInbasketQuestion(iiiq);
				participantInbasketInboxInbasketQuestion.setParticipant(participant);
				
				participantInbasketInboxInbasketQuestion.setCommonFields(commonFields);
				
				participantInbasketInboxInbasketQuestionServcie.save(participantInbasketInboxInbasketQuestion);
			}
			
			listParticipantIiiq.add(participantInbasketInboxInbasketQuestion);
		}
	
		model.addAttribute("listParticipantIiiq", listParticipantIiiq);
		
		
		List<InbasketInbox> listInbox = inbasketInboxService.getByInboxType(InboxType.MEMO);
		model.addAttribute("listInbox", listInbox);
		
		Long prevId = 0L;
		boolean isCurrent = false;
		for(InbasketInbox in : listInbox)
		{
			
			if(in.getId().equals(inboxId))
			{
				isCurrent = true;
				break;
			}
			
			prevId = in.getId();
		}
		listInbox = inbasketInboxService.getByInboxType(InboxType.VIDEO);
		model.addAttribute("listInboxVideo", listInbox);
		
		if(!isCurrent)
		{
			
			 
			 for(InbasketInbox in : listInbox)
				{
				 	
					if(in.getId().equals(inboxId))
					{
						isCurrent = true;
						
						participantLogDuring.setInbasketVideoLinkOpened(new Date());
						participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
						participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
						
						participantLogDuringService.saveOrUpdate(participantLogDuring);
						httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
						
						break;
					}
					prevId = in.getId();
				}
		}
		
		
		if(!isCurrent)
		{
			prevId = 0L;
		}
		
		model.addAttribute("prevId", prevId);
		
		
		int remainingTime = DateUtil.calculateRemainingTime(participantLogDuring.getInbasketEndTime());
		model.addAttribute("remainingTime", remainingTime);
		logger.debug("END");
		return "inbasket-view-detail";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public String post(@ModelAttribute("inbasketDto") InbasketDto inbasketDto, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		Date now = new Date();
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(participant.getNik());
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(participant.getNik());
		commonFields.setLastModifiedDate(now);
		
		ParticipantInbasketInboxInbasketQuestion participantInbasketInboxInbasketQuestion = null;
		
		for(ParticipantInbasketDto participantInbasketDto : inbasketDto.getListParticipantInbasketDto())
		{
			participantInbasketInboxInbasketQuestion=null;
			if(participantInbasketDto.getParticipantIiqId() != null)
			{
				participantInbasketInboxInbasketQuestion = participantInbasketInboxInbasketQuestionServcie.findById(participantInbasketDto.getParticipantIiqId());
			}
			
			if(participantInbasketInboxInbasketQuestion == null)
			{
				participantInbasketInboxInbasketQuestion = new ParticipantInbasketInboxInbasketQuestion();
				participantInbasketInboxInbasketQuestion.setCommonFields(commonFields);
			}
			
			InbasketInboxInbasketQuestion inbasketInboxInbasketQuestion = inbasketInboxInbasketQuestionServcie.findById(participantInbasketDto.getIiqId());
			participantInbasketInboxInbasketQuestion.setParticipant(participant);
			participantInbasketInboxInbasketQuestion.setInbasketInboxInbasketQuestion(inbasketInboxInbasketQuestion);
			participantInbasketInboxInbasketQuestion.setAnswer(participantInbasketDto.getAnswer());
			
			participantInbasketInboxInbasketQuestionServcie.saveOrUpdate(participantInbasketInboxInbasketQuestion);
		}
		
		// LOG
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setAnalysysLastDraftTime(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		List<InbasketInbox> list = inbasketInboxService.getByInboxType(InboxType.MEMO);
		Long nextId = 0L;
		boolean isCurrent = false;
		for(InbasketInbox in : list)
		{
			if(isCurrent)
			{
				nextId = in.getId();
				break;
			}
			if(in.getId().equals(inbasketDto.getInbasketInboxId()))
			{
				isCurrent = true;
			}
		}
		
		
		if(nextId.equals(0L))
		{
			 list = inbasketInboxService.getByInboxType(InboxType.VIDEO);
			 
			 for(InbasketInbox in : list)
				{
					if(isCurrent)
					{
						nextId = in.getId();
						break;
					}
					if(in.getId().equals(inbasketDto.getInbasketInboxId()))
					{
						isCurrent = true;
					}
				}
		}
		
		if(isCurrent)
		{
			if(nextId.equals(0L))
			{
				return "redirect:/controller/pages/view/inbasket/beforefinish";
			}else
			{
				return "redirect:/controller/pages/view/inbasket/detail/"+nextId;
			}
		}
		else
		{
			return "redirect:/controller/pages/view/home";
		}
		
		
	}
	@RequestMapping(value = "/beforefinish", method = RequestMethod.GET)
	public String beforefinish(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		Long countAnswer =participantInbasketInboxInbasketQuestionServcie.countMemoByPartId(participant.getId());
		model.addAttribute("countAnswer", countAnswer);
		logger.debug("END");
		return "inbasket-finish";
	}
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	public String finish(@ModelAttribute("inbasketDto") InbasketDto inbasketDto, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		post(inbasketDto, model, httpRequest);
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setLogDuringStatus(LogDuringStatus.ANALYSYS);
		participantLogDuring.setInbasketFinishTime(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		return "redirect:/controller/pages/view/analysys/home";
	}
	
	
	@RequestMapping(value = "/finish", method = RequestMethod.GET)
	public String finish(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setLogDuringStatus(LogDuringStatus.ANALYSYS);
		participantLogDuring.setInbasketFinishTime(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		return "redirect:/controller/pages/view/analysys/home";
	}
	
	@RequestMapping(value = "/expired", method = RequestMethod.GET)
	public String expired(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setLogDuringStatus(LogDuringStatus.ANALYSYS);
		participantLogDuring.setAnalysysLastDraftTime(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		return "redirect:/controller/pages/view/analysys/home";
	}
	
	
	@RequestMapping(value = "/redirectToVideo", method = RequestMethod.POST)
	public String redirectToVideo(@ModelAttribute("inbasketDto") InbasketDto inbasketDto, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		post(inbasketDto, model, httpRequest);
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		participantLogDuring.setInbasketRedirect(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		List<InbasketInbox> list = inbasketInboxService.getByInboxType(InboxType.VIDEO);
		return "redirect:/controller/pages/view/inbasket/detail/"+list.get(0).getId();
	}
	
	@RequestMapping(value = "/redirectToVideo", method = RequestMethod.GET)
	public String redirectToVideo( Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		participantLogDuring.setInbasketRedirect(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		List<InbasketInbox> list = inbasketInboxService.getByInboxType(InboxType.VIDEO);
		return "redirect:/controller/pages/view/inbasket/detail/"+list.get(0).getId();
	}
	
	
	@RequestMapping(value = "/warningVideo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String warningVideo(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		participantLogDuring.setInbasketWarning(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		return "suc";
		
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		List<InbasketInbox> listMemo = inbasketInboxService.getByInboxType(InboxType.MEMO);
		
		model.addAttribute("list", listMemo);
		
		
		Long countAnswer =participantInbasketInboxInbasketQuestionServcie.countMemoByPartId(participant.getId());
		model.addAttribute("countAnswer", countAnswer);
		logger.debug("END");
		return "inbasket-home";
	}
	
	@RequestMapping(value = "/yourrole", method = RequestMethod.GET)
	public String yourrole(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		
		Long countAnswer =participantInbasketInboxInbasketQuestionServcie.countMemoByPartId(participant.getId());
		model.addAttribute("countAnswer", countAnswer);
		logger.debug("END");
		return "inbasket-yourrole";
	}
}
