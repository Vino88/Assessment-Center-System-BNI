package com.dlabs.acs.controller.admin.administrator.config;

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
import com.dlabs.acs.dto.config.SystemConfigDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.config.SystemConfig;
import com.dlabs.acs.service.intf.config.ISystemConfigService;

@Controller
@RequestMapping("/pages/admin/administrator/config/systemconfig")
public class AdminSystemConfigController
{
	private Logger logger = Logger.getLogger(AdminSystemConfigController.class);
	@Autowired
	private ISystemConfigService systemconfigService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/config/systemconfig/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/config/systemconfig/add";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		SystemConfig systemconfig =systemconfigService.findById(id);
		model.addAttribute("dataForm", systemconfig);
		
		return "/pages/admin/administrator/config/systemconfig/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		systemconfigService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/config/systemconfig/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") SystemConfigDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/config/systemconfig/add";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			SystemConfig systemconfig = new SystemConfig();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			systemconfig.setCommonFields(commonFields);
			
			systemconfig.setModule(dataForm.getModule());
			systemconfig.setSystemCode(dataForm.getSystemCode());
			systemconfig.setSystemValue(dataForm.getSystemValue());
			systemconfig.setDescription(dataForm.getDescription());
			
			systemconfigService.save(systemconfig);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/config/systemconfig/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") SystemConfigDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/config/systemconfig/edit";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			SystemConfig systemconfig = systemconfigService.findById(dataForm.getId());
			
			
			systemconfig.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			systemconfig.getCommonFields().setLastModifiedDate(new Date());
			
			systemconfig.setModule(dataForm.getModule());
			systemconfig.setSystemCode(dataForm.getSystemCode());
			systemconfig.setSystemValue(dataForm.getSystemValue());
			systemconfig.setDescription(dataForm.getDescription());
			
			systemconfigService.saveOrUpdate(systemconfig);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/config/systemconfig/view";
		}
	}

}