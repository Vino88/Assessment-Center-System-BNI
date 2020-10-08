package com.dlabs.acs.controller.admin.administrator.inbasket;

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
import com.dlabs.acs.dto.inbasket.InbasketInboxDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.inbasket.InbasketInbox;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxService;

@Controller
@RequestMapping("/pages/admin/administrator/inbasket/inbasketinbox")
public class AdminInbasketInboxController
{
	private Logger logger = Logger.getLogger(AdminInbasketInboxController.class);
	@Autowired
	private IInbasketInboxService inbasketinboxService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/inbasket/inbasketinbox/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/inbasket/inbasketinbox/add";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		InbasketInbox inbasketinbox =inbasketinboxService.findById(id);
		model.addAttribute("dataForm", inbasketinbox);
		
		return "/pages/admin/administrator/inbasket/inbasketinbox/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		inbasketinboxService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/inbasket/inbasketinbox/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") InbasketInboxDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/inbasket/inbasketinbox/add";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			InbasketInbox inbasketinbox = new InbasketInbox();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			inbasketinbox.setCommonFields(commonFields);
			inbasketinbox.setInboxType(dataForm.getInboxType());
			inbasketinbox.setNumber(dataForm.getNumber());
			inbasketinbox.setTitle(dataForm.getTitle());
			
			
			inbasketinboxService.save(inbasketinbox);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/inbasket/inbasketinbox/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") InbasketInboxDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/inbasket/inbasketinbox/edit";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			InbasketInbox inbasketinbox = inbasketinboxService.findById(dataForm.getId());
			
			
			inbasketinbox.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			inbasketinbox.getCommonFields().setLastModifiedDate(new Date());
			
			inbasketinbox.setInboxType(dataForm.getInboxType());
			inbasketinbox.setNumber(dataForm.getNumber());
			inbasketinbox.setTitle(dataForm.getTitle());
			
			inbasketinboxService.saveOrUpdate(inbasketinbox);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/inbasket/inbasketinbox/view";
		}
	}

}