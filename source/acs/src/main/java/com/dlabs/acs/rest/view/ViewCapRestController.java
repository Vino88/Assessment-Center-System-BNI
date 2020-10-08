package com.dlabs.acs.rest.view;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.dto.assessement.CapDto;
import com.dlabs.acs.dto.assessement.ParticipantCapDto;
import com.dlabs.acs.dto.rest.BaseResult;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantCap;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.service.intf.assessement.IParticipantCapService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.cap.ICapService;

@RestController()
@RequestMapping("rest/view/cap")
public class ViewCapRestController {
	private Logger logger =Logger.getLogger(ViewCapRestController.class);
	
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	
	@Autowired
	private ICapService capService;
	
	@Autowired
	private IParticipantCapService participantCapService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST )
	public BaseResult post(@ModelAttribute("capDto") CapDto capDto, Model model, HttpServletRequest httpRequest)
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
		int index = 0;
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
			
			if(index == capDto.getFromStep())
			{
				if(capDto.getLastTimer() != null)
				{
					participantCap.setLastTimer(capDto.getLastTimer().replaceAll(",", ""));
				}
				
			}
			participantCapService.saveOrUpdate(participantCap);
			
			index = index + 1;
		}
		
		
		participantLogDuring.setCapLastDraftTime(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		BaseResult baseResult = new BaseResult();
		
		baseResult.setRespCode(200);
		baseResult.setResponseObject("success");
		
		logger.debug("END post");
		return baseResult;
	}
}
