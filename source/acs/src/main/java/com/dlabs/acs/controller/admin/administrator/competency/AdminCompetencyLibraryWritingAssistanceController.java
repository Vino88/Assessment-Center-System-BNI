package com.dlabs.acs.controller.admin.administrator.competency;

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
import com.dlabs.acs.dto.competency.CompetencyLibraryWritingAssistanceDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.CompetencyLibraryWritingAssistance;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryWritingAssistanceService;

@Controller
@RequestMapping("/pages/admin/administrator/competency/competencylibrarywritingassistance")
public class AdminCompetencyLibraryWritingAssistanceController
{
	private Logger logger = Logger.getLogger(AdminCompetencyLibraryWritingAssistanceController.class);
	@Autowired
	private ICompetencyLibraryWritingAssistanceService competencylibrarywritingassistanceService;
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/competency/competencylibrarywritingassistance/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/competency/competencylibrarywritingassistance/add";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		CompetencyLibraryWritingAssistance competencylibrarywritingassistance =competencylibrarywritingassistanceService.findById(id);
		model.addAttribute("dataForm", competencylibrarywritingassistance);
		
		return "/pages/admin/administrator/competency/competencylibrarywritingassistance/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		competencylibrarywritingassistanceService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/competency/competencylibrarywritingassistance/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") CompetencyLibraryWritingAssistanceDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/competency/competencylibrarywritingassistance/add";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			CompetencyLibraryWritingAssistance competencylibrarywritingassistance = new CompetencyLibraryWritingAssistance();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			competencylibrarywritingassistance.setCommonFields(commonFields);
			
			competencylibrarywritingassistance.setDescription(dataForm.getDescription());
			competencylibrarywritingassistance.setLevel(dataForm.getLevel());
			
			CompetencyLibrary competencyLibrary = competencyLibraryService.findById(dataForm.getCompetencyLibraryId());
			competencylibrarywritingassistance.setCompetencyLibrary(competencyLibrary);
			
			competencylibrarywritingassistanceService.save(competencylibrarywritingassistance);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/competency/competencylibrarywritingassistance/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") CompetencyLibraryWritingAssistanceDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/competency/competencylibrarywritingassistance/edit";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			CompetencyLibraryWritingAssistance competencylibrarywritingassistance = competencylibrarywritingassistanceService.findById(dataForm.getId());
			
			
			competencylibrarywritingassistance.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			competencylibrarywritingassistance.getCommonFields().setLastModifiedDate(new Date());
			
			competencylibrarywritingassistance.setDescription(dataForm.getDescription());
			competencylibrarywritingassistance.setLevel(dataForm.getLevel());
			
			CompetencyLibrary competencyLibrary = competencyLibraryService.findById(dataForm.getCompetencyLibraryId());
			competencylibrarywritingassistance.setCompetencyLibrary(competencyLibrary);
			
			
			competencylibrarywritingassistanceService.saveOrUpdate(competencylibrarywritingassistance);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/competency/competencylibrarywritingassistance/view";
		}
	}

}