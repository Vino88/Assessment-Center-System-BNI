package com.dlabs.acs.controller.open.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.controller.AbstractController;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.lcb.enumeration.Role;
import com.dlabs.acs.service.intf.IUserAdminService;
import com.dlabs.acs.util.Validator;

@Controller()
@RequestMapping("/pages/open/auth/admin/login")
public class AdminLoginController extends AbstractController {
	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(AdminLoginController.class);
	
	@Autowired
	IUserAdminService userAdminService;
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public String view(HttpServletRequest request, HttpServletResponse response){
		logger.debug("BEGIN");
		
		String result = "admin-login";
		if(request.getSession().getAttribute(SessionConstants.USER_ADMIN) != null){
			return "redirect:/controller/pages/admin/home";
		}
		logger.debug("END");
		return result;
		
	}
	@RequestMapping(method = RequestMethod.POST, value = "")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		
		logger.debug("BEGIN");
		
		if(Validator.isNull(username) || Validator.isNull(password))
		{
			redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.nullorempty");
			return "redirect:/controller/pages/open/auth/admin/login";
		}
		
		
		boolean isAuthenticateUser = userAdminService.login(username, password);
		if(!isAuthenticateUser)
		{
			redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.invalid");
			return "redirect:/controller/pages/open/auth/admin/login";
		}
		
		UserAdmin userAdmin = userAdminService.getByUsername(username);
		if(!password.equals(userAdmin.getPassword()))
		{
			redirectAttributes.addFlashAttribute("aunthentication_failed", "authentication.failed.invalid");
			return "redirect:/controller/pages/open/auth/admin/login";
		}
		
		
		request.getSession().setAttribute(SessionConstants.USER_ADMIN, userAdmin);
		
		logger.debug("END");
		return "redirect:/controller/pages/admin/home";
	}

}
