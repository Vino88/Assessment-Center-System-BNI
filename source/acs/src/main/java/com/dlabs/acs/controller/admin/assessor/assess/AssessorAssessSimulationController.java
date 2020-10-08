package com.dlabs.acs.controller.admin.assessor.assess;

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
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantInbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.participant.ParticipantLogPost;
import com.dlabs.acs.service.intf.assessement.IParticipantInbasketInboxInbasketQuestionService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryBehaviourService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.inbasket.ICompetencyLibraryInbasketInboxInbasketQuestionService;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxInbasketQuestionService;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;

@Controller
@RequestMapping("/pages/admin/assessor/assess/simulation")
public class AssessorAssessSimulationController {
	private Logger logger =Logger.getLogger(AssessorAssessSimulationController.class);
	
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantLogPostService participantLogPostService;
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
	@Autowired
	private IInbasketInboxInbasketQuestionService inbasketInboxInbasketQuestionService;
	
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
		return "/pages/admin/assessor/assess/simulation/view";
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
			}
			else
			{
				participantInbasketInboxInbasketQuestion = new ParticipantInbasketInboxInbasketQuestion();
				participantInbasketInboxInbasketQuestion.setInbasketInboxInbasketQuestion(iiq);
			}
			listParticipantIiiq.add(participantInbasketInboxInbasketQuestion);
		}
		
		model.addAttribute("listParticipantIiiq", listParticipantIiiq);
		
		List<CompetencyLibraryBehaviour> competencyLibraryBehaviours = competencyLibraryBehaviourService.getByCompetencyLibraryId(competencyId);
		model.addAttribute("competencyLibraryBehaviours", competencyLibraryBehaviours);
		
		
		ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantIdAndCompetencyLibraryId(id, competencyId);
		model.addAttribute("participantCompetencyLibraryFinalResult", participantCompetencyLibraryFinalResult);
		logger.debug("END");
		return "/pages/admin/assessor/assess/simulation/competency";
	}
	
	
	@RequestMapping(value = "/competency/assess", method = RequestMethod.POST)
	public String assess(@Valid @ModelAttribute("dataForm") ParticipantAssessmentResultDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		logger.debug("BEGIN");
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
			return "redirect:/controller/pages/admin/assessor/assess/cap/competency/"+dataForm.getParticipantId()+"/"+dataForm.getCompetencyId();    
		}
		
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);

		Date now = new Date();
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(userAdmin.getEmail());
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(userAdmin.getEmail());
		commonFields.setLastModifiedDate(now);
		
		ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantIdAndCompetencyLibraryId(dataForm.getParticipantId(), dataForm.getCompetencyId());
		
		if(participantCompetencyLibraryFinalResult == null)
		{
			participantCompetencyLibraryFinalResult = new ParticipantCompetencyLibraryFinalResult();
			participantCompetencyLibraryFinalResult.setCommonFields(commonFields);
			
			Participant participant =participantService.findById(dataForm.getParticipantId());
			participantCompetencyLibraryFinalResult.setParticipant(participant);
			
			CompetencyLibrary competencyLibrary = competencyLibraryService.findById(dataForm.getCompetencyId());
			participantCompetencyLibraryFinalResult.setCompetencyLibrary(competencyLibrary);
		}
		
		participantCompetencyLibraryFinalResult.getCommonFields().setLastModifiedBy(userAdmin.getEmail());
		participantCompetencyLibraryFinalResult.getCommonFields().setLastModifiedDate(now);
		
		participantCompetencyLibraryFinalResult.setInbasketRating(dataForm.getRating());
		participantCompetencyLibraryFinalResult.setInbasketNotes(dataForm.getNotes());
		participantCompetencyLibraryFinalResult.setInbasketFlag(dataForm.getAsessmentFlag());
		
		participantCompetencyLibraryFinalResultService.saveOrUpdate(participantCompetencyLibraryFinalResult);
		
		logger.debug("END");
		return "redirect:/controller/pages/admin/assessor/assess/simulation/view/"+dataForm.getParticipantId();
	}
	
	@RequestMapping(value = "/finish/{id}", method = RequestMethod.POST)
	public String finish(Model model, @PathVariable("id")Long id, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		logger.debug("BEGIN");
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		
		
		Participant participant =participantService.findById(id);
		participant.setParticipantStatus(ParticipantStatus.PARTIAL_RATING);
		participant.getCommonFields().setLastModifiedBy(userAdmin.getEmail());
		participant.getCommonFields().setLastModifiedDate(new Date());
		
		participantService.saveOrUpdate(participant);
		
		
		ParticipantLogPost participantLogPost = participantLogPostService.getByParticipantIdFirst(id);
		
		participantLogPost.setAssessSimulationFinishTime(new Date());
		
		participantLogPost.getCommonFields().setLastModifiedBy(userAdmin.getEmail());
		participantLogPost.getCommonFields().setLastModifiedDate(new Date());
		
		participantLogPostService.saveOrUpdate(participantLogPost);
		
		return "redirect:/controller/pages/admin/assessor/assess/home/view/"+id;
	}
	
}
