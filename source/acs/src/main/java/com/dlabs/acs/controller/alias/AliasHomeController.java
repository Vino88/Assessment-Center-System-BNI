package com.dlabs.acs.controller.alias;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dlabs.acs.controller.view.HomeController;

@Controller
@RequestMapping("/pages/alias/home")
public class AliasHomeController {
	private Logger logger =Logger.getLogger(HomeController.class);
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		
		logger.debug("END");
		return "/alias/home";
	}
}
