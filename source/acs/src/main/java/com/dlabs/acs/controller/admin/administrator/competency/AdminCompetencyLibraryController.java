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
import com.dlabs.acs.dto.competency.CompetencyLibraryDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;

@Controller
@RequestMapping("/pages/admin/administrator/competency/competencylibrary")
public class AdminCompetencyLibraryController {
	private Logger logger = Logger.getLogger(AdminCompetencyLibraryController.class);
	
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/competency/competencylibrary/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/competency/competencylibrary/add";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		CompetencyLibrary competencyLibrary = competencyLibraryService.findById(id);
		model.addAttribute("dataForm", competencyLibrary);
		
		return "/pages/admin/administrator/competency/competencylibrary/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		competencyLibraryService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/competency/competencylibrary/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") CompetencyLibraryDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/competency/competencylibrary/add";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			CompetencyLibrary competencyLibrary = new CompetencyLibrary();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			competencyLibrary.setCommonFields(commonFields);
			
			competencyLibrary.setCategory(dataForm.getCategory());
			competencyLibrary.setCompetencyName(dataForm.getCompetencyName());
			competencyLibrary.setCompetencyNameBahasa(dataForm.getCompetencyNameBahasa());
			competencyLibrary.setCompetencyDescription(dataForm.getCompetencyDescription());
			competencyLibrary.setDisplayOrder(dataForm.getDisplayOrder());
			
			competencyLibraryService.save(competencyLibrary);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/competency/competencylibrary/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") CompetencyLibraryDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/competency/competencylibrary/edit";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			CompetencyLibrary competencyLibrary = competencyLibraryService.findById(dataForm.getId());
			
			competencyLibrary.setCategory(dataForm.getCategory());
			competencyLibrary.setCompetencyName(dataForm.getCompetencyName());
			competencyLibrary.setCompetencyNameBahasa(dataForm.getCompetencyNameBahasa());
			competencyLibrary.setCompetencyDescription(dataForm.getCompetencyDescription());
			competencyLibrary.setDisplayOrder(dataForm.getDisplayOrder());
			
			competencyLibrary.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			competencyLibrary.getCommonFields().setLastModifiedDate(new Date());
			
			competencyLibraryService.saveOrUpdate(competencyLibrary);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/competency/competencylibrary/view";
		}
	}
}
