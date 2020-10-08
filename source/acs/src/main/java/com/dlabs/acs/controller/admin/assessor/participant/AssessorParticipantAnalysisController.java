package com.dlabs.acs.controller.admin.assessor.participant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.dlabs.acs.dto.assessementresult.ParticipantCompetencyResultDisplayDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantAnalysys;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;
import com.dlabs.acs.entity.participant.ParticipantLogPost;
import com.dlabs.acs.service.intf.analysys.ICompetencyLibraryAnalysysService;
import com.dlabs.acs.service.intf.assessement.IParticipantAnalysysService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryBehaviourService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;

@Controller
@RequestMapping("/pages/admin/assessor/participant/analysis")
public class AssessorParticipantAnalysisController {
	private Logger logger =Logger.getLogger(AssessorParticipantAnalysisController.class);
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantCompetencyLibraryFinalResultService participantCompetencyLibraryFinalResultService;
	
	
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	@Autowired
	private ICompetencyLibraryBehaviourService competencyLibraryBehaviourService;
	
	@Autowired
	private ICompetencyLibraryAnalysysService competencyLibraryAnalysysService;
	@Autowired
	private IParticipantAnalysysService participantCompetencyLibraryAnalysysService;
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		List<CompetencyLibrary> listCompetency = competencyLibraryAnalysysService.getDistinctCompetencyLibrary();
		List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantId(id);
		
		
		List<ParticipantCompetencyResultDisplayDto> listParticipantCompetencyResultDisplayDto = new ArrayList<ParticipantCompetencyResultDisplayDto> ();
		ParticipantCompetencyResultDisplayDto participantCompetencyResultDisplayDto = null;
		for(CompetencyLibrary cl : listCompetency)
		{
			participantCompetencyResultDisplayDto = new ParticipantCompetencyResultDisplayDto();
			
			participantCompetencyResultDisplayDto.setCompetencyLibrary(cl);
			
			for(ParticipantCompetencyLibraryFinalResult pc : listParticipantCompetencyLibraryFinalResult)
			{
				if(pc.getCompetencyLibrary().getId().equals(cl.getId()))
				{
					participantCompetencyResultDisplayDto.setParticipantCompetencyLibraryFinalResult(pc);
				}
			}
			
			listParticipantCompetencyResultDisplayDto.add(participantCompetencyResultDisplayDto);
		}
		
		
		model.addAttribute("listParticipantCompetencyResultDisplayDto", listParticipantCompetencyResultDisplayDto);
		logger.debug("END");
		return "/pages/admin/assessor/participant/analysis/view";
	}
	
	@RequestMapping(value = "/competency/{id}/{competencyId}", method = RequestMethod.GET)
	public String competency(Model model, @PathVariable("id")Long id, @PathVariable("competencyId")Long competencyId) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		CompetencyLibrary competencyLibrary = competencyLibraryService.findById(competencyId);
		model.addAttribute("competencyLibrary", competencyLibrary);
		
		List<Analysys> listClAnalysis =  competencyLibraryAnalysysService.getAnalysisByCompetencyId(competencyId);
		
		List<ParticipantAnalysys> listParticipantClAnalysis = new ArrayList<ParticipantAnalysys>();
		
		ParticipantAnalysys participantCompetencyLibraryAnalysys = null;
		for(Analysys clAnalysis : listClAnalysis){
			participantCompetencyLibraryAnalysys = participantCompetencyLibraryAnalysysService.getByPartIdAndAnalysysId(id, clAnalysis.getId());
			if(participantCompetencyLibraryAnalysys == null)
			{
				participantCompetencyLibraryAnalysys = new ParticipantAnalysys();
			}
			participantCompetencyLibraryAnalysys.setAnalysys(clAnalysis);
			listParticipantClAnalysis.add(participantCompetencyLibraryAnalysys);
		}
		
		model.addAttribute("listParticipantClAnalysis", listParticipantClAnalysis);
		
		List<CompetencyLibraryBehaviour> competencyLibraryBehaviours = competencyLibraryBehaviourService.getByCompetencyLibraryId(competencyId);
		model.addAttribute("competencyLibraryBehaviours", competencyLibraryBehaviours);
		
		
		ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantIdAndCompetencyLibraryId(id, competencyId);
		model.addAttribute("participantCompetencyLibraryFinalResult", participantCompetencyLibraryFinalResult);
		logger.debug("END");
		return "/pages/admin/assessor/participant/analysis/competency";
	}
}
