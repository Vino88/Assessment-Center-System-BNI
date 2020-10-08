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
import com.dlabs.acs.dto.inbasket.InbasketQuestionDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.inbasket.InbasketQuestion;
import com.dlabs.acs.service.intf.inbasket.IInbasketQuestionService;

@Controller
@RequestMapping("/pages/admin/administrator/inbasket/inbasketquestion")
public class AdminInbasketQuestionController
{
	private Logger logger = Logger.getLogger(AdminInbasketQuestionController.class);
	@Autowired
	private IInbasketQuestionService inbasketquestionService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/inbasket/inbasketquestion/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/inbasket/inbasketquestion/add";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		InbasketQuestion inbasketquestion =inbasketquestionService.findById(id);
		model.addAttribute("dataForm", inbasketquestion);
		
		return "/pages/admin/administrator/inbasket/inbasketquestion/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		inbasketquestionService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/inbasket/inbasketquestion/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") InbasketQuestionDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/inbasket/inbasketquestion/add";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			InbasketQuestion inbasketquestion = new InbasketQuestion();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			inbasketquestion.setCommonFields(commonFields);
			
			inbasketquestion.setNumber(dataForm.getNumber());
			inbasketquestion.setQuestion(dataForm.getQuestion());
			
			inbasketquestionService.save(inbasketquestion);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/inbasket/inbasketquestion/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") InbasketQuestionDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/inbasket/inbasketquestion/edit";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			InbasketQuestion inbasketquestion = inbasketquestionService.findById(dataForm.getId());
			
			
			inbasketquestion.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			inbasketquestion.getCommonFields().setLastModifiedDate(new Date());
			
			inbasketquestion.setNumber(dataForm.getNumber());
			inbasketquestion.setQuestion(dataForm.getQuestion());
			
			inbasketquestionService.saveOrUpdate(inbasketquestion);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/inbasket/inbasketquestion/view";
		}
	}

}