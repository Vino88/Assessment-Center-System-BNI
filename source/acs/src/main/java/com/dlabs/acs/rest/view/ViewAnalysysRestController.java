package com.dlabs.acs.rest.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.controller.view.AnalysysController;
import com.dlabs.acs.dto.assessement.ParticipantAnalysysDto;
import com.dlabs.acs.dto.rest.BaseResult;

@RestController()
@RequestMapping("rest/view/analysys")
public class ViewAnalysysRestController {
	private Logger logger =Logger.getLogger(ViewAnalysysRestController.class);
	@Autowired
	private AnalysysController analysysController;
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public BaseResult post(@ModelAttribute("participantAnalysysDto") ParticipantAnalysysDto participantAnalysysDto, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		analysysController.post(participantAnalysysDto, model, httpRequest);
		
		BaseResult baseResult = new BaseResult();
		
		baseResult.setRespCode(200);
		baseResult.setResponseObject("success");
		
		logger.debug("END post");
		return baseResult;
	}
	
	@RequestMapping(value = "/detail/visionspeech", method = RequestMethod.POST)
	public BaseResult postVisionSpeech(@ModelAttribute("participantAnalysysDto") ParticipantAnalysysDto participantAnalysysDto, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		analysysController.postVisionSpeech(participantAnalysysDto, model, httpRequest);
		
		BaseResult baseResult = new BaseResult();
		
		baseResult.setRespCode(200);
		baseResult.setResponseObject("success");
		
		logger.debug("END post");
		return baseResult;
	}
}
