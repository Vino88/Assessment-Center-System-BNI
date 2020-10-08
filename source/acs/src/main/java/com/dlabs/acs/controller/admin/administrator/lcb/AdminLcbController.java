package com.dlabs.acs.controller.admin.administrator.lcb;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.dlabs.acs.dto.lcb.LcbDto;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.lcb.Lcb;
import com.dlabs.acs.service.intf.lcb.ILcbService;
import com.dlabs.acs.util.Validator;

@Controller
@RequestMapping("/pages/admin/administrator/lcb")
public class AdminLcbController {
	@Autowired
	private ILcbService lcbService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		return "administrator/lcb/view";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		Lcb lcb=lcbService.findById(id);
		model.addAttribute("dataForm", lcb);
		
		return "administrator/lcb/edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") LcbDto lcbDto, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return  "administrator/lcb/edit";
        }
		else {
			Lcb lcb = lcbService.findById(lcbDto.getId());
			
			lcb.setQuestion(lcbDto.getQuestion());
			
			lcb.setChoiceA(lcbDto.getChoiceA());
			lcb.setChoiceB(lcbDto.getChoiceB());
			lcb.setChoiceC(lcbDto.getChoiceC());
			if(Validator.isNotNull(lcb.getChoiceD()))
			{
				lcb.setChoiceD(lcbDto.getChoiceD());
			}
			if(Validator.isNotNull(lcb.getChoiceE()))
			{
				lcb.setChoiceE(lcbDto.getChoiceE());
			}
			
			lcb.getCommonFields().setLastModifiedDate(new Date());
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			lcb.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			
			lcbService.saveOrUpdate(lcb);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/lcb/view";
		}
	}
}
