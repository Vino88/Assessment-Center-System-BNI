package com.dlabs.acs.controller.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.service.intf.IUserAdminService;
import com.dlabs.acs.util.Validator;

@Controller()
@RequestMapping("/pages/admin/changepassword")
public class ChangePasswordController {
	private Logger logger = Logger.getLogger(ChangePasswordController.class);
	
	@Autowired
	private IUserAdminService userAdminService;
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public String view()
	{
		logger.debug("BEGIN");
		
		logger.debug("END");
		
		return "admin-changepassword";
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,  @RequestParam("newPasswordConfirmation") String newPasswordConfirmation, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		logger.debug("BEGIN");
		if(Validator.isNull(oldPassword) || Validator.isNull(newPassword) || Validator.isNull(newPasswordConfirmation))
		{
			
			redirectAttributes.addFlashAttribute("submit_failed", "changePassword.fail.nullorempty");
			return "redirect:/controller/pages/admin/changepassword";
		}
		
		if(!newPassword.equals(newPasswordConfirmation))
		{
			redirectAttributes.addFlashAttribute("submit_failed", "changePassword.fail.newpasswordnotsame");
			return "redirect:/controller/pages/admin/changepassword";
		}
		
		if(oldPassword.equals(newPassword))
		{
			redirectAttributes.addFlashAttribute("submit_failed", "changePassword.fail.oldsamewithnew");
			return "redirect:/controller/pages/admin/changepassword";
		}
		
		if(newPassword.length() < 5 )
		{
			redirectAttributes.addFlashAttribute("submit_failed", "changePassword.fail.newpasslength");
			return "redirect:/controller/pages/admin/changepassword";
		}
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		
		if(!oldPassword.equals(userAdmin.getPassword()))
		{
			redirectAttributes.addFlashAttribute("submit_failed", "changePassword.fail.oldpassisnotcorrect");
			return "redirect:/controller/pages/admin/changepassword";
		}
		
		
		userAdmin.setPassword(newPassword);
		userAdmin.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
		userAdmin.getCommonFields().setLastModifiedDate(new Date());
		userAdminService.saveOrUpdate(userAdmin);
		
		request.getSession().setAttribute(SessionConstants.USER_ADMIN, userAdmin);
		
		redirectAttributes.addFlashAttribute("submit_success", "changePassword.success");
		logger.debug("END");
		
		return "redirect:/controller/pages/admin/changepassword";
	}
}
