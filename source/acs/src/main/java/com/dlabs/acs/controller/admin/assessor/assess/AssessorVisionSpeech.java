package com.dlabs.acs.controller.admin.assessor.assess;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlabs.acs.util.properties.ConfigProperties;

@Controller
@RequestMapping("/pages/admin/assessor/assess/visionspeech")
public class AssessorVisionSpeech {
	@Autowired
	private ConfigProperties configProperties;
	
	@RequestMapping(value = "/preview/{id}", method = RequestMethod.GET)
	@ResponseBody public FileSystemResource preview(@PathVariable("id") String id, HttpServletResponse response) {
	    String path = configProperties.getVisionSpeechFilePath() + File.separator + id + ".webm";
	    return new FileSystemResource(path);
	}
}
