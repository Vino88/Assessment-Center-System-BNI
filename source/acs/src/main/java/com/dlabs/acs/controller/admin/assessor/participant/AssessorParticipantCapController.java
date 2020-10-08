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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.dto.assessementresult.ParticipantAssessmentResultDto;
import com.dlabs.acs.dto.assessementresult.ParticipantCapCompetencyLibraryCapDto;
import com.dlabs.acs.dto.assessementresult.ParticipantCompetencyResultDisplayDto;
import com.dlabs.acs.dto.assessementresult.ParticipantListAssessmentResultDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantCap;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.entity.cap.CompetencyLibraryCap;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;
import com.dlabs.acs.entity.participant.ParticipantLogPost;
import com.dlabs.acs.service.intf.assessement.IParticipantCapService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;
import com.dlabs.acs.service.intf.cap.ICapService;
import com.dlabs.acs.service.intf.cap.ICompetencyLibraryCapService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryBehaviourService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;

@Controller
@RequestMapping("/pages/admin/assessor/participant/cap")
public class AssessorParticipantCapController {
	private Logger logger =Logger.getLogger(AssessorParticipantCapController.class);
	
	@Autowired
	private IParticipantService participantService;

	@Autowired
	private IParticipantCompetencyLibraryFinalResultService participantCompetencyLibraryFinalResultService;
	
	@Autowired
	private ICompetencyLibraryBehaviourService competencyLibraryBehaviourService;
	
	@Autowired
	private ICapService capService;
	@Autowired
	private ICompetencyLibraryCapService competencyLibraryCapService;
	@Autowired
	private IParticipantCapService participantCapService;
	
	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public String list(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		List<Cap> listCap = capService.getAll();
		
		List<CompetencyLibraryCap> listCompetencyCap = competencyLibraryCapService.getAll();
		
		List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantId(id);
		
		Map<Long, Integer> mapCompetencyIdAndFlag = new HashMap<Long, Integer>();
		for(ParticipantCompetencyLibraryFinalResult pc : listParticipantCompetencyLibraryFinalResult)
		{
			mapCompetencyIdAndFlag.put(pc.getCompetencyLibrary().getId(), pc.getCapRating());
		}
		
		Map<Long, Boolean> mapCapIdAndFlag = new HashMap<Long, Boolean>();
		
		Integer rating = null;
		boolean flag  = false;
		for(CompetencyLibraryCap cl : listCompetencyCap)
		{
			if(mapCapIdAndFlag.get(cl.getCap().getId()) == null || mapCapIdAndFlag.get(cl.getCap().getId()) == false)
			{
				rating = mapCompetencyIdAndFlag.get(cl.getCompetencyLibrary().getId());
				if(rating != null)
				{
					flag = rating > 0;
				}
				else
				{
					flag = false;
				}
				
				mapCapIdAndFlag.put(cl.getCap().getId(),  flag);
			}
		}
		
		
		ParticipantCapCompetencyLibraryCapDto participantCapCompetencyLibraryCapDto = null;		
		List<ParticipantCapCompetencyLibraryCapDto> listParticipantCap = new ArrayList<ParticipantCapCompetencyLibraryCapDto> ();
		
		for(Cap cap : listCap)
		{
			participantCapCompetencyLibraryCapDto = new ParticipantCapCompetencyLibraryCapDto();
			participantCapCompetencyLibraryCapDto.setCap(cap);
			participantCapCompetencyLibraryCapDto.setFinished(mapCapIdAndFlag.get(cap.getId()));
			listParticipantCap.add(participantCapCompetencyLibraryCapDto);
		}
		
		
		model.addAttribute("listParticipantCap", listParticipantCap);
		logger.debug("END");
		return "/pages/admin/assessor/participant/cap/list";
	}
	
	@RequestMapping(value = "/capdetail/{id}/{capId}", method = RequestMethod.GET)
	public String capdetail(Model model, @PathVariable("id")Long id, @PathVariable("capId")Long capId) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		
		Cap cap =  capService.findById(capId);
		model.addAttribute("cap", cap);
		
		ParticipantCap participantCap = participantCapService.getByParticipantIdAndCapId(id, capId);
		model.addAttribute("participantCap", participantCap);
		
		List<CompetencyLibrary> competencyLibraries = competencyLibraryCapService.getCompetenciesByCapId(capId);
		
		List<ParticipantCapCompetencyLibraryCapDto> listParticipantCapCompetencyLibraryCapDto = new ArrayList<ParticipantCapCompetencyLibraryCapDto> ();
		ParticipantCapCompetencyLibraryCapDto participantCapCompetencyLibraryCapDto = null;
		
		for(CompetencyLibrary competencyLibrary : competencyLibraries)
		{
			participantCapCompetencyLibraryCapDto = new ParticipantCapCompetencyLibraryCapDto();
			
			
			participantCapCompetencyLibraryCapDto.setCompetencyLibrary(competencyLibrary);
			participantCapCompetencyLibraryCapDto.setCompetencyLibraryBehaviours(competencyLibraryBehaviourService.getByCompetencyLibraryId(competencyLibrary.getId()));
			
			participantCapCompetencyLibraryCapDto.setParticipantCompetencyLibraryFinalResult(participantCompetencyLibraryFinalResultService.getByParticipantIdAndCompetencyLibraryId(id, competencyLibrary.getId()));
			
			listParticipantCapCompetencyLibraryCapDto.add(participantCapCompetencyLibraryCapDto);
		}
		
		model.addAttribute("listParticipantCapCompetencyLibraryCapDto", listParticipantCapCompetencyLibraryCapDto);
		
		logger.debug("END");
		return "/pages/admin/assessor/participant/cap/capdetail";
	}
	
}
