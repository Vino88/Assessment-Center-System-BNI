package com.dlabs.acs.controller.alias;

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
import com.dlabs.acs.controller.view.AnalysysController;
import com.dlabs.acs.dto.assessement.ParticipantAnalysysDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.AnalysysType;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantAnalysys;
import com.dlabs.acs.service.intf.analysys.IAnalysysService;
import com.dlabs.acs.service.intf.assessement.IParticipantAnalysysService;

@Controller
@RequestMapping("/pages/alias/analysys")
public class AliasAnalysisController {
	private Logger logger =Logger.getLogger(AnalysysController.class);
	
	@Autowired
	private IAnalysysService analysysService;
	@Autowired
	private IParticipantAnalysysService participantAnalysysService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		List<Analysys> list = analysysService.getByAnalysysType(AnalysysType.TEXT);
		model.addAttribute("list", list);
		Map<Long, Boolean> map = new HashMap<Long, Boolean>();
		
		for(Analysys analysys : list)
		{
			map.put(analysys.getId(), Boolean.FALSE);
		}
		
		List<ParticipantAnalysys> listParticipantAnalysis = participantAnalysysService.getByParticipantId(participant.getId());
		for(ParticipantAnalysys participantAnalysys : listParticipantAnalysis)
		{
			map.put(participantAnalysys.getAnalysys().getId(), Boolean.TRUE);
		}
		
		model.addAttribute("map", map);
		logger.debug("END");
		return "/alias/analysys/view";
	}
	
	@RequestMapping(value = "/detail/{analysysId}", method = RequestMethod.GET)
	public String viewDetail(@PathVariable("analysysId") Long analysysId, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		Analysys analysys = analysysService.findById(analysysId);
		model.addAttribute("analysys", analysys);
		
		ParticipantAnalysys participantAnalysys = participantAnalysysService.getByPartIdAndAnalysysId(participant.getId(), analysysId);
		
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
		
		
		List<Analysys> list = analysysService.getByAnalysysType(AnalysysType.TEXT);
		
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
			list = analysysService.getByAnalysysType(AnalysysType.VIDEO);
			nextId = list.get(0).getId();
		}
		
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		logger.debug("END");
		return "/alias/analysys/view/detail";
	}
	
	@RequestMapping(value = "/detail/visionspeech/{analysysId}", method = RequestMethod.GET)
	public String viewDetailVisionSpeech(@PathVariable("analysysId") Long analysysId, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		viewDetail(analysysId, model, httpRequest);
		logger.debug("END");
		return "/alias/analysys/view/detail/visionspeech";
	}
	
	@RequestMapping(value = "/detail/visionspeechvs/{analysysId}", method = RequestMethod.GET)
	public String viewDetailVisionSpeechVs(@PathVariable("analysysId") Long analysysId, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		viewDetail(analysysId, model, httpRequest);
		logger.debug("END");
		return "/alias/analysys/view/detail/visionspeech/vs";
	}
	
	@RequestMapping(value = "/detail/visionspeech", method = RequestMethod.POST)
	public String postVisionSpeech(@ModelAttribute("participantAnalysysDto") ParticipantAnalysysDto participantAnalysysDto, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		post(participantAnalysysDto, model, httpRequest);
		
		List<Analysys> list = analysysService.getByAnalysysType(AnalysysType.VIDEO);
		
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
		return "redirect:/controller/pages/alias/analysys/detail/visionspeechvs/"+participantAnalysysDto.getAnalysysId();
	}
	
	@RequestMapping(value = "/detail/visionspeechvs", method = RequestMethod.POST)
	public String postVisionSpeechvs(@ModelAttribute("participantAnalysysDto") ParticipantAnalysysDto participantAnalysysDto, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		//post(participantAnalysysDto, model, httpRequest);
		logger.debug("END");
		return "redirect:/controller/pages/alias/analysys/beforefinish";
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
	
		logger.debug("END");
		List<Analysys> list = analysysService.getByAnalysysType(AnalysysType.VIDEO);
		if(list.get(0).getId().equals(participantAnalysysDto.getNextId()))
		{
			return "redirect:/controller/pages/alias/analysys/detail/visionspeech/" + participantAnalysysDto.getNextId();
		}else if(participantAnalysysDto.getNextId() == null)
		{
			return "redirect:/controller/pages/alias/analysys/beforefinish";
		}
		return "redirect:/controller/pages/alias/analysys/detail/" + participantAnalysysDto.getNextId();
	}
	
}
