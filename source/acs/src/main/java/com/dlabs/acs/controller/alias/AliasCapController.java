package com.dlabs.acs.controller.alias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.dlabs.acs.controller.view.FactsheetController;
import com.dlabs.acs.dto.assessement.CapDto;
import com.dlabs.acs.dto.assessement.ParticipantCapDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantCap;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.service.intf.assessement.IParticipantCapService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.cap.ICapService;

@Controller
@RequestMapping("/pages/alias/cap")
public class AliasCapController {
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
		
		model.addAttribute("currentIndex", currentIndex);
		
		
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
		
		
		logger.debug("END");
		return "/alias/cap/view";
	}
	
	
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	public String finish(@ModelAttribute("capDto") CapDto capDto, Model model, HttpServletRequest httpRequest, final RedirectAttributes redirectAttributes)
	{
		logger.debug("BEGIN post");
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
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
	
		
		logger.debug("END post");
		return "redirect:/controller/pages/alias/lcb/home";
	}
	
}
