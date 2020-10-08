package com.dlabs.acs.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.lcb.enumeration.Role;

@Controller
@RequestMapping("/pages/admin/home")
public class AdminHomeController {
	private Logger logger =Logger.getLogger(AdminHomeController.class);
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public String view(HttpServletRequest request)
	{
		logger.debug("BEGIN");
		
		UserAdmin userAdmin = (UserAdmin)request.getSession().getAttribute(SessionConstants.USER_ADMIN);
	
		if(Role.ADMINISTRATOR.equals(userAdmin.getRole()))
		{
			return "redirect:/controller/pages/admin/administrator/home";
		}else if(Role.OPERATOR.equals(userAdmin.getRole()))
		{
			return "redirect:/controller/pages/admin/operator/home";
		}else if(Role.ASSESSOR.equals(userAdmin.getRole()))
		{
			return "redirect:/controller/pages/admin/assessor/home";
		}
		logger.debug("END");
		return "redirect:/controller/pages/admin/logout";
	}
}
