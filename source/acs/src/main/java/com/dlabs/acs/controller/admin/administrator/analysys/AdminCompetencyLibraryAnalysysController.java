package com.dlabs.acs.controller.admin.administrator.analysys;

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
import com.dlabs.acs.dto.analysys.CompetencyLibraryAnalysysDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.CompetencyLibraryAnalysys;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.intf.analysys.IAnalysysService;
import com.dlabs.acs.service.intf.analysys.ICompetencyLibraryAnalysysService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;

@Controller
@RequestMapping("/pages/admin/administrator/analysys/competencylibraryanalysys")
public class AdminCompetencyLibraryAnalysysController
{
	private Logger logger = Logger.getLogger(AdminCompetencyLibraryAnalysysController.class);
	@Autowired
	private ICompetencyLibraryAnalysysService competencylibraryanalysysService;
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	@Autowired
	private IAnalysysService analysysService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/analysys/competencylibraryanalysys/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/analysys/competencylibraryanalysys/add";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		CompetencyLibraryAnalysys competencylibraryanalysys =competencylibraryanalysysService.findById(id);
		model.addAttribute("dataForm", competencylibraryanalysys);
		
		return "/pages/admin/administrator/analysys/competencylibraryanalysys/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		competencylibraryanalysysService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/analysys/competencylibraryanalysys/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") CompetencyLibraryAnalysysDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/analysys/competencylibraryanalysys/add";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			CompetencyLibraryAnalysys competencylibraryanalysys = new CompetencyLibraryAnalysys();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			competencylibraryanalysys.setCommonFields(commonFields);
			
			CompetencyLibrary competencyLibrary = competencyLibraryService.findById(dataForm.getCompetencyLibraryId());
			competencylibraryanalysys.setCompetencyLibrary(competencyLibrary);
			
			Analysys analysys = analysysService.getByQuestNumber(dataForm.getNumber());
			competencylibraryanalysys.setAnalysys(analysys);
			
			competencylibraryanalysysService.save(competencylibraryanalysys);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/analysys/competencylibraryanalysys/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") CompetencyLibraryAnalysysDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/analysys/competencylibraryanalysys/edit";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			CompetencyLibraryAnalysys competencylibraryanalysys = competencylibraryanalysysService.findById(dataForm.getId());
			
			
			competencylibraryanalysys.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			competencylibraryanalysys.getCommonFields().setLastModifiedDate(new Date());
			
			
			
			CompetencyLibrary competencyLibrary = competencyLibraryService.findById(dataForm.getCompetencyLibraryId());
			competencylibraryanalysys.setCompetencyLibrary(competencyLibrary);
			
			Analysys analysys = analysysService.getByQuestNumber(dataForm.getNumber());
			competencylibraryanalysys.setAnalysys(analysys);
			
			competencylibraryanalysysService.saveOrUpdate(competencylibraryanalysys);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/analysys/competencylibraryanalysys/view";
		}
	}

}