package com.dlabs.acs.controller.view;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pages/view/home")
public class HomeController {
	private Logger logger =Logger.getLogger(HomeController.class);
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		
		logger.debug("END");
		return "home";
	}
}
