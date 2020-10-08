package com.dlabs.acs.controller.alias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.controller.view.LcbController;
import com.dlabs.acs.dto.assessement.LcbDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantLcb;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.lcb.Lcb;
import com.dlabs.acs.entity.lcb.enumeration.LcbCategory;
import com.dlabs.acs.service.business.lcb.LcbBusinessService;
import com.dlabs.acs.service.intf.assessement.IParticipantLcbService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.lcb.ILcbService;
import com.dlabs.acs.util.Validator;

@Controller
@RequestMapping("/pages/alias/lcb")
public class AliasLcbController {
	private Logger logger =Logger.getLogger(LcbController.class);
	@Autowired
	private ILcbService lcbService;
	@Autowired
	private LcbBusinessService lcbBusinessService;
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	@Autowired
	private IParticipantLcbService participantLcbService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		List<ParticipantLcb> listParticipantLcb = participantLcbService.getByParticipantId(participant.getId());
		
		if(listParticipantLcb == null || listParticipantLcb.size() == 0)
		{
			List<Lcb> list = lcbService.getAll();
			
			List<Lcb> list1 = new ArrayList<Lcb>();
			List<Lcb> othersLcb= new ArrayList<Lcb>();
			
			for(Lcb lcb : list)
			{
				if(LcbCategory.LCB1.equals(lcb.getLbCategory()))
				{
					list1.add(lcb);
				}
				else
				{
					othersLcb.add(lcb);
				}
			}
			
			
			List<Integer> listInteger = new ArrayList<Integer>(list1.size());
			
			if(participantLogDuring.getLcbOrder() == null)
			{
				if(Validator.isNull(participantLogDuring.getLcbOrder()) && list1 != null && list1.size() > 0)
				{
					for(int i=0;i<list1.size();i++)
					{
						listInteger.add(i);
					}
					
					Collections.shuffle(listInteger);
					participantLogDuring.setLcbOrder(StringUtils.join(listInteger, ","));
					
				}
				
				participantLogDuringService.saveOrUpdate(participantLogDuring);
				httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
			}
			try
			{
			List<Lcb> list1Shuffled = new ArrayList<Lcb>(list1.size());
			
			List<String> listString = Arrays.asList(participantLogDuring.getLcbOrder().split("\\s*,\\s*"));
			
			listInteger = new ArrayList<Integer>(listInteger.size());
			for(String order : listString)
			{
				listInteger.add(Integer.parseInt(order));
			}
			
			
			
			for(Integer index : listInteger)
			{
				Lcb lcb = list1.get(index);
				list1Shuffled.add(lcb);
			}
			
			list1 = list1Shuffled;
			}catch(Exception e) {
				e.printStackTrace();
			};
			
			
			ParticipantLcb participantLcb = null;
			listParticipantLcb = new ArrayList<ParticipantLcb>();
			
			Date now = new Date();
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(participant.getNik());
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(participant.getNik());
			commonFields.setLastModifiedDate(now);
			
			for(Lcb lcb : list1)
			{
				participantLcb = new ParticipantLcb();
				
				participantLcb.setLcb(lcb);
				participantLcb.setParticipant(participant);
				participantLcb.setCommonFields(commonFields);
				
				listParticipantLcb.add(participantLcb);
			}
			
			for(Lcb lcb : othersLcb)
			{
				participantLcb = new ParticipantLcb();
				
				participantLcb.setLcb(lcb);
				participantLcb.setParticipant(participant);
				participantLcb.setCommonFields(commonFields);
				
				listParticipantLcb.add(participantLcb);
			}
			
			participantLcbService.save(listParticipantLcb);
			
		}
		
		model.addAttribute("listParticipantLcb", listParticipantLcb);
		
		logger.debug("END");
		return "/alias/lcb/view";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String post(@ModelAttribute("lcbDto") LcbDto lcbDto, Model model, HttpServletRequest httpRequest, final RedirectAttributes redirectAttributes)
	{
		logger.debug("BEGIN post");
		logger.debug("lcbDto = " +lcbDto);
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		lcbBusinessService.evaluate(lcbDto.getListParticipantLcbDto(), participant);
		
		logger.debug("END post");
		
		return "redirect:/controller/pages/alias/inbasket";
	}
	
}
