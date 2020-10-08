package com.dlabs.acs.controller.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.constants.SystemConfigConstants;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantAnalysys;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.enumeration.LogDuringStatus;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.service.intf.analysys.IAnalysysService;
import com.dlabs.acs.service.intf.assessement.IParticipantAnalysysService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.util.DateUtil;
import com.dlabs.acs.util.properties.ConfigProperties;

@Controller
@RequestMapping("/pages/view/visionspeech")
public class VisionSpeechController {
	@Autowired
	private ConfigProperties configProperties;
	
	private Logger logger =Logger.getLogger(AnalysysController.class);
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	@Autowired
	private IAnalysysService analysysService;
	@Autowired
	private IParticipantAnalysysService participantAnalysysService;
	@Autowired
	private IParticipantService participantService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/play")
    public String play(@RequestParam("now") Long now)
    {
		return "vs-play";
    }
	
	@RequestMapping(value = "/preview/{id}", method = RequestMethod.GET)
	@ResponseBody public FileSystemResource preview(@RequestParam("now") Long now, @PathVariable("id") String id, HttpServletResponse response) {
	    String path = configProperties.getVisionSpeechFilePath() + File.separator + id + ".webm";
	    return new FileSystemResource(path);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/play")
	@ResponseBody
    public boolean handleFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {
		Participant participant = (Participant) request.getSession().getAttribute(SessionConstants.PARTICIPANT);
		
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) request.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setVisionUploaded(true);
		participantLogDuring.setVisionCount(participantLogDuring.getVisionCount()+1);
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		request.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		File visionSpeechFolder = new File(configProperties.getVisionSpeechFilePath());
		if(!visionSpeechFolder.exists())
		{
			visionSpeechFolder.mkdirs();
		}
		
		File fileVs = new File(visionSpeechFolder, participant.getId() +".webm");
		if(!fileVs.exists())
		{
			try {
				fileVs.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
        
        try {
        	OutputStream is = new FileOutputStream(fileVs);
			FileCopyUtils.copy(file.getInputStream(), is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return true;
    }
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		logger.debug("END");
		return "/view/visionspeech/home";
	}
	
	@RequestMapping(value = "/detail/visionspeechvs/{analysysId}", method = RequestMethod.GET)
	public String viewDetailVisionSpeechVs(@PathVariable("analysysId") Long analysysId, Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		
		if(participantLogDuring.getVisionStartTime() == null)
		{
			Map<String, String> mapTimerSystemConfig = (Map<String, String>) httpRequest.getSession().getAttribute(SessionConstants.SYSTEM_CONFIG_TIMER);
			
			String vsTimerStr = mapTimerSystemConfig.get(SystemConfigConstants.TIMER.TIMER_VISION_SPEECH_ALL);
			int vsTimerInt = SystemConfigConstants.TIMER.DEFAULT_VISION_SPEECH_ALL;
			try
			{
				vsTimerInt = Integer.parseInt(vsTimerStr);
			}catch(Exception e){ vsTimerInt = SystemConfigConstants.TIMER.DEFAULT_VISION_SPEECH_ALL;}
			
			
			
			Calendar endTime = Calendar.getInstance();
			endTime.add(Calendar.MINUTE, vsTimerInt);
			
			participantLogDuring.setVisionEndTime(endTime.getTime());
			participantLogDuring.setVisionStartTime(new Date());
			participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
			participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
			
			participantLogDuringService.saveOrUpdate(participantLogDuring);
			httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		}
		
		
		Analysys analysys = analysysService.findById(analysysId);
		model.addAttribute("analysys", analysys);
		
		ParticipantAnalysys participantAnalysys = participantAnalysysService.getByPartIdAndAnalysysId(participant.getId(), analysysId);
		
		if(participantAnalysys == null)
		{
			Date now = new Date();
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(participant.getNik());
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(participant.getNik());
			commonFields.setLastModifiedDate(now);
			
			participantAnalysys = new ParticipantAnalysys();
			participantAnalysys.setCommonFields(commonFields);
			
			participantAnalysys.setParticipant(participant);
			participantAnalysys.setAnalysys(analysys);
			
			participantAnalysysService.save(participantAnalysys);
		}
	
		model.addAttribute("participantAnalysys", participantAnalysys);
		int remainingTime = DateUtil.calculateRemainingTime(participantLogDuring.getVisionEndTime());
		model.addAttribute("remainingTime", remainingTime);
		logger.debug("END");
		return "/view/visionspeech/detail/visionspeech/vs";
	}
	
	@RequestMapping(value = "/beforefinish", method = RequestMethod.GET)
	public String beforefinish(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		
		logger.debug("END");
		return "/view/visionspeech/finish";
	}
	
	@RequestMapping(value = "/finish", method = RequestMethod.GET)
	public String finish(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setLogDuringStatus(LogDuringStatus.FINISH);
		participantLogDuring.setVisionFinishTime(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		participant.setParticipantStatus(ParticipantStatus.PARTICIPANT_FINISH);
		participant.getCommonFields().setLastModifiedDate(new Date());
		participant.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantService.saveOrUpdate(participant);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT, participant);
		
		httpRequest.getSession().setAttribute(SessionConstants.UNAUTHORIZED_MESSAGE, "message.simulation.finish");
		return "redirect:/controller/pages/view/logout/secondhalf";
	}
	
	@RequestMapping(value = "/expired", method = RequestMethod.GET)
	public String expired(Model model, HttpServletRequest httpRequest) {
		logger.debug("BEGIN");
		Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
		ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
		participantLogDuring.setLogDuringStatus(LogDuringStatus.FINISH);
		participantLogDuring.setVisionFinishTime(new Date());
		participantLogDuring.getCommonFields().setLastModifiedDate(new Date());
		participantLogDuring.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantLogDuringService.saveOrUpdate(participantLogDuring);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT_LOG_DURING, participantLogDuring);
		
		participant.setParticipantStatus(ParticipantStatus.PARTICIPANT_FINISH);
		participant.getCommonFields().setLastModifiedDate(new Date());
		participant.getCommonFields().setLastModifiedBy(participant.getNik());
		
		participantService.saveOrUpdate(participant);
		httpRequest.getSession().setAttribute(SessionConstants.PARTICIPANT, participant);
		
		httpRequest.getSession().setAttribute(SessionConstants.UNAUTHORIZED_MESSAGE, "message.simulation.finish");
		return "redirect:/controller/pages/view/logout/secondhalf";
	}
}
