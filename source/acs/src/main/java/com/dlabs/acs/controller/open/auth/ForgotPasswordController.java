package com.dlabs.acs.controller.open.auth;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.service.intf.IUserAdminService;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.mail.MailUtil;

@Controller()
@RequestMapping("/open/auth/admin/forgotpassword")
public class ForgotPasswordController {

	private Logger logger = Logger.getLogger(ForgotPasswordController.class);
	
	@Autowired
	private IUserAdminService userAdminService;
	
	@Autowired
	private MailUtil mailUtil;
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public String view()
	{
		logger.debug("BEGIN");
		
		logger.debug("END");
		
		return "admin-forgotpassword";
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	public String requestForgotPassword(@RequestParam("username")String username, final RedirectAttributes redirectAttributes) {
		logger.debug("BEGIN");
		logger.debug("username="+username);
		
		if(Validator.isNull(username) )
		{
			
			redirectAttributes.addFlashAttribute("submit_failed", "changePassword.fail.nullorempty");
			return "redirect:/controller/open/auth/admin/forgotpassword";
		}
		
		UserAdmin userAdmin = userAdminService.getByUsername(username);
		
		if(userAdmin == null)
		{
			redirectAttributes.addFlashAttribute("submit_failed", "forgotPassword.fail.username");
			return "redirect:/controller/open/auth/admin/forgotpassword";
		}
		
		mailUtil.sendEmailForgotPassword(userAdmin);
		
		logger.debug("END");
		redirectAttributes.addFlashAttribute("submit_success", "forgotPassword.success");
		return "redirect:/controller/open/auth/admin/forgotpassword";
	}
}
