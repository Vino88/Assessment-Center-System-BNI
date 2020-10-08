package com.dlabs.acs.controller.admin.assessor.assess;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.dto.assessementresult.ParticipantAssessmentResultDto;
import com.dlabs.acs.dto.assessementresult.ParticipantCapCompetencyLibraryCapDto;
import com.dlabs.acs.dto.assessementresult.ParticipantCompetencyResultDisplayDto;
import com.dlabs.acs.dto.assessementresult.ParticipantListAssessmentResultDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantCap;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.entity.cap.CompetencyLibraryCap;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;
import com.dlabs.acs.entity.participant.ParticipantLogPost;
import com.dlabs.acs.service.intf.assessement.IParticipantCapService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;
import com.dlabs.acs.service.intf.cap.ICapService;
import com.dlabs.acs.service.intf.cap.ICompetencyLibraryCapService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryBehaviourService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;

@Controller
@RequestMapping("/pages/admin/assessor/assess/cap")
public class AssessorAssessCapController {
	private Logger logger =Logger.getLogger(AssessorAssessCapController.class);
	
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantLogPostService participantLogPostService;
	@Autowired
	private IParticipantCompetencyLibraryFinalResultService participantCompetencyLibraryFinalResultService;
	
	
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	@Autowired
	private ICompetencyLibraryBehaviourService competencyLibraryBehaviourService;
	
	@Autowired
	private ICapService capService;
	@Autowired
	private ICompetencyLibraryCapService competencyLibraryCapService;
	@Autowired
	private IParticipantCapService participantCapService;
	
	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public String list(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		List<Cap> listCap = capService.getAll();
		
		List<CompetencyLibraryCap> listCompetencyCap = competencyLibraryCapService.getAll();
		
		List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantId(id);
		
		Map<Long, Integer> mapCompetencyIdAndFlag = new HashMap<Long, Integer>();
		for(ParticipantCompetencyLibraryFinalResult pc : listParticipantCompetencyLibraryFinalResult)
		{
			mapCompetencyIdAndFlag.put(pc.getCompetencyLibrary().getId(), pc.getCapRating());
		}
		
		Map<Long, Boolean> mapCapIdAndFlag = new HashMap<Long, Boolean>();
		
		Integer rating = null;
		boolean flag  = false;
		for(CompetencyLibraryCap cl : listCompetencyCap)
		{
			if(mapCapIdAndFlag.get(cl.getCap().getId()) == null || mapCapIdAndFlag.get(cl.getCap().getId()) == false)
			{
				rating = mapCompetencyIdAndFlag.get(cl.getCompetencyLibrary().getId());
				if(rating != null)
				{
					flag = rating > 0;
				}
				else
				{
					flag = false;
				}
				
				mapCapIdAndFlag.put(cl.getCap().getId(),  flag);
			}
		}
		
		
		ParticipantCapCompetencyLibraryCapDto participantCapCompetencyLibraryCapDto = null;		
		List<ParticipantCapCompetencyLibraryCapDto> listParticipantCap = new ArrayList<ParticipantCapCompetencyLibraryCapDto> ();
		
		for(Cap cap : listCap)
		{
			participantCapCompetencyLibraryCapDto = new ParticipantCapCompetencyLibraryCapDto();
			participantCapCompetencyLibraryCapDto.setCap(cap);
			participantCapCompetencyLibraryCapDto.setFinished(mapCapIdAndFlag.get(cap.getId()));
			listParticipantCap.add(participantCapCompetencyLibraryCapDto);
		}
		
		
		model.addAttribute("listParticipantCap", listParticipantCap);
		logger.debug("END");
		return "/pages/admin/assessor/assess/cap/list";
	}
	
	@RequestMapping(value = "/capdetail/{id}/{capId}", method = RequestMethod.GET)
	public String capdetail(Model model, @PathVariable("id")Long id, @PathVariable("capId")Long capId) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		
		Cap cap =  capService.findById(capId);
		model.addAttribute("cap", cap);
		
		ParticipantCap participantCap = participantCapService.getByParticipantIdAndCapId(id, capId);
		model.addAttribute("participantCap", participantCap);
		
		List<CompetencyLibrary> competencyLibraries = competencyLibraryCapService.getCompetenciesByCapId(capId);
		
		List<ParticipantCapCompetencyLibraryCapDto> listParticipantCapCompetencyLibraryCapDto = new ArrayList<ParticipantCapCompetencyLibraryCapDto> ();
		ParticipantCapCompetencyLibraryCapDto participantCapCompetencyLibraryCapDto = null;
		
