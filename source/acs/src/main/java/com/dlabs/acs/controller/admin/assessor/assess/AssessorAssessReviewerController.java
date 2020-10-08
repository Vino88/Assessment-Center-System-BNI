package com.dlabs.acs.controller.admin.assessor.assess;

import java.util.Date;

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
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.enumeration.DisabledReason;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.participant.ParticipantLogPost;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;
import com.dlabs.acs.service.intf.factsheet.IProfileService;
import com.dlabs.acs.service.intf.participant.IBatchService;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;

@Controller
@RequestMapping("/pages/admin/assessor/assess/reviewer")
public class AssessorAssessReviewerController extends AssessorAssessIntegrationGridController{
	
	private Logger logger =Logger.getLogger(AssessorAssessReviewerController.class);
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IProfileService profileService;
	@Autowired
	private IParticipantLogPostService participantLogPostService;
	@Autowired
	private IParticipantCompetencyLibraryFinalResultService participantCompetencyLibraryFinalResultService;
	@Autowired
	private IBatchService batchService;
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id")Long id) {
		super.view(model, id);
		return "/pages/admin/assessor/assess/reviewer/view";
	}
	
	@RequestMapping(value = "/rating/{id}", method = RequestMethod.POST)
	public String rating(@Valid @ModelAttribute("dataForm") ParticipantFinalRatingDto dataForm,@PathVariable("id")Long id, Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
	{
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		ParticipantCompetencyLibraryFinalResult pc = null;
		Date now = new Date();
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(userAdmin.getEmail());
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(userAdmin.getEmail());
		commonFields.setLastModifiedDate(now);
		
		for(ParticipantFinalRatingDetailDto dataRating : dataForm.getIgRating())
		{
			if(dataRating.getCompetencyId() == null)
			{
				continue;
			}
			pc = participantCompetencyLibraryFinalResultService.getByParticipantIdAndCompetencyLibraryId(id, dataRating.getCompetencyId());
			if(pc == null)
			{
				pc = new ParticipantCompetencyLibraryFinalResult();
				pc.setCommonFields(commonFields);
			}
			pc.getCommonFields().setLastModifiedBy(userAdmin.getEmail());
			pc.getCommonFields().setLastModifiedDate(new Date());
			
			if(dataRating.getFinalRating() != null)
			{
				pc.setFinalRating(dataRating.getFinalRating());
			}
			
			participantCompetencyLibraryFinalResultService.saveOrUpdate(pc);
			
		}
		participant.setAssessmentRating(dataForm.getAssessmentRating());
		participant.getCommonFields().setLastModifiedBy(userAdmin.getEmail());
		participant.getCommonFields().setLastModifiedDate(new Date());
		participantService.saveOrUpdate(participant);
		
		Profile profile = participantService.getProfileByParticipantId(participant.getId());
		profile.setStrong(dataForm.getStrong());
		profile.setWeakness(dataForm.getWeakness());
		profile.setPersonalCompetency(dataForm.getPersonalCompetency());
		profile.setLeadershipCompetency(dataForm.getLeadershipCompetency());
		profile.setFunctionalCompetency(dataForm.getFunctionalCompetency());
		profileService.saveOrUpdate(profile);
		
		
		logger.debug("END");
		return "redirect:/controller/pages/admin/assessor/assess/home/view/"+participant.getId();
	}
	
	@RequestMapping(value = "/ratingandfinish/{id}", method = RequestMethod.POST)
	public String ratingandfinish(@Valid @ModelAttribute("dataForm") ParticipantFinalRatingDto dataForm,@PathVariable("id")Long id, Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		logger.debug("BEGIN");
		rating(dataForm, id , model, redirectAttributes, request);
		
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		
		
		Participant participant =participantService.findById(id);
		participant.setDisabledTime(new Date());
		participant.setDisabledReason(DisabledReason.ASSESSEMENT_FINISH);
		participant.setParticipantStatus(ParticipantStatus.ASSESSEMENT_COMPLETE);
		
		participant.getCommonFields().setLastModifiedBy(userAdmin.getEmail());
		participant.getCommonFields().setLastModifiedDate(new Date());
		
		participantService.saveOrUpdate(participant);
		
		
		ParticipantLogPost participantLogPost = participantLogPostService.getByParticipantIdFirst(id);
		
		participantLogPost.setAssessReviewerFinishTime(new Date());
		participantLogPost.setAssessFinishTime(new Date());
		
		participantLogPost.getCommonFields().setLastModifiedBy(userAdmin.getEmail());
		participantLogPost.getCommonFields().setLastModifiedDate(new Date());
		
		participantLogPostService.saveOrUpdate(participantLogPost);
		
		
		if(participantService.countAssessementFinishByBatchId(participant.getBatch().getId()) <= 0 )
		{
			participant.getBatch().setInprogress(false);
			batchService.saveOrUpdate(participant.getBatch());
		}
			
		
		logger.debug("END");
		return "redirect:/controller/pages/admin/assessor/assess/home/view/"+id;
	}
}
