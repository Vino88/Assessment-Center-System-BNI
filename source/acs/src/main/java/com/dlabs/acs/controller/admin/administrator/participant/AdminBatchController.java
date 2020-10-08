package com.dlabs.acs.controller.admin.administrator.participant;

import java.util.Date;
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
import com.dlabs.acs.constants.SystemConfigConstants;
import com.dlabs.acs.dto.assessementresult.report.GroupReportDto;
import com.dlabs.acs.dto.participant.BatchDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.service.intf.IUserAdminService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.config.ISystemConfigService;
import com.dlabs.acs.service.intf.participant.IBatchService;

@Controller
@RequestMapping("/pages/admin/administrator/participant/batch")
public class AdminBatchController
{
	private Logger logger = Logger.getLogger(AdminBatchController.class);
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IBatchService batchService;
	@Autowired
	private ISystemConfigService systemConfigService;
	@Autowired
	private IUserAdminService userAdminService;
	@Autowired
	private IParticipantCompetencyLibraryFinalResultService pClFinalResultService;
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/participant/batch/view";
	}
	
	@RequestMapping(value = "/inprogress/view", method = RequestMethod.GET)
	public String viewinprogress(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/participant/batch/inprogress/view";
	}
	
	@RequestMapping(value = "/inprogress/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		model.addAttribute("timermodule", systemConfigService.getMapByModule(SystemConfigConstants.TIMER.MODULE_NAME));
		return "/pages/admin/administrator/participant/batch/inprogress/add";
	}
	
	@RequestMapping(value = "/inprogress/edit/{id}", method = RequestMethod.GET)
	public String editinprogress(Model model, @PathVariable("id")Long id, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		logger.debug("BEGIN");
		Batch batch =batchService.findById(id);
		
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		
		if(!batch.getCommonFields().getCreatedBy().equals(userAdmin.getUsername()))
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","You can not access to the page because you are not the original batch creator.");
			return "redirect:/controller/pages/admin/administrator/participant/batch/inprogress/view/";
		}
		
		model.addAttribute("dataForm", batch);
		model.addAttribute("timermodule", systemConfigService.getMapByModule(SystemConfigConstants.TIMER.MODULE_NAME));
		return "/pages/admin/administrator/participant/batch/inprogress/edit";
	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		logger.debug("BEGIN");
		Batch batch =batchService.findById(id);
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		
		if(!batch.getCommonFields().getCreatedBy().equals(userAdmin.getUsername()))
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","You can not access to the page because you are not the original batch creator.");
			return "redirect:/controller/pages/admin/administrator/participant/batch/inprogress/view/";
		}
		model.addAttribute("dataForm", batch);
		
		model.addAttribute("timermodule", systemConfigService.getMapByModule(SystemConfigConstants.TIMER.MODULE_NAME));
		
		return "/pages/admin/administrator/participant/batch/edit";
	}
	
	@RequestMapping(value = "/detail/{batchId}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("batchId")Long batchId) {
		logger.debug("BEGIN");
		Batch batch =batchService.findById(batchId);
		model.addAttribute("dataForm", batch);
		return "/pages/admin/administrator/participant/batch/detail";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes, HttpServletRequest request){
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		Batch batch =batchService.findById(id);
		if(!batch.getCommonFields().getCreatedBy().equals(userAdmin.getUsername()))
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","You can not access to the page because you are not the original batch creator.");
			return "redirect:/controller/pages/admin/administrator/participant/batch/inprogress/view/";
		}
		
		Long destinationBatchCount = participantService.countByBatchBySearch(id, null);
		if(destinationBatchCount > 0)
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","Batch still have participant. Please delete all the participant first.");
			return "redirect:/controller/pages/admin/administrator/participant/batch/inprogress/view";
		}
		
		batchService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/participant/batch/inprogress/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") BatchDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/participant/batch/inprogress/add";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			Batch batch = new Batch();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			batch.setCommonFields(commonFields);
			
			batch.setDescription(dataForm.getDescription());
			
			batch.setLocationSecond(dataForm.getLocationSecond());
			batch.setLocation(dataForm.getLocation());
			batch.setMaxQuota(dataForm.getMaxQuota());
			batch.setAdditionalInformation(dataForm.getAdditionalInformation());
			
			batch.setAssessementFirstHalfStartTime(dataForm.getAssessementFirstHalfStartTime());
			batch.setAssessementFirstHalfEndTime(dataForm.getAssessementFirstHalfEndTime());
			
			batch.setAssessementSecondHalfStartTime(dataForm.getAssessementSecondHalfStartTime());
			batch.setAssessementFinishTime(dataForm.getAssessementFinishTime());
			batch.setInprogress(true);
			
			if(dataForm.getUserId() != null)
			{
				UserAdmin operator = userAdminService.findById(dataForm.getUserId());
				batch.setUserAdmin(operator);
			}
			
			batchService.save(batch);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/participant/batch/inprogress/view";
		}
	}
	
	@RequestMapping(value = "/inprogress/edit", method = RequestMethod.POST)
	public String editPostInprogress(@Valid @ModelAttribute("dataForm") BatchDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		Batch batch =batchService.findById(dataForm.getId());
		if(!batch.getCommonFields().getCreatedBy().equals(userAdmin.getUsername()))
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","You can not access to the page because you are not the original batch creator.");
			return "redirect:/controller/pages/admin/administrator/participant/batch/inprogress/view/";
		}
		
		String resultView = editPost(dataForm, result, redirectAttributes, request, model);
		if(resultView.contains("redirect"))
		{
			return "redirect:/controller/pages/admin/administrator/participant/batch/inprogress/view";
		}
		return resultView;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") BatchDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/participant/batch/edit";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			Batch batch =batchService.findById(dataForm.getId());
			if(!batch.getCommonFields().getCreatedBy().equals(userAdmin.getUsername()))
			{
				redirectAttributes.addFlashAttribute("plainErrorMessage","You can not access to the page because you are not the original batch creator.");
				return "redirect:/controller/pages/admin/administrator/participant/batch/inprogress/view/";
			}
			
			
			batch.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			batch.getCommonFields().setLastModifiedDate(new Date());
			
			batch.setDescription(dataForm.getDescription());
			batch.setLocation(dataForm.getLocation());
			batch.setLocationSecond(dataForm.getLocationSecond());
			
			batch.setMaxQuota(dataForm.getMaxQuota());
			
			batch.setAdditionalInformation(dataForm.getAdditionalInformation());
			
			batch.setAssessementFirstHalfStartTime(dataForm.getAssessementFirstHalfStartTime());
			batch.setAssessementFirstHalfEndTime(dataForm.getAssessementFirstHalfEndTime());
			
			batch.setAssessementSecondHalfStartTime(dataForm.getAssessementSecondHalfStartTime());
			batch.setAssessementFinishTime(dataForm.getAssessementFinishTime());
			
			//batch.setInprogress(dataForm.isInprogress());
			
			if(dataForm.getUserId() != null)
			{
				UserAdmin operator = userAdminService.findById(dataForm.getUserId());
				batch.setUserAdmin(operator);
			}
			
			batchService.saveOrUpdate(batch);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/participant/batch/view";
		}
	}
	
	
	@RequestMapping(value = "/report/{batchId}", method = RequestMethod.GET)
	public String report(Model model, @PathVariable("batchId")Long batchId) {
		logger.debug("BEGIN");
		Batch batch =batchService.findById(batchId);
		model.addAttribute("dataForm", batch);
		
		List<CompetencyLibrary> competencyLibraries = competencyLibraryService.getAll();
		
		List<GroupReportDto> list = pClFinalResultService.getReportBatch(competencyLibraries, batchId);
		model.addAttribute("list", list);
		
		return "/pages/admin/administrator/participant/batch/report";
	}
	
	
	@RequestMapping(value = "/reportgroup/{batchId}", method = RequestMethod.GET)
	public String reportgroup(Model model, @PathVariable("batchId")Long batchId) {
		logger.debug("BEGIN");
		Batch batch =batchService.findById(batchId);
		model.addAttribute("dataForm", batch);
		
		List<CompetencyLibrary> competencyLibraries = competencyLibraryService.getAll();
		model.addAttribute("competencyLibraries", competencyLibraries);
		
		List<Map<String,Object>> list = pClFinalResultService.getReportGroup(batchId);
		model.addAttribute("list", list);
		
		return "/pages/admin/administrator/participant/batch/reportgroup";
	}

}