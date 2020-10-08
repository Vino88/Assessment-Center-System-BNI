package com.dlabs.acs.controller.admin.administrator.factsheet;

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

import com.dlabs.acs.dto.factsheet.ProfileDto;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.service.intf.factsheet.IProfileService;

@Controller
@RequestMapping("/pages/admin/administrator/factsheet/profile")
public class AdminProfileController
{
	private Logger log = Logger.getLogger(AdminProfileController.class);
	@Autowired
	private IProfileService profileService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		return "/pages/admin/administrator/factsheet/profile/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		
		return "/pages/admin/administrator/factsheet/profile/add";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		Profile profile =profileService.findById(id);
		model.addAttribute("dataForm", profile);
		
		return "/pages/admin/administrator/factsheet/profile/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		profileService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/factsheet/profile/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") ProfileDto profileDto,  BindingResult result, final RedirectAttributes redirectAttributes, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/factsheet/profile/add";
        }
		else {
			
			Profile profile = new Profile();
			
			profileService.save(profile);
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/factsheet/profile/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") ProfileDto profileDto, BindingResult result, final RedirectAttributes redirectAttributes, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
            return "/pages/admin/administrator/factsheet/profile/edit";
        }
		else {
			Profile profile = profileService.findById(profileDto.getId());
			
			profileService.saveOrUpdate(profile);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/factsheet/profile/view";
		}
	}

}