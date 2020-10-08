package com.dlabs.acs.controller.admin.administrator.masterdata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.dto.masterdata.EmployeeDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.service.intf.masterdata.IEmployeeService;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.excel.masterdata.ExcelEmployee;
import com.dlabs.acs.util.properties.ConfigProperties;

@Controller
@RequestMapping("/pages/admin/administrator/masterdata/employee")
public class AdminEmployeeController
{
	private Logger logger = Logger.getLogger(AdminEmployeeController.class);
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private ConfigProperties configProperties;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/masterdata/employee/view";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/masterdata/employee/add";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(Model model) {
		logger.debug("BEGIN");
		return "/pages/admin/administrator/masterdata/employee/upload";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		Employee employee =employeeService.findById(id);
		model.addAttribute("dataForm", employee);
		
		return "/pages/admin/administrator/masterdata/employee/edit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model,@PathVariable("id")Long id, final RedirectAttributes redirectAttributes){
		employeeService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage","crud.delete.success");
		return "redirect:/controller/pages/admin/administrator/masterdata/employee/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("dataForm") EmployeeDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
			add(model);
            return "/pages/admin/administrator/masterdata/employee/add";
        }
		else {
			
			Employee existingEmployee = employeeService.getByNik(dataForm.getNik());
			if(existingEmployee != null)
			{
				model.addAttribute("plainErrorMessage", "NPP already exists");
				add(model);
				return "/pages/admin/administrator/masterdata/employee/add";
			}
			
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			Employee employee = new Employee();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			employee.setCommonFields(commonFields);
			employee.setDateOfBirth(dataForm.getDateOfBirth());
			employee.setEmail(dataForm.getEmail());
			employee.setFullName(dataForm.getFullName());
			employee.setNik(dataForm.getNik());
			employee.setPhone(dataForm.getPhone());
			employee.setPlaceOfBirth(dataForm.getPlaceOfBirth());
			employee.setWorkingArea(dataForm.getWorkingArea());
			employee.setCurrentDirectSupervisor(dataForm.getCurrentDirectSupervisor());
			employee.setCurrentPeriode(dataForm.getCurrentPeriode());
			employee.setCurrentPositionName(dataForm.getCurrentPositionName());
			employee.setCurrentResponsibility(dataForm.getCurrentResponsibility());
			employee.setWorkingArea(dataForm.getWorkingArea());
			employee.setCurrentSupervisorEmail(dataForm.getCurrentSupervisorEmail());
			
			
			employeeService.save(employee);
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.add.success");
			return "redirect:/controller/pages/admin/administrator/masterdata/employee/view";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@Valid @ModelAttribute("dataForm") EmployeeDto dataForm, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
			edit(model, dataForm.getId());
            return "/pages/admin/administrator/masterdata/employee/edit";
        }
		else {
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			Employee employee = employeeService.findById(dataForm.getId());
			
			if(!employee.getNik().equals(dataForm.getNik()))
			{
				Employee existingEmployee = employeeService.getByNik(dataForm.getNik());
				if(existingEmployee != null)
				{
					model.addAttribute("plainErrorMessage", "You can not change to NPP "+dataForm.getNik() +". This NPP already exists for other user");
					edit(model, dataForm.getId());
		            return "/pages/admin/administrator/masterdata/employee/edit";
				}
			}
			
			employee.setDateOfBirth(dataForm.getDateOfBirth());
			employee.setEmail(dataForm.getEmail());
			employee.setFullName(dataForm.getFullName());
			employee.setNik(dataForm.getNik());
			employee.setPhone(dataForm.getPhone());
			employee.setPlaceOfBirth(dataForm.getPlaceOfBirth());
			employee.setWorkingArea(dataForm.getWorkingArea());
			employee.setCurrentDirectSupervisor(dataForm.getCurrentDirectSupervisor());
			employee.setCurrentPeriode(dataForm.getCurrentPeriode());
			employee.setCurrentPositionName(dataForm.getCurrentPositionName());
			employee.setCurrentResponsibility(dataForm.getCurrentResponsibility());
			employee.setWorkingArea(dataForm.getWorkingArea());
			employee.setCurrentSupervisorEmail(dataForm.getCurrentSupervisorEmail());
			employee.getCommonFields().setLastModifiedBy(userAdmin.getUsername());
			employee.getCommonFields().setLastModifiedDate(new Date());
			
			employeeService.saveOrUpdate(employee);
			
			
			
			redirectAttributes.addFlashAttribute("successMessage","crud.edit.success");
			return "redirect:/controller/pages/admin/administrator/masterdata/employee/view";
		}
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		logger.debug("BEGIN");
		if(file == null || file.isEmpty() || file.getOriginalFilename() == null || file.getSize() == 0)
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","File is empty");
			return "redirect:/controller/pages/admin/administrator/masterdata/employee/view";
		}
		if(!file.getOriginalFilename().endsWith(".xlsx"))
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","Extentions not .xlsx");
			return "redirect:/controller/pages/admin/administrator/masterdata/employee/view";
		}
		double bytes = file.getSize();
		double kilobytes = (bytes / 1024);
		double megabytes = (kilobytes / 1024);
		
		if(megabytes > 10)
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage","File Maximum 10 MB");
			return "redirect:/controller/pages/admin/administrator/masterdata/employee/view";
		}
		try
		{
			List<EmployeeDto> listEmployeeDto = ExcelEmployee.parse(file.getInputStream());
			List<EmployeeDto> listEmployeeFailed = ExcelEmployee.parseWithFailed(file.getInputStream());
			
			if(listEmployeeDto == null || listEmployeeDto.size() == 0)
			{
				redirectAttributes.addFlashAttribute("plainErrorMessage","No data exist inside the file");
				return "redirect:/controller/pages/admin/administrator/masterdata/employee/view";
			}
			List<String> niks = new ArrayList<String>();
			for(EmployeeDto employeeDto : listEmployeeDto)
			{
				niks.add(employeeDto.getNik());
			}
			List<String> existingNik = employeeService.getNikExisting(niks);
			
			if(existingNik != null && existingNik.size() > 0)
			{
				redirectAttributes.addFlashAttribute("plainErrorMessage","The following NPP " + existingNik + " is already exists");
				return "redirect:/controller/pages/admin/administrator/masterdata/employee/view";
			}
			
			List<Employee> listEmployee = new ArrayList<Employee>();
			
			UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
			
			Employee employee = null;
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(userAdmin.getUsername());
			commonFields.setCreatedDate(new Date());
			commonFields.setLastModifiedBy(userAdmin.getUsername());
			commonFields.setLastModifiedDate(new Date());
			
			
			for(EmployeeDto employeeDto : listEmployeeDto)
			{
				employee = new Employee();
				employee.setCommonFields(commonFields);
				employee.setDateOfBirth(employeeDto.getDateOfBirth());
				employee.setEmail(employeeDto.getEmail());
				employee.setFullName(employeeDto.getFullName());
				employee.setNik(employeeDto.getNik());
				employee.setPhone(employeeDto.getPhone());
				employee.setPlaceOfBirth(employeeDto.getPlaceOfBirth());
				employee.setWorkingArea(employeeDto.getWorkingArea());
				employee.setCurrentDirectSupervisor(employeeDto.getCurrentDirectSupervisor());
				employee.setCurrentPeriode(employeeDto.getCurrentPeriode());
				employee.setCurrentPositionName(employeeDto.getCurrentPositionName());
				employee.setCurrentResponsibility(employeeDto.getCurrentResponsibility());
				employee.setWorkingArea(employeeDto.getWorkingArea());
				employee.setCurrentSupervisorEmail(employeeDto.getCurrentSupervisorEmail());
				listEmployee.add(employee);
			}
			
			
			employeeService.save(listEmployee);
			
			redirectAttributes.addFlashAttribute("plainSuccessMessage","Successfully uploaded");
			
			redirectAttributes.addFlashAttribute("listEmployeeDto", listEmployeeDto);
			redirectAttributes.addFlashAttribute("listEmployeeFailed", listEmployeeFailed);
		}catch(Exception e)
		{
			redirectAttributes.addFlashAttribute("plainErrorMessage",e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/controller/pages/admin/administrator/masterdata/employee/view";
	}
	
	@RequestMapping(value = "/profilepicture/{id}", method = RequestMethod.GET)
	public String profilepicture(Model model,@PathVariable("id") Long id) {
		Employee employee =employeeService.findById(id);
		model.addAttribute("dataForm", employee);
		logger.debug("BEGIN");
		return "/pages/admin/administrator/masterdata/employee/profilepicture";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/profilepicture/{id}")
	private String handleProfilePicture(@PathVariable("id") Long id, MultipartFile file, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		
			if(file == null || file.isEmpty() || file.getSize() < 1 || file.getOriginalFilename() == null)
			{
				redirectAttributes.addFlashAttribute("plainErrorMessage","empty file");
				return "redirect:/controller/pages/admin/administrator/masterdata/employee/profilepicture/"+id;
			}
			
			if(file.getOriginalFilename().endsWith("png") || file.getOriginalFilename().endsWith("PNG") || 
					file.getOriginalFilename().endsWith("jpg") || file.getOriginalFilename().endsWith("JPG") ||
					file.getOriginalFilename().endsWith("jpeg") || file.getOriginalFilename().endsWith("JPEG")
					)
			{
				
			}
			else
			{
				redirectAttributes.addFlashAttribute("plainErrorMessage","file extentions is not png, jpg or jpeg");
				return "redirect:/controller/pages/admin/administrator/masterdata/employee/profilepicture/"+id;
			}
			
			double bytes = file.getSize();
			double kilobytes = (bytes / 1024);
			double megabytes = (kilobytes / 1024);
			
			if(megabytes > 2)
			{
				redirectAttributes.addFlashAttribute("plainErrorMessage","file size maximum is 2 MB");
				return "redirect:/controller/pages/admin/administrator/masterdata/employee/profilepicture/"+id;
			}
			
			File baseFolder = new File(configProperties.getProfileFilePath());
			if(!baseFolder.exists())
			{
				baseFolder.mkdirs();
			}
			Employee employee = employeeService.findById(id);
			File employeeFolder = new File(baseFolder, employee.getId().toString());
			if(!employeeFolder.exists())
			{
				employeeFolder.mkdir();
			}
			
			String filename = null;
			if(file!= null && file.getOriginalFilename() != null && file.getOriginalFilename().trim().length() > 0 && file.getOriginalFilename().lastIndexOf(".") > 0)
			{
				filename = Long.toString(Calendar.getInstance().getTimeInMillis()) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
			}
			
			File fileVs = new File(employeeFolder, filename);
			if(!fileVs.exists())
			{
				try {
					fileVs.createNewFile();
				} catch (IOException e) {
					redirectAttributes.addFlashAttribute("plainErrorMessage","Something wrong with the configuration (create employee folder). Please contact your administrator");
					return "redirect:/controller/pages/admin/administrator/masterdata/employee/profilepicture/"+id;
				}
			}
			
			try {
				OutputStream is = new FileOutputStream(fileVs);
				FileCopyUtils.copy(file.getInputStream(), is);
				
				if(Validator.isNotNull(employee.getProfilePicture()))
				{
					try
					{
						File fileOld = new File(employeeFolder, employee.getProfilePicture());
						fileOld.delete();
					}catch(Exception ie){}
				}
				
			} catch (IOException e) {
			// TODO Auto-generated catch block
				redirectAttributes.addFlashAttribute("plainErrorMessage","Something wrong with the configuration (create image file). Please contact your administrator");
				return "redirect:/controller/pages/admin/administrator/masterdata/employee/profilepicture/"+id;
			}
			
			employee.setProfilePicture(filename);
			employeeService.saveOrUpdate(employee);
			redirectAttributes.addFlashAttribute("successMessage","Successfully uploaded");
			return "redirect:/controller/pages/admin/administrator/masterdata/employee/profilepicture/"+id;
	}
	
	@RequestMapping(value = "/preview/{id}", method = RequestMethod.GET)
	@ResponseBody public FileSystemResource preview(@PathVariable("id") Long id, HttpServletResponse response) {
		Employee employee = employeeService.findById(id);
	    String path = configProperties.getProfileFilePath() + File.separator + id + File.separator + employee.getProfilePicture();
	    return new FileSystemResource(path);
	}

}