		for(CompetencyLibrary competencyLibrary : competencyLibraries)
		{
			participantCapCompetencyLibraryCapDto = new ParticipantCapCompetencyLibraryCapDto();
			
			
			participantCapCompetencyLibraryCapDto.setCompetencyLibrary(competencyLibrary);
			participantCapCompetencyLibraryCapDto.setCompetencyLibraryBehaviours(competencyLibraryBehaviourService.getByCompetencyLibraryId(competencyLibrary.getId()));
			
			participantCapCompetencyLibraryCapDto.setParticipantCompetencyLibraryFinalResult(participantCompetencyLibraryFinalResultService.getByParticipantIdAndCompetencyLibraryId(id, competencyLibrary.getId()));
			
			listParticipantCapCompetencyLibraryCapDto.add(participantCapCompetencyLibraryCapDto);
		}
		
		model.addAttribute("listParticipantCapCompetencyLibraryCapDto", listParticipantCapCompetencyLibraryCapDto);
		
		logger.debug("END");
		return "/pages/admin/assessor/assess/cap/capdetail";
	}
	
	
	@RequestMapping(value = "/capdetail/assess", method = RequestMethod.POST)
	public String assessCap(@Valid @ModelAttribute("dataForm") ParticipantListAssessmentResultDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		logger.debug("BEGIN");
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("plainErrorMessage", result.getAllErrors().get(0).toString());
			return "redirect:/controller/pages/admin/assessor/assess/cap/capdetail/"+dataForm.getParticipantId()+"/"+dataForm.getSimulationId();    
		}
		
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);

		Date now = new Date();
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(userAdmin.getEmail());
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(userAdmin.getEmail());
		commonFields.setLastModifiedDate(now);
		
		for(ParticipantAssessmentResultDto resultDto : dataForm.getListResult())
		{
			ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantIdAndCompetencyLibraryId(dataForm.getParticipantId(), resultDto.getCompetencyId());
			
			if(participantCompetencyLibraryFinalResult == null)
			{
				participantCompetencyLibraryFinalResult = new ParticipantCompetencyLibraryFinalResult();
				participantCompetencyLibraryFinalResult.setCommonFields(commonFields);
				
				Participant participant =participantService.findById(dataForm.getParticipantId());
				participantCompetencyLibraryFinalResult.setParticipant(participant);
				
				CompetencyLibrary competencyLibrary = competencyLibraryService.findById(resultDto.getCompetencyId());
				participantCompetencyLibraryFinalResult.setCompetencyLibrary(competencyLibrary);
			}
			
			participantCompetencyLibraryFinalResult.getCommonFields().setLastModifiedBy(userAdmin.getEmail());
			participantCompetencyLibraryFinalResult.getCommonFields().setLastModifiedDate(now);
			
			if(resultDto.getRating() != null)
			{
				participantCompetencyLibraryFinalResult.setCapRating(resultDto.getRating());
			}
			
			participantCompetencyLibraryFinalResult.setCapNotes(resultDto.getNotes());
			participantCompetencyLibraryFinalResult.setCapFlag(resultDto.getAsessmentFlag());
			
			participantCompetencyLibraryFinalResultService.saveOrUpdate(participantCompetencyLibraryFinalResult);
		}
		
		
		
		logger.debug("END");
		return "redirect:/controller/pages/admin/assessor/assess/cap/list/"+dataForm.getParticipantId();
	}
	
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id")Long id) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		List<CompetencyLibrary> listCompetency = competencyLibraryCapService.getDistinctCompetencyLibrary();
		List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantId(id);
		
		
		List<ParticipantCompetencyResultDisplayDto> listParticipantCompetencyResultDisplayDto = new ArrayList<ParticipantCompetencyResultDisplayDto> ();
		ParticipantCompetencyResultDisplayDto participantCompetencyResultDisplayDto = null;
		for(CompetencyLibrary cl : listCompetency)
		{
			participantCompetencyResultDisplayDto = new ParticipantCompetencyResultDisplayDto();
			
			participantCompetencyResultDisplayDto.setCompetencyLibrary(cl);
			
			for(ParticipantCompetencyLibraryFinalResult pc : listParticipantCompetencyLibraryFinalResult)
			{
				if(pc.getCompetencyLibrary().getId().equals(cl.getId()))
				{
					participantCompetencyResultDisplayDto.setParticipantCompetencyLibraryFinalResult(pc);
				}
			}
			
			listParticipantCompetencyResultDisplayDto.add(participantCompetencyResultDisplayDto);
		}
		
		
		model.addAttribute("listParticipantCompetencyResultDisplayDto", listParticipantCompetencyResultDisplayDto);
		logger.debug("END");
		return "/pages/admin/assessor/assess/cap/view";
	}
	
	@RequestMapping(value = "/competency/{id}/{competencyId}", method = RequestMethod.GET)
	public String competency(Model model, @PathVariable("id")Long id, @PathVariable("competencyId")Long competencyId) {
		logger.debug("BEGIN");
		Participant participant =participantService.findById(id);
		model.addAttribute("participant", participant);
		
		CompetencyLibrary competencyLibrary = competencyLibraryService.findById(competencyId);
		model.addAttribute("competencyLibrary", competencyLibrary);
		
		Cap cap =  competencyLibraryCapService.getCapByCompetencyId(competencyId);
		model.addAttribute("cap", cap);
		
		ParticipantCap participantCap = participantCapService.getByParticipantIdAndCapId(id, cap.getId());
		model.addAttribute("participantCap", participantCap);
		
		List<CompetencyLibraryBehaviour> competencyLibraryBehaviours = competencyLibraryBehaviourService.getByCompetencyLibraryId(competencyId);
		model.addAttribute("competencyLibraryBehaviours", competencyLibraryBehaviours);
		
		ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantIdAndCompetencyLibraryId(id, competencyId);
		model.addAttribute("participantCompetencyLibraryFinalResult", participantCompetencyLibraryFinalResult);
		logger.debug("END");
		return "/pages/admin/assessor/assess/cap/competency";
	}
	
	@RequestMapping(value = "/competency/assess", method = RequestMethod.POST)
	public String assess(@Valid @ModelAttribute("dataForm") ParticipantAssessmentResultDto dataForm,  BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		logger.debug("BEGIN");
		if (result.hasErrors()) {
			model.addAttribute("errorMessage", result.getAllErrors().get(0).toString());
			return "redirect:/controller/pages/admin/assessor/assess/cap/competency/"+dataForm.getParticipantId()+"/"+dataForm.getCompetencyId();    
		}
		
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);

		Date now = new Date();
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(userAdmin.getEmail());
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(userAdmin.getEmail());
		commonFields.setLastModifiedDate(now);
		
		ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultService.getByParticipantIdAndCompetencyLibraryId(dataForm.getParticipantId(), dataForm.getCompetencyId());
		
		if(participantCompetencyLibraryFinalResult == null)
		{
			participantCompetencyLibraryFinalResult = new ParticipantCompetencyLibraryFinalResult();
			participantCompetencyLibraryFinalResult.setCommonFields(commonFields);
			
			Participant participant =participantService.findById(dataForm.getParticipantId());
			participantCompetencyLibraryFinalResult.setParticipant(participant);
			
			CompetencyLibrary competencyLibrary = competencyLibraryService.findById(dataForm.getCompetencyId());
			participantCompetencyLibraryFinalResult.setCompetencyLibrary(competencyLibrary);
		}
		
		participantCompetencyLibraryFinalResult.getCommonFields().setLastModifiedBy(userAdmin.getEmail());
		participantCompetencyLibraryFinalResult.getCommonFields().setLastModifiedDate(now);
		
		participantCompetencyLibraryFinalResult.setCapRating(dataForm.getRating());
		participantCompetencyLibraryFinalResult.setCapNotes(dataForm.getNotes());
		participantCompetencyLibraryFinalResult.setCapFlag(dataForm.getAsessmentFlag());
		
		participantCompetencyLibraryFinalResultService.saveOrUpdate(participantCompetencyLibraryFinalResult);
		
		logger.debug("END");
		return "redirect:/controller/pages/admin/assessor/assess/cap/view/"+dataForm.getParticipantId();
	}
	
	@RequestMapping(value = "/finish/{id}", method = RequestMethod.POST)
	public String finish(Model model, @PathVariable("id")Long id, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		logger.debug("BEGIN");
		UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		
		
		Participant participant =participantService.findById(id);
		participant.setParticipantStatus(ParticipantStatus.PARTIAL_RATING);
		participant.getCommonFields().setLastModifiedBy(userAdmin.getEmail());
		participant.getCommonFields().setLastModifiedDate(new Date());
		
		participantService.saveOrUpdate(participant);
		
		
		ParticipantLogPost participantLogPost = participantLogPostService.getByParticipantIdFirst(id);
		
		participantLogPost.setAssessCapFinishTime(new Date());
		
		participantLogPost.getCommonFields().setLastModifiedBy(userAdmin.getEmail());
		participantLogPost.getCommonFields().setLastModifiedDate(new Date());
		
		participantLogPostService.saveOrUpdate(participantLogPost);
		
		return "redirect:/controller/pages/admin/assessor/assess/home/view/"+id;
	}
}
