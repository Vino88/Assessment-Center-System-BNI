package com.dlabs.acs.controller.alias;

import java.util.ArrayList;
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
import com.dlabs.acs.controller.view.InbasketController;
import com.dlabs.acs.dto.assessement.InbasketDto;
import com.dlabs.acs.dto.assessement.ParticipantInbasketDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantInbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.inbasket.InbasketInbox;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.inbasket.enumeration.InboxType;
import com.dlabs.acs.service.intf.assessement.IParticipantInbasketInboxInbasketQuestionService;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxInbasketQuestionService;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxService;

@Controller
@RequestMapping("/pages/alias/inbasket")
public class AliasInbasketController {
private Logger logger =Logger.getLogger(InbasketController.class);
	
	@Autowired
	private IInbasketInboxService  inbasketInboxService;
	
	@Autowired
	private IInbasketInboxInbasketQuestionService inbasketInboxInbasketQuestionServcie;
	
	@Autowired
	private IParticipantInbasketInboxInbasketQuestionService participantInbasketInboxInbasketQuestionServcie;
	
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
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
			map.put(participantInbasketInboxInbasketQuestion.getInbasketInboxInbasketQuestion().getInbasketInbox().getId(), Boolean.TRUE);
		}
		model.addAttribute("map", map);
		logger.debug("END");
		return "/alias/inbasket/view";
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
		
		Long countAnswer =participantInbasketInboxInbasketQuestionServcie.countMemoByPartId(participant.getId());
		model.addAttribute("countAnswer", countAnswer);
		
		
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
		logger.debug("END");
		return "/alias/inbasket/view/detail";
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
				return "redirect:/controller/pages/alias/analysys";
			}else
			{
				return "redirect:/controller/pages/alias/inbasket/detail/"+nextId;
			}
		}
		else
		{
			return "redirect:/controller/pages/alias/analysis";
		}
		
		
	}
	
}
