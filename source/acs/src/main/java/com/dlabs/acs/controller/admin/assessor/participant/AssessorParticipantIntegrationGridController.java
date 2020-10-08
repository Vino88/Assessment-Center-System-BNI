package com.dlabs.acs.controller.admin.assessor.participant;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.dlabs.acs.dto.assessementresult.ParticipantFinalRatingDetailDto;
import com.dlabs.acs.dto.assessementresult.ParticipantFinalRatingDto;
import com.dlabs.acs.dto.assessementresult.ig.IgFlagDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.participant.ParticipantLogPost;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.factsheet.IProfileService;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;

@Controller
@RequestMapping("/pages/admin/assessor/participant/integrationgrid")
public class AssessorParticipantIntegrationGridController {
	private Logger logger =Logger.getLogger(AssessorParticipantIntegrationGridController.class);
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IProfileService profileService;
	@Autowired
	private IParticipantLogPostService participantLogPostService;
	@Autowired
	private IParticipantCompetencyLibraryFinalResultService participantCompetencyLibraryFinalResultService;
	
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		Profile profile = participantService.getProfileByParticipantId(participant.getId());
		model.addAttribute("profile", profile);
		
		model.addAttribute("competencyLibraries", competencyLibraryService.getAllActive());
		
		
		List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantIdOrderByCompetencyOrder(participant.getId());
		List<Integer> defaultFinalResultList = new ArrayList<Integer>();
		
		int defaultFinalResult = 3;
		int countFinal = 0;
		int total = 0;
		Map<Integer, Integer> mapCountFinalResult = new HashMap<Integer, Integer>();
		for(ParticipantCompetencyLibraryFinalResult pcf : listParticipantCompetencyLibraryFinalResult)
		{
			defaultFinalResult = 3;
			countFinal = 0;
			total = 0;
			
			mapCountFinalResult.put(1, 0);
			mapCountFinalResult.put(2, 0);
			mapCountFinalResult.put(3, 0);
			mapCountFinalResult.put(4, 0);
			mapCountFinalResult.put(5, 0);
			
			
			if(pcf.getLcbRating() > 0)
			{
				mapCountFinalResult.put(pcf.getLcbRating(), mapCountFinalResult.get(pcf.getLcbRating()) + 1);
				countFinal = countFinal + 1;
				total = total + pcf.getLcbRating();
			}
			
			if(pcf.getCapRating() > 0)
			{
				mapCountFinalResult.put(pcf.getCapRating(), mapCountFinalResult.get(pcf.getCapRating()) + 1);
				countFinal = countFinal + 1;
				total = total + pcf.getCapRating();
			}
			
			
			if(pcf.getInbasketRating() > 0)
			{
				mapCountFinalResult.put(pcf.getInbasketRating(), mapCountFinalResult.get(pcf.getInbasketRating()) + 1);
				countFinal = countFinal + 1;
				total = total + pcf.getInbasketRating();
			}
			
			if(pcf.getAnalysisRating() > 0)
			{
				mapCountFinalResult.put(pcf.getAnalysisRating(), mapCountFinalResult.get(pcf.getAnalysisRating()) + 1);
				countFinal = countFinal + 1;
				total = total + pcf.getAnalysisRating();
			}
			
			if(countFinal == 1)
			{
				if(mapCountFinalResult.get(1) > 0)
				{
					defaultFinalResult = 1;
				}
				
				else if(mapCountFinalResult.get(2) > 0)
				{
					defaultFinalResult = 2;
				}
				
				else if(mapCountFinalResult.get(3) > 0)
				{
					defaultFinalResult = 3;
				}
				
				else if(mapCountFinalResult.get(4) > 0)
				{
					defaultFinalResult = 4;
				}
				
				else if(mapCountFinalResult.get(5) > 0)
				{
					defaultFinalResult = 5;
				}
				else
				{
					defaultFinalResult = 3;
				}
			}else if(countFinal == 2)
			{
				if(mapCountFinalResult.get(1) > 1)
				{
					defaultFinalResult = 1;
				}
				
				else if(mapCountFinalResult.get(2) > 1)
				{
					defaultFinalResult = 2;
				}
				
				else if(mapCountFinalResult.get(3) > 1)
				{
					defaultFinalResult = 3;
				}
				
				else if(mapCountFinalResult.get(4) > 1)
				{
					defaultFinalResult = 4;
				}
				
				else if(mapCountFinalResult.get(5) > 1)
				{
					defaultFinalResult = 5;
				}
				else
				{
					defaultFinalResult = total/countFinal;
				}
			}else if(countFinal == 3)
			{
				if(mapCountFinalResult.get(1) > 2)
				{
					defaultFinalResult = 1;
				}
				
				else if(mapCountFinalResult.get(2) > 2)
				{
					defaultFinalResult = 2;
				}
				
				else if(mapCountFinalResult.get(3) > 2)
				{
					defaultFinalResult = 3;
				}
				
				else if(mapCountFinalResult.get(4) > 2)
				{
					defaultFinalResult = 4;
				}
				
				else if(mapCountFinalResult.get(5) > 2)
				{
					defaultFinalResult = 5;
				}
				else
				{
					defaultFinalResult = total/countFinal;
				}
			}else if(countFinal == 4)
			{
				if(mapCountFinalResult.get(1) > 3)
				{
					defaultFinalResult = 1;
				}
				
				else if(mapCountFinalResult.get(2) > 3)
				{
					defaultFinalResult = 2;
				}
				
				else if(mapCountFinalResult.get(3) > 3)
				{
					defaultFinalResult = 3;
				}
				
				else if(mapCountFinalResult.get(4) > 3)
				{
					defaultFinalResult = 4;
				}
				
				else if(mapCountFinalResult.get(5) > 3)
				{
					defaultFinalResult = 5;
				}
				else
				{
					defaultFinalResult = total/countFinal;
				}
			}
			
			
			defaultFinalResultList.add(defaultFinalResult);
		}
		model.addAttribute("listParticipantCompetencyLibraryFinalResult", listParticipantCompetencyLibraryFinalResult);
		model.addAttribute("defaultFinalResultList", defaultFinalResultList);
		
		
		IgFlagDto spmFlag = new IgFlagDto();
		IgFlagDto srbFlag = new IgFlagDto();
		IgFlagDto capFlag = new IgFlagDto();
		
