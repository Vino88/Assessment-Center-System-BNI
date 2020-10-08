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
import com.dlabs.acs.dto.analysys.AnalysysDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.service.intf.analysys.IAnalysysService;

@Controller
@RequestMapping("/pages/admin/administrator/analysys/analysys")
public class AdminAnalysysController
{
	private Logger logger = Logger.getLogger(AdminAnalysysController.class);
	@Autowired
	private IAnalysysService analysysService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/analysys/analysys/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/analysys/analysys/add";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		Analysys analysys =analysysService.findById(id);
		model.addAttribute("dataForm", analysys);
		
		return "/pages/admin/administrator/analysys/analysys/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		analysysService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/analysys/analysys/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") AnalysysDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/analysys/analysys/add";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			Analysys analysys = new Analysys();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			analysys.setCommonFields(commonFields);
			
			analysys.setAnalysysType(dataForm.getAnalysysType());
			analysys.setNumber(dataForm.getNumber());
			analysys.setQuestion(dataForm.getQuestion());
			analysys.setTitle(dataForm.getTitle());
			
			analysysService.save(analysys);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/analysys/analysys/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") AnalysysDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/analysys/analysys/edit";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			Analysys analysys = analysysService.findById(dataForm.getId());
			
			
			analysys.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			analysys.getCommonFields().setLastModifiedDate(new Date());
			
			analysys.setAnalysysType(dataForm.getAnalysysType());
			analysys.setNumber(dataForm.getNumber());
			analysys.setQuestion(dataForm.getQuestion());
			analysys.setTitle(dataForm.getTitle());
			
			analysysService.saveOrUpdate(analysys);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/analysys/analysys/view";
		}
	}

}