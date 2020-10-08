package com.dlabs.acs.controller.admin.assessor.participant;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dlabs.acs.dto.assessementresult.ParticipantCompetencyResultDisplayDto;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantInbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;
import com.dlabs.acs.service.intf.assessement.IParticipantInbasketInboxInbasketQuestionService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryBehaviourService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.inbasket.ICompetencyLibraryInbasketInboxInbasketQuestionService;

@Controller
@RequestMapping("/pages/admin/assessor/participant/simulation")
public class AssessorParticipantSimulationController {
	private Logger logger =Logger.getLogger(AssessorParticipantSimulationController.class);
	
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantCompetencyLibraryFinalResultService participantCompetencyLibraryFinalResultService;
	
	
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	@Autowired
	private ICompetencyLibraryBehaviourService competencyLibraryBehaviourService;
	
	@Autowired
	private ICompetencyLibraryInbasketInboxInbasketQuestionService competencyLibraryInbasketInboxInbasketQuestionService;
	@Autowired
	private IParticipantInbasketInboxInbasketQuestionService participantInbasketInboxInbasketQuestionService;
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		List<CompetencyLibrary> listCompetency = competencyLibraryInbasketInboxInbasketQuestionService.getDistinctCompetencyLibrary();
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
		return "/pages/admin/assessor/participant/simulation/view";
	}
	
	
	@RequestMapping(value = "/competency/{id}/{competencyId}", method = RequestMethod.GET)
	public String competency(Model model, @PathVariable("id")Long id, @PathVariable("competencyId")Long competencyId) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		CompetencyLibrary competencyLibrary = competencyLibraryService.findById(competencyId);
		model.addAttribute("competencyLibrary", competencyLibrary);
		
		List<InbasketInboxInbasketQuestion> listIiiq =  competencyLibraryInbasketInboxInbasketQuestionService.getIiiqByCompetencyId(competencyId);
		
		List<ParticipantInbasketInboxInbasketQuestion> listParticipantIiiq = new ArrayList<ParticipantInbasketInboxInbasketQuestion>();
		
		ParticipantInbasketInboxInbasketQuestion participantInbasketInboxInbasketQuestion = null;
		for(InbasketInboxInbasketQuestion iiq : listIiiq){
			participantInbasketInboxInbasketQuestion = participantInbasketInboxInbasketQuestionService.getByPartIdAndIiibId(participant.getId(), iiq.getId());
			
			if(participantInbasketInboxInbasketQuestion != null)
			{
				participantInbasketInboxInbasketQuestion.setInbasketInboxInbasketQuestion(iiq);
				
				listParticipantIiiq.add(participantInbasketInboxInbasketQuestion);
			}
			
		}
		
		model.addAttribute("listParticipantIiiq", listParticipantIiiq);
		
		List<CompetencyLibraryBehaviour> competencyLibraryBehaviours = competencyLibraryBehaviourService.getByCompetencyLibraryId(competencyId);
		model.addAttribute("competencyLibraryBehaviours", competencyLibraryBehaviours);
		
		
		ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantIdAndCompetencyLibraryId(id, competencyId);
		model.addAttribute("participantCompetencyLibraryFinalResult", participantCompetencyLibraryFinalResult);
		logger.debug("END");
		return "/pages/admin/assessor/participant/simulation/competency";
	}
}