		for(ParticipantCompetencyLibraryFinalResult pc : listParticipantCompetencyLibraryFinalResult)
		{
			if(pc.getInbasketRating() > 0)
			{
				if("NOT_SURE".equals(pc.getInbasketFlag()))
				{
					spmFlag.setNotSure(spmFlag.getNotSure() + 1);
				}else if("ANSWER_CANT_ASSESSED".equals(pc.getInbasketFlag()))
				{
					spmFlag.setAnswer(spmFlag.getAnswer() + 1);
				}else if("NO_FLAG".equals(pc.getInbasketFlag()))
				{
					spmFlag.setNoFlag(spmFlag.getNoFlag() + 1);
				}else
				{
					spmFlag.setEmptyFlag(spmFlag.getEmptyFlag() + 1);
				}
			}
			
			
			if(pc.getAnalysisRating() > 0)
			{
				if("NOT_SURE".equals(pc.getAnalysisFlag()))
				{
					srbFlag.setNotSure(srbFlag.getNotSure() + 1);
				}else if("ANSWER_CANT_ASSESSED".equals(pc.getAnalysisFlag()))
				{
					srbFlag.setAnswer(srbFlag.getAnswer() + 1);
				}else if("NO_FLAG".equals(pc.getAnalysisFlag()))
				{
					srbFlag.setNoFlag(srbFlag.getNoFlag() + 1);
				}else
				{
					srbFlag.setEmptyFlag(srbFlag.getEmptyFlag() + 1);
				}
			}
			
			
			
			if(pc.getCapRating() > 0)
			{
				if("NOT_SURE".equals(pc.getCapFlag()))
				{
					capFlag.setNotSure(capFlag.getNotSure() + 1);
				}else if("ANSWER_CANT_ASSESSED".equals(pc.getCapFlag()))
				{
					capFlag.setAnswer(capFlag.getAnswer() + 1);
				}else if("NO_FLAG".equals(pc.getCapFlag()))
				{
					capFlag.setNoFlag(capFlag.getNoFlag() + 1);
				}else
				{
					capFlag.setEmptyFlag(capFlag.getEmptyFlag() + 1);
				}
			}
		}
		model.addAttribute("spmFlag", spmFlag);
		model.addAttribute("srbFlag", srbFlag);
		model.addAttribute("capFlag", capFlag);
		
		
		logger.debug("END");
		return "/pages/admin/assessor/participant/integrationgrid/view";
	}
}
