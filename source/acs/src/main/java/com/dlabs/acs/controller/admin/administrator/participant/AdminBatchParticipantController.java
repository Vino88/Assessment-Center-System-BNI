package com.dlabs.acs.controller.admin.administrator.participant;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.dto.assessement.ParticipantDto;
import com.dlabs.acs.dto.assessementresult.ParticipantFinalRatingDetailDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.ParticipantLogPre;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.service.intf.IUserAdminService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogPreService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.factsheet.IProfileService;
import com.dlabs.acs.service.intf.masterdata.IEmployeeService;
import com.dlabs.acs.service.intf.participant.IBatchService;
import com.dlabs.acs.util.DateUtil;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.mail.MailUtil;

@Controller
@RequestMapping("/pages/admin/administrator/participant/batch/participant")
public class AdminBatchParticipantController {
	private Logger logger = Logger.getLogger(AdminBatchParticipantController.class);
	@Autowired
	private IBatchService batchService;
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	@Autowired
	private IParticipantLogPreService participantLogPreService;
	
	
	@Autowired
	private IProfileService profileService;
	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private IUserAdminService userAdminService;
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	@Autowired
	private IParticipantCompetencyLibraryFinalResultService participantCompetencyLibraryFinalResultService;
	
	@RequestMapping(value = "/view/{batchId}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("batchId")Long batchId) {
		logger.debug("BEGIN");
		Batch batch =batchService.findById(batchId);
		model.addAttribute("dataForm", batch);
		return "/pages/admin/administrator/participant/batch/participant/view";
	}
	
	@RequestMapping(value = "/add/{batchId}", method = RequestMethod.GET)
	public String add(Model model, @PathVariable("batchId")Long batchId) {
		model.addAttribute("batchId", batchId);
		model.addAttribute("dataForm", new ParticipantDto());
		List<CompetencyLibrary> competencyLibraries = competencyLibraryService.getAllActive();
		model.addAttribute("competencyLibraries", competencyLibraries);
		
		return "/pages/admin/administrator/participant/batch/participant/add";
	}
	
	
	@RequestMapping(value = "/personalitytest/{batchId}/{id}", method = RequestMethod.GET)
	public String personalitytest(Model model,  @PathVariable("batchId")Long batchId, @PathVariable("id")Long id) {
		model.addAttribute("dataForm", participantCompetencyLibraryFinalResultService.getByParticipantIdOrderByCompetencyOrder(id));
		
		List<CompetencyLibrary> competencyLibraries = competencyLibraryService.getAllActive();
		model.addAttribute("competencyLibraries", competencyLibraries);
		
		model.addAttribute("batchId",batchId);
		model.addAttribute("id",id);
		
		return "/pages/admin/administrator/participant/batch/participant/personalitytest";
	}
	
	
	@RequestMapping(value = "/personalitytest/{batchId}/{id}", method = RequestMethod.POST)
	public String personalitytestPost(@Valid @ModelAttribute("dataForm") ParticipantDto participantDto,Model model,  @PathVariable("batchId")Long batchId, @PathVariable("id")Long id,BindingResult result,HttpServletRequest request, final RedirectAttributes redirectAttributes){
		if (result.hasErrors()) {
			personalitytest(model,batchId, id);
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
			model.addAttribute("dataForm", participantDto);
			
			
			return "/pages/admin/administrator/participant/batch/personalitytest/"+batchId+"/"+id;
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			Participant participant = participantService.findById(id);
			
			ParticipantCompetencyLibraryFinalResult pcf = null;
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			for(ParticipantFinalRatingDetailDto pfdto : participantDto.getPersonalityTestList())
			{
				pcf = participantCompetencyLibraryFinalResultService.getByParticipantIdAndCompetencyLibraryId(participant.getId(), pfdto.getCompetencyId());
				if(pcf == null)
				{
					pcf = new ParticipantCompetencyLibraryFinalResult();
					pcf.setParticipant(participant);
					pcf.setCommonFields(commonFields);
					pcf.setCompetencyLibrary(competencyLibraryService.findById(pfdto.getCompetencyId()));
				}
				
				pcf.setOtherRating(pfdto.getOtherRating());
				
				participantCompetencyLibraryFinalResultService.saveOrUpdate(pcf);
			}
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+participantDto.getBatchId();
		}
	}
	
	@RequestMapping(value = "/delete/{batchId}/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("batchId")Long batchId, @PathVariable("id")Long id, HttpServletRequest request, final RedirectAttributes redirectAttributes){
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
	
		Batch batch = batchService.findById(batchId);
		
		if(!batch.getCommonFields().getCreatedBy().equals(userAdmin.getUsername()))
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","You can not access to the page because you are not the original batch creator.");
			return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+batchId;
		}
		
