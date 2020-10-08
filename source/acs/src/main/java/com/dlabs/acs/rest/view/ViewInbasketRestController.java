package com.dlabs.acs.rest.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.controller.view.InbasketController;
import com.dlabs.acs.dto.assessement.InbasketDto;
import com.dlabs.acs.dto.rest.BaseResult;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;

@RestController()
@RequestMapping("rest/view/inbasket")
public class ViewInbasketRestController {
	private Logger logger =Logger.getLogger(ViewInbasketRestController.class);
	
	@Autowired
	private InbasketController inbasketController;
	
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public BaseResult post(@ModelAttribute("inbasketDto") InbasketDto inbasketDto, Model model, HttpServletRequest httpRequest) {
		
		inbasketController.post(inbasketDto, model, httpRequest);
		
		BaseResult baseResult = new BaseResult();
		
		baseResult.setRespCode(200);
		baseResult.setResponseObject("success");
		
		logger.debug("END post");
		return baseResult;
	}
	

	@RequestMapping(value = "/spmread", method = RequestMethod.GET)
	public BaseResult spmread(Model model, HttpServletRequest httpRequest) {
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setArticulateSpmRead(true);
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		
		BaseResult baseResult = new BaseResult();
		
		baseResult.setRespCode(200);
		baseResult.setResponseObject("success");
		
		logger.debug("END post");
		return baseResult;
	}
	
	
	@RequestMapping(value = "/memoread", method = RequestMethod.GET)
	public BaseResult memoread(Model model, HttpServletRequest httpRequest) {
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setArticulateMemoRead(true);
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		
		BaseResult baseResult = new BaseResult();
		
		baseResult.setRespCode(200);
		baseResult.setResponseObject("success");
		
		logger.debug("END post");
		return baseResult;
	}
	
	@RequestMapping(value = "/srbread", method = RequestMethod.GET)
	public BaseResult spmRead(Model model, HttpServletRequest httpRequest) {
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setArticulateSrbRead(true);
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		
		BaseResult baseResult = new BaseResult();
		
		baseResult.setRespCode(200);
		baseResult.setResponseObject("success");
		
		logger.debug("END post");
		return baseResult;
	}
}
