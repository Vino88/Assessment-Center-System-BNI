package com.dlabs.acs.controller.admin.administrator.credential;

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
import com.dlabs.acs.dto.credential.UserAdminDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.service.intf.IUserAdminService;

@Controller
@RequestMapping("/pages/admin/administrator/credential/useradmin")
public class AdminUserAdminController
{
	private Logger logger = Logger.getLogger(AdminUserAdminController.class);
	@Autowired
	private IUserAdminService useradminService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/credential/useradmin/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/credential/useradmin/add";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		UserAdmin useradmin =useradminService.findById(id);
		model.addAttribute("dataForm", useradmin);
		
		return "/pages/admin/administrator/credential/useradmin/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		logger.debug("DELETE id="+id);
		useradminService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/credential/useradmin/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") UserAdminDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/credential/useradmin/add";
        }
		else {
			UserAdmin userAdminLogin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			UserAdmin useradmin = new UserAdmin();
			
			if(useradminService.getByUsername(dataForm.getUsername()) != null)
			{
				model.addAttribute("errorMessage", "Username already exist");
	            return "/pages/admin/administrator/credential/useradmin/add";
			}
			
			useradmin.setUsername(dataForm.getUsername());
			useradmin.setFullname(dataForm.getFullname());
			useradmin.setEmail(dataForm.getEmail());
			useradmin.setPassword(dataForm.getPassword());
			useradmin.setRole(dataForm.getRole());
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdminLogin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdminLogin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			useradmin.setCommonFields(commonFields);
			
			
			
			useradminService.save(useradmin);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/credential/useradmin/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") UserAdminDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/credential/useradmin/edit";
        }
		else {
			UserAdmin userAdminLogin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			UserAdmin useradmin = useradminService.findById(dataForm.getId());
			
			if(!dataForm.getUsername().equals(useradmin.getUsername()))
			{
				if(useradminService.getByUsername(dataForm.getUsername()) != null)
				{
					model.addAttribute("errorMessage", "Username already exist");
		            return "/pages/admin/administrator/credential/useradmin/add";
				}
			}
			
			useradmin.setUsername(dataForm.getUsername());
			useradmin.setFullname(dataForm.getFullname());
			useradmin.setEmail(dataForm.getEmail());
			useradmin.setPassword(dataForm.getPassword());
			useradmin.setRole(dataForm.getRole());
			
			useradmin.getCommonFields().setLastModifiedBy(userAdminLogin.getUsername());
			useradmin.getCommonFields().setLastModifiedDate(new Date());
			
			
			
			useradminService.saveOrUpdate(useradmin);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/credential/useradmin/view";
		}
	}

}