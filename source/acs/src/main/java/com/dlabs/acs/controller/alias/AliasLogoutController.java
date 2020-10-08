package com.dlabs.acs.controller.alias;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pages/alias/logout")
public class AliasLogoutController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/controller/pages/open/auth/alias/login";
	}
	
	@RequestMapping(value = "/firsthalf", method = RequestMethod.GET)
	public String firstHalf(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/controller/pages/open/auth/alias/login/firsthalf";
	}
	
	
	@RequestMapping(value = "/secondhalf", method = RequestMethod.GET)
	public String secondHalf(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/controller/pages/open/auth/alias/login/secondhalf";
	}
}