		ParticipantLogDuring participantLogDuring = participantLogDuringService.getByParticipantIdFirst(id);
		if(participantLogDuring != null && (participantLogDuring.getFistTimeLogin() != null || participantLogDuring.getLogDuringStatus() != null))
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","Participant already logged in to system. Can't delete the participant.");
			return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+batchId;
		}
		
		participantService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+batchId;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") ParticipantDto participantDto,  BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			add(model,participantDto.getBatchId());
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
			model.addAttribute("dataForm", participantDto);
			return "/pages/admin/administrator/participant/batch/participant/add";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			Batch batch = batchService.findById(participantDto.getBatchId());
			
			if(!batch.getCommonFields().getCreatedBy().equals(userAdmin.getUsername()))
			{
				add(model,participantDto.getBatchId());
				
				redirectAttributes.addFlashAttribute("plainErrorMessage","You can not access to the page because you are not the original batch creator.");
				return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+participantDto.getBatchId();
			}
			
			Participant existingParticipant = participantService.getByNikAndActive(participantDto.getNik().trim());
			if(existingParticipant != null)
			{
				add(model,participantDto.getBatchId());
				model.addAttribute("dataForm", participantDto);
				model.addAttribute("plainErrorMessage", "NPP "+ participantDto.getNik() +" is exist and active in batch : " + existingParticipant.getBatch().getDescription());
				return "/pages/admin/administrator/participant/batch/participant/add";
			}
			
			Employee employee = employeeService.getByNik(participantDto.getNik());
			if(employee == null)
			{
				add(model,participantDto.getBatchId());
				model.addAttribute("dataForm", participantDto);
				model.addAttribute("plainErrorMessage", "NPP not found");
				return "/pages/admin/administrator/participant/batch/participant/add";
			}
			
			
			Participant participant = new Participant();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			participant.setCommonFields(commonFields);
			participant.setEmail(employee.getEmail());
			participant.setNik(employee.getNik().trim());
			participant.setFullName(employee.getFullName());
			
			if(Validator.isNotNull(participantDto.getPassword()))
			{
				participant.setPassword(participantDto.getPassword());
			}
			else
			{
				StringBuffer password = new StringBuffer();
				
				int length = 4;
				
				if(employee.getNik().length() < 4)
				{
					length = employee.getNik().length();
				}
				password.append(employee.getNik().substring(0, length));
				password.append(DateUtil.dateToStr(employee.getDateOfBirth(), "ddMMyyyy"));
				
			}
			
			
			participant.setParticipantStatus(ParticipantStatus.REGISTERED);
			
			participant.setBatch(batch);
			
			Profile profile = new Profile();
			profile.setCommonFields(commonFields);
			profile.setCurrentDirectSupervisor(employee.getCurrentDirectSupervisor());
			profile.setCurrentPeriode(employee.getCurrentPeriode());
			profile.setCurrentPositionName(employee.getCurrentPositionName());
			profile.setCurrentResponsibility(employee.getCurrentResponsibility());
			profile.setWorkingArea(employee.getWorkingArea());
			profile.setCurrentSupervisorEmail(employee.getCurrentSupervisorEmail());
			
			profileService.save(profile);
			
			ParticipantLogDuring participantLogDuring = new ParticipantLogDuring();
			
			participantLogDuring.setCommonFields(commonFields);
			
			
			participantLogDuringService.save(participantLogDuring);
			
			participant.setProfile(profile);
			participant.setEmployee(employee);
			participant.setParticipantLogDuring(participantLogDuring);
			
			participantService.save(participant);
			
			
			ParticipantLogPre participantLogPre = new ParticipantLogPre();
			
			participantLogPre.setCommonFields(commonFields);
			participantLogPre.setParticipant(participant);
			
			participantLogPreService.save(participantLogPre);
			
			
			
			participantLogDuring.setParticipant(participant);
			participantLogDuringService.saveOrUpdate(participantLogDuring);
			
			
			ParticipantCompetencyLibraryFinalResult pcf = null;
			
			List<ParticipantCompetencyLibraryFinalResult> listPcf = new ArrayList<ParticipantCompetencyLibraryFinalResult>();
			
			for(ParticipantFinalRatingDetailDto pfdto : participantDto.getPersonalityTestList())
			{
				pcf = new ParticipantCompetencyLibraryFinalResult();
				pcf.setParticipant(participant);
				pcf.setCommonFields(commonFields);
				pcf.setCompetencyLibrary(competencyLibraryService.findById(pfdto.getCompetencyId()));
				pcf.setOtherRating(pfdto.getOtherRating());
				
				listPcf.add(pcf);
			}
			
			participantCompetencyLibraryFinalResultService.save(listPcf);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+participantDto.getBatchId();
		}
	}
	

	
	@RequestMapping(value = "/sendinvitation/{batchId}", method = RequestMethod.GET)
	public String sendinvitation(Model model, @PathVariable("batchId")Long batchId,HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		Batch batch = batchService.findById(batchId);
		
		if(!batch.getCommonFields().getCreatedBy().equals(userAdmin.getUsername()))
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","You can not access to the page because you are not the original batch creator.");
			return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+batchId;
		}
		
		List<Long> listParticipant = participantLogPreService.getInvitationSentTimeNullByBatchId(batchId);
		for(Long id : listParticipant)
		{
			sendinvitation(model, batchId, id, request, redirectAttributes);
		}
		
		return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+batchId;
	}
	
	@RequestMapping(value = "/move", method = RequestMethod.POST)
	public String move(Model model, @RequestParam("batchIdDestination")Long batchIdDestination, @RequestParam("participantId")Long participantId,HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		
		Participant participant = participantService.findById(participantId);
		Long oldBatchId = participant.getBatch().getId();
		
		Batch batch = batchService.findById(batchIdDestination);
		if(batch == null)
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","Destination Batch not found");
			return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+oldBatchId;
		}
		if(batch.getMaxQuota() == null || batch.getMaxQuota() <= 0)
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","Destination Batch has not set the max quota");
			return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+oldBatchId;
		}
		
		Batch oldBatch = batchService.findById(oldBatchId);
		if(!oldBatch.getCommonFields().getCreatedBy().equals(userAdmin.getUsername()))
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","You can not access to the page because you are not the original batch creator.");
			return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+oldBatchId;
		}
		
		Long destinationBatchCount = participantService.countByBatchBySearch(batchIdDestination, null);
		if(batch.getMaxQuota() <= destinationBatchCount)
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","Batch Full. Destination Batch has a max quota = " + batch.getMaxQuota() +", registered participant = "+destinationBatchCount);
			return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+oldBatchId;
		}
		
		ParticipantLogDuring participantLogDuring = participantLogDuringService.getByParticipantIdFirst(participant.getId());
		if(participantLogDuring != null && (participantLogDuring.getFistTimeLogin() != null || participantLogDuring.getLogDuringStatus() != null))
		{
			
			if(participantLogDuring.getFirstSessionFinishTime() != null && participantLogDuring.getSecondSessionLoginTime() == null)
			{
				
			}
			else
			{
				redirectAttributes.addFlashAttribute("plainErrorMessage","Participant already logged in to system. Can't move the participant.");
				return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+oldBatchId;
			}
			
		}
		
		participant.setBatch(batch);
		
		participantService.saveOrUpdate(participant);
		
		if(!batch.getCommonFields().getCreatedBy().equals(userAdmin.getUsername()))
		{
			// email to original batch creator
			UserAdmin userAdminCreator = userAdminService.getByUsername(batch.getCommonFields().getCreatedBy());
			mailUtil.sendEmailMoveBatch(userAdminCreator, userAdmin, participant, batch);
		}
		
		return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+oldBatchId;
	}
		
	@RequestMapping(value = "/sendinvitation/{batchId}/{id}", method = RequestMethod.GET)
	public String sendinvitation(Model model, @PathVariable("batchId")Long batchId, @PathVariable("id")Long id,HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		model.addAttribute("batchId", batchId);
		
		Participant participant =participantService.findById(id);
		model.addAttribute("dataForm", participant);
		
		Batch batch = batchService.findById(batchId);
		boolean isEmailSent = mailUtil.sendEmailInvitation(participant, batch);
		
		ParticipantLogPre participantLogPre = participantLogPreService.getByParticipantIdFirst(participant.getId());
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		
		
		if(!batch.getCommonFields().getCreatedBy().equals(userAdmin.getUsername()))
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","You can not access to the page because you are not the original batch creator.");
			return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+batchId;
		}
		if(participantLogPre == null)
		{
			participantLogPre = new ParticipantLogPre();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			participantLogPre.setCommonFields(commonFields);
			participantLogPre.setParticipant(participant);
		}
		
		participantLogPre.setInvitationSent(isEmailSent);
		participantLogPre.setInvitationNumberOfTry(participantLogPre.getInvitationNumberOfTry()+1);
		
		participantLogPre.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
		participantLogPre.getCommonFields().setLastModifiedDate(new Date());
		
		if(isEmailSent)
		{
			participantLogPre.setInvitationSentTime(new Date());
			
			
			participant.setParticipantStatus(ParticipantStatus.INVITATION_SENT);
			participantService.saveOrUpdate(participant);
		}
		
		participantLogPreService.saveOrUpdate(participantLogPre);
		
		if(isEmailSent)
		{
			redirectAttributes.addFlashAttribute("successMessage","participantion.invitation.sent");
		}else
		{
			redirectAttributes.addFlashAttribute("errorMessage","participantion.invitation.failed");
		}
		
		
		return "redirect:/controller/pages/admin/administrator/participant/batch/participant/view/"+batchId;
	}
}
