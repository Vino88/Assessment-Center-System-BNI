package com.dlabs.acs.controller.admin.administrator.assessement;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.dto.assessementresult.report.ListIndividuReportDto;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantLcb;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.ParticipantLogPre;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.entity.participant.ParticipantLogPost;
import com.dlabs.acs.service.intf.assessement.IParticipantLcbService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogPreService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.factsheet.IProfileService;
import com.dlabs.acs.service.intf.masterdata.IEmployeeService;
import com.dlabs.acs.service.intf.participant.IBatchService;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;
import com.dlabs.acs.util.mail.MailUtil;
import com.dlabs.acs.util.report.ReportIndividuUtil;

@Controller
@RequestMapping("/pages/admin/administrator/assessement/participant")
public class AdminParticipantController
{
	private Logger logger = Logger.getLogger(AdminParticipantController.class);
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	@Autowired
	private IParticipantLogPreService participantLogPreService;
	@Autowired
	private IParticipantLogPostService participantLogPostService;
	
	@Autowired
	private IParticipantLcbService participantLcbService;
	@Autowired
	private IBatchService batchService;
	
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	@Autowired
	private ReportIndividuUtil reportIndividuUtil;
	
	/*@Autowired
	private JasperReportIndividuUtil jasperReportIndividuUtil;*/
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IProfileService profileService;
	
	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private IParticipantCompetencyLibraryFinalResultService participantCompetencyLibraryFinalResultService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {
		return "/pages/admin/administrator/assessement/participant/view";
	}
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable("id")Long id) {
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		Employee employee = employeeService.getByNik(participant.getNik());
		model.addAttribute("employee", employee);
		
		ParticipantLogPre participantLogPre = participantLogPreService.getByParticipantIdFirst(participant.getId());
		model.addAttribute("participantLogPre", participantLogPre);
		
		ParticipantLogDuring participantLogDuring = participantLogDuringService.getByParticipantIdFirst(participant.getId());
		model.addAttribute("participantLogDuring", participantLogDuring);
		
		ParticipantLogPost participantLogPost = participantLogPostService.getByParticipantIdFirst(participant.getId());
		model.addAttribute("participantLogPost", participantLogPost);
		
		Batch batch = participantService.getBatchByParticipantId(id);
		model.addAttribute("batch", batch);
		
		List<CompetencyLibrary> listCompetency = competencyLibraryService.getBySearch("", 0, 40);
		model.addAttribute("listCompetency", listCompetency);
		
		return "/pages/admin/administrator/assessement/participant/detail";
	}
	
	@RequestMapping(value = "/detail/lcb/{id}", method = RequestMethod.GET)
	public String detailLcb(Model model, @PathVariable("id")Long id) {
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		List<ParticipantLcb> listParticipantLcb = participantLcbService.getByParticipantId(participant.getId());
		model.addAttribute("listParticipantLcb", listParticipantLcb);
		
		return "/pages/admin/administrator/assessement/participant/detail/lcb";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/individualreport/download/{id}")
	public void download(@PathVariable("id") Long id,@RequestParam("password") String password,HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("BEGIN");
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		Participant participant = participantService.findById(id);
		Employee employee = employeeService.getByNik(participant.getNik());
		Profile profile = participantService.getProfileByParticipantId(id);
		
		Batch batch = participantService.getBatchByParticipantId(participant.getId());
		
		List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantId(id);
		ListIndividuReportDto dataForm = (ListIndividuReportDto) request.getSession().getAttribute(SessionConstants.ADMIN_REPORT_COMPETENCY_SELECT);
		
		response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=Assessment_report_"+participant.getNik().trim().replaceAll(" ", "_") +participant.getFullName().trim().replaceAll(" ", "_")+".pdf");
        try
        {
        	reportIndividuUtil.generateReport(password,userAdmin.getFullname(), employee, profile,batch, listParticipantCompetencyLibraryFinalResult,dataForm.getIndividuReportDto(), response.getOutputStream());
            response.getOutputStream().flush();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
        //
	        
		logger.debug("END");
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/individualreport/downloadodt/{id}")
	public void download(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("BEGIN");
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		Participant participant = participantService.findById(id);
		Employee employee = employeeService.getByNik(participant.getNik());
		Profile profile = participantService.getProfileByParticipantId(id);
		
		Batch batch = participantService.getBatchByParticipantId(participant.getId());
		
		List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantId(id);
		ListIndividuReportDto dataForm = (ListIndividuReportDto) request.getSession().getAttribute(SessionConstants.ADMIN_REPORT_COMPETENCY_SELECT);
		
		response.setContentType("application/nd.openxmlformats-officedocument.wordprocessingml.document");
        response.addHeader("Content-Disposition", "attachment; filename=Assessment_report_"+participant.getNik().trim().replaceAll(" ", "_") +participant.getFullName().trim().replaceAll(" ", "_")+".docx");
        try
        {
        	reportIndividuUtil.generateReportOdt(userAdmin.getFullname(),employee, profile,batch,listParticipantCompetencyLibraryFinalResult,dataForm.getIndividuReportDto(), response.getOutputStream());
            response.getOutputStream().flush();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
	        
		logger.debug("END");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/individualreport/email/{id}")
	public String email(@PathVariable("id") Long id,@RequestParam("email") String email,HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("BEGIN");
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		Participant participant = participantService.findById(id);
		Employee employee = employeeService.getByNik(participant.getNik());
		Profile profile = participantService.getProfileByParticipantId(id);
		Batch batch = participantService.getBatchByParticipantId(participant.getId());
		
		List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantId(id);
		ListIndividuReportDto dataForm = (ListIndividuReportDto) request.getSession().getAttribute(SessionConstants.ADMIN_REPORT_COMPETENCY_SELECT);
		
        try
        {
        	ByteArrayOutputStream os = new ByteArrayOutputStream();
        	
        	reportIndividuUtil.generateReportOdt(userAdmin.getFullname(), employee, profile,batch, listParticipantCompetencyLibraryFinalResult,dataForm.getIndividuReportDto(), os);
        	mailUtil.sendEmailReport(email, os, "Assessment_report_"+participant.getNik().trim().replaceAll(" ", "_") +participant.getFullName().trim().replaceAll(" ", "_")+".docx");
        	
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
	        
		logger.debug("END");
		return "redirect:/controller/pages/admin/administrator/assessement/participant/detail/"+id;
	}

}