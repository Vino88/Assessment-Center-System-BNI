package com.dlabs.acs.controller.alias;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.util.properties.ConfigProperties;

@Controller
@RequestMapping("/alias/visionspeech")
public class AliasVisionSpeechController {
	@Autowired
	private ConfigProperties configProperties;
	
	@RequestMapping(method = RequestMethod.GET, value = "/play")
    public String play()
    {
		return "/alias/vs/play";
    }
	
	@RequestMapping(value = "/preview/{id}", method = RequestMethod.GET)
	@ResponseBody public FileSystemResource preview(@PathVariable("id") String id, HttpServletResponse response) {
	    String path = configProperties.getVisionSpeechFilePath() + File.separator + id + ".webm";
	    return new FileSystemResource(path);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/play")
	@ResponseBody
    public boolean handleFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {
		Participant participant = (Participant) request.getSession().getAttribute(SessionConstants.PARTICIPANT);
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
}
