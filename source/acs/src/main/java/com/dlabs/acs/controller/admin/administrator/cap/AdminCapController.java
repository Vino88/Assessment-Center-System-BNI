package com.dlabs.acs.controller.admin.administrator.cap;

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
import com.dlabs.acs.dto.cap.CapDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.service.intf.cap.ICapService;

@Controller
@RequestMapping("/pages/admin/administrator/cap/cap")
public class AdminCapController
{
	private Logger logger = Logger.getLogger(AdminCapController.class);
	@Autowired
	private ICapService capService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/cap/cap/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/cap/cap/add";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		Cap cap =capService.findById(id);
		model.addAttribute("dataForm", cap);
		
		return "/pages/admin/administrator/cap/cap/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		capService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/cap/cap/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") CapDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/cap/cap/add";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			Cap cap = new Cap();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			cap.setCommonFields(commonFields);
			
			cap.setNumber(dataForm.getNumber());
			cap.setQuestion(dataForm.getQuestion());
			
			cap.setqResult(dataForm.getqResult());
			cap.setqAction(dataForm.getqAction());
			cap.setqSituation(dataForm.getqSituation());
			
			
			capService.save(cap);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/cap/cap/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") CapDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/cap/cap/edit";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			Cap cap = capService.findById(dataForm.getId());
			cap.setNumber(dataForm.getNumber());
			cap.setQuestion(dataForm.getQuestion());
			
			cap.setqResult(dataForm.getqResult());
			cap.setqAction(dataForm.getqAction());
			cap.setqSituation(dataForm.getqSituation());
			
			cap.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			cap.getCommonFields().setLastModifiedDate(new Date());
			
			
			
			capService.saveOrUpdate(cap);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/cap/cap/view";
		}
	}

}