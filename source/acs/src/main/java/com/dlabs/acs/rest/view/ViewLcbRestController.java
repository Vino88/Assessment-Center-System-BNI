package com.dlabs.acs.rest.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.dto.assessement.LcbDto;
import com.dlabs.acs.dto.rest.BaseResult;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.service.business.lcb.LcbBusinessService;

@RestController()
@RequestMapping("rest/view/lcb")
public class ViewLcbRestController {
	@Autowired
	private LcbBusinessService lcbBusinessService;
	
	@RequestMapping(value = "/saveasdraft", method = RequestMethod.POST)
	public BaseResult post(@ModelAttribute("lcbDto") LcbDto lcbDto, Model model, HttpServletRequest httpRequest, final RedirectAttributes redirectAttributes)
	{
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		lcbBusinessService.dtoToEntity(lcbDto.getListParticipantLcbDto(), participant);
		
		BaseResult baseResult = new BaseResult();
		
		baseResult.setRespCode(200);
		baseResult.setResponseObject("success");
		
		
		return baseResult;
	}
}
