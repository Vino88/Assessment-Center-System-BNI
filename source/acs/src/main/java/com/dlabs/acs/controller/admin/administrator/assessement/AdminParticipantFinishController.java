package com.dlabs.acs.controller.admin.administrator.assessement;

import java.util.Date;

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
import com.dlabs.acs.dto.assessement.participantfinish.ParticipantFinishDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.participant.ParticipantLogPost;
import com.dlabs.acs.service.intf.IUserAdminService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;

@Controller
@RequestMapping("/pages/admin/administrator/assessement/participantfinish")
public class AdminParticipantFinishController
{
	private Logger logger = Logger.getLogger(AdminParticipantFinishController.class);
	
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantLogPostService participantLogPostService;
	@Autowired
	private IUserAdminService userAdminService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		return "/pages/admin/administrator/assessement/participantfinish/view";
	}
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable("id")Long id) {
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		ParticipantLogPost participantLogPost = participantLogPostService.getByParticipantIdFirst(id);
		model.addAttribute("participantLogPost", participantLogPost);
		
		return "/pages/admin/administrator/assessement/participantfinish/detail";
	}
	
	@RequestMapping(value = "/assign", method = RequestMethod.POST)
	public String assign(@Valid @ModelAttribute("dataForm") ParticipantFinishDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
			return detail(model, dataForm.getParticipantId());        
		}
		
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		
		Participant participant =participantService.findById(dataForm.getParticipantId());
		ParticipantLogPost participantLogPost = participantLogPostService.getByParticipantIdFirst(dataForm.getParticipantId());
		if(participantLogPost == null)
		{
			participantLogPost = new ParticipantLogPost();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			participantLogPost.setCommonFields(commonFields);
		}
		participantLogPost.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
		participantLogPost.getCommonFields().setLastModifiedDate(new Date());
		
		if(dataForm.getCapAssessorUserId() != null)
		{
			UserAdmin capAssessor = userAdminService.findById(dataForm.getCapAssessorUserId());
			participantLogPost.setCapAssessor(capAssessor);
			participantLogPost.setAssessStartTime(new Date());
			
			if(ParticipantStatus.PARTICIPANT_FINISH.equals(participant.getParticipantStatus()))
			{
				participant.setParticipantStatus(ParticipantStatus.ASSIGNED_TO_ASSESSOR);
			}
			
			
		}
		
		
		if(dataForm.getSimulationAssessorUserId() != null)
		{
			UserAdmin simulationAssessor = userAdminService.findById(dataForm.getSimulationAssessorUserId());
			participantLogPost.setSimulationAssessor(simulationAssessor);
			participantLogPost.setAssessStartTime(new Date());
			
			if(ParticipantStatus.PARTICIPANT_FINISH.equals(participant.getParticipantStatus()))
			{
				participant.setParticipantStatus(ParticipantStatus.ASSIGNED_TO_ASSESSOR);
			}
		}
		
		if(dataForm.getAnalysysAssessorUserId() != null)
		{
			UserAdmin analysysAssessor = userAdminService.findById(dataForm.getAnalysysAssessorUserId());
			participantLogPost.setAnalysysAssessor(analysysAssessor);
			participantLogPost.setAssessStartTime(new Date());
			if(ParticipantStatus.PARTICIPANT_FINISH.equals(participant.getParticipantStatus()))
			{
				participant.setParticipantStatus(ParticipantStatus.ASSIGNED_TO_ASSESSOR);
			}
		}
		
		if(dataForm.getIntegrationGridAssessorUserId() != null)
		{
			UserAdmin integrationGridAssessor = userAdminService.findById(dataForm.getIntegrationGridAssessorUserId());
			participantLogPost.setIntegrationGridAssessor(integrationGridAssessor);
			participantLogPost.setAssessStartTime(new Date());
			if(ParticipantStatus.PARTICIPANT_FINISH.equals(participant.getParticipantStatus()))
			{
				participant.setParticipantStatus(ParticipantStatus.ASSIGNED_TO_ASSESSOR);
			}
		}
		
		if(dataForm.getReviewerAssessorUserId() != null)
		{
			UserAdmin reviewer = userAdminService.findById(dataForm.getReviewerAssessorUserId());
			participantLogPost.setReviewer(reviewer);
			participantLogPost.setAssessStartTime(new Date());
			if(ParticipantStatus.PARTICIPANT_FINISH.equals(participant.getParticipantStatus()))
			{
				participant.setParticipantStatus(ParticipantStatus.ASSIGNED_TO_ASSESSOR);
			}
		}
		
		
		
		
		participantLogPost.setParticipant(participant);
		participantLogPostService.saveOrUpdate(participantLogPost);
		
		
		
		participant.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
		participant.getCommonFields().setLastModifiedDate(new Date());
		
		participantService.saveOrUpdate(participant);
		redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
		return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+participant.getBatch().getId();
	}
}