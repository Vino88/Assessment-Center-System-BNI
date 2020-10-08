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
import com.dlabs.acs.dto.competency.CompetencyLibraryBehaviourDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryBehaviourService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;

@Controller
@RequestMapping("/pages/admin/administrator/competency/competencylibrarybehaviour")
public class AdminCompetencyLibraryBehaviourController
{
	private Logger logger = Logger.getLogger(AdminCompetencyLibraryBehaviourController.class);
	@Autowired
	private ICompetencyLibraryBehaviourService competencylibrarybehaviourService;
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/competency/competencylibrarybehaviour/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/competency/competencylibrarybehaviour/add";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		CompetencyLibraryBehaviour competencylibrarybehaviour =competencylibrarybehaviourService.findById(id);
		model.addAttribute("dataForm", competencylibrarybehaviour);
		
		return "/pages/admin/administrator/competency/competencylibrarybehaviour/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		competencylibrarybehaviourService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/competency/competencylibrarybehaviour/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") CompetencyLibraryBehaviourDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/competency/competencylibrarybehaviour/add";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			CompetencyLibraryBehaviour competencylibrarybehaviour = new CompetencyLibraryBehaviour();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			competencylibrarybehaviour.setCommonFields(commonFields);
			
			competencylibrarybehaviour.setBehaviour(dataForm.getBehaviour());
			competencylibrarybehaviour.setBehaviourLevel(dataForm.getBehaviourLevel());
			
			CompetencyLibrary competencyLibrary = competencyLibraryService.findById(dataForm.getCompetencyLibraryId());
			competencylibrarybehaviour.setCompetencyLibrary(competencyLibrary);
			
			competencylibrarybehaviourService.save(competencylibrarybehaviour);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/competency/competencylibrarybehaviour/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") CompetencyLibraryBehaviourDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/competency/competencylibrarybehaviour/edit";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			CompetencyLibraryBehaviour competencylibrarybehaviour = competencylibrarybehaviourService.findById(dataForm.getId());
			
			
			competencylibrarybehaviour.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			competencylibrarybehaviour.getCommonFields().setLastModifiedDate(new Date());
			
			competencylibrarybehaviour.setBehaviour(dataForm.getBehaviour());
			competencylibrarybehaviour.setBehaviourLevel(dataForm.getBehaviourLevel());
			
			CompetencyLibrary competencyLibrary = competencyLibraryService.findById(dataForm.getCompetencyLibraryId());
			competencylibrarybehaviour.setCompetencyLibrary(competencyLibrary);
			
			competencylibrarybehaviourService.saveOrUpdate(competencylibrarybehaviour);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/competency/competencylibrarybehaviour/view";
		}
	}

}