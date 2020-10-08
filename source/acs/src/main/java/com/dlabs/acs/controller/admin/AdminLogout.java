package com.dlabs.acs.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/pages/admin/logout")
public class AdminLogout {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/controller/pages/open/auth/admin/login";
	}
}
