package com.dlabs.acs.rest.admin.administrator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlabs.acs.dto.ImageUploadDto;
import com.dlabs.acs.util.properties.ConfigProperties;


@Controller
@RequestMapping("rest/admin/administrator/image")
public class AdminImageUploaderRest {
	private Logger logger = Logger.getLogger(AdminImageUploaderRest.class);
	@Autowired
	private ConfigProperties configProperties;
	
	@RequestMapping(method = RequestMethod.POST, value = "/upload")
	@ResponseBody
    public ImageUploadDto handleFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {
		logger.debug("BEGIN");
		File baseFolder = new File(configProperties.getImageFilePath());
		if(!baseFolder.exists())
		{
			baseFolder.mkdirs();
		}
		
		String filename = null;
		if(file!= null && file.getOriginalFilename() != null && file.getOriginalFilename().trim().length() > 0 && file.getOriginalFilename().lastIndexOf(".") > 0)
		{
			filename = Long.toString(Calendar.getInstance().getTimeInMillis()) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
		}
		
		File fileVs = new File(baseFolder,filename);
		if(!fileVs.exists())
		{
			try {
				fileVs.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
        try {
        	OutputStream is = new FileOutputStream(fileVs);
			FileCopyUtils.copy(file.getInputStream(), is);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        logger.debug("END");
        ImageUploadDto imageUploadDto = new ImageUploadDto();
        imageUploadDto.setLocation(configProperties.getImageUrlPath()+filename);
        return imageUploadDto;
    }
}
