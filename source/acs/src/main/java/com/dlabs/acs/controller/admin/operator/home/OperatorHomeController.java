package com.dlabs.acs.controller.admin.operator.home;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.service.intf.participant.IBatchService;

@Controller
@RequestMapping("/pages/admin/operator/home")
public class OperatorHomeController {
	private Logger logger =Logger.getLogger(OperatorHomeController.class);
	
	@Autowired
	private IBatchService batchService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		
		logger.debug("END");
		return "operator-home";
	}
	
	@RequestMapping(value = "/view/{batchId}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("batchId")Long batchId) {
		logger.debug("BEGIN");
		Batch batch =batchService.findById(batchId);
		model.addAttribute("dataForm", batch);
		return "operator-home-detail";
	}
	
	@RequestMapping(value = "/start/{sessi}/{batchId}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("sessi")Long sessi,  @PathVariable("batchId")Long batchId, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		if(sessi != null )
		{
			Batch batch = batchService.findById(batchId);
			if(sessi.intValue() == 1)
			{
				batch.setAssessementFirstHalfStart(true);
			}else if(sessi.intValue() == 2)
			{
				batch.setAssessementSecondHalfStart(true);
			}
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			batch.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			batch.getCommonFields().setLastModifiedDate(new Date());
			
			batchService.saveOrUpdate(batch);
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
		}
		return "redirect:/controller/pages/admin/operator/home/view/"+batchId;
	}
}
