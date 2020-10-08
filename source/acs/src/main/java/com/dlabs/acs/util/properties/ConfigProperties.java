package com.dlabs.acs.util.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:config.properties")
public class ConfigProperties {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${visionspeech.file.path}")
	private String visionSpeechFilePath;
	
	@Value("${visionspeech.url.path}")
	private String visionSpeechUrlPath;
	
	@Value("${image.file.path}")
	private String imageFilePath;
	
	@Value("${image.url.path}")
	private String imageUrlPath;
	
	
	@Value("${profile.file.path}")
	private String profileFilePath;
	
	@Value("${profile.url.path}")
	private String profileUrlPath;
	
	@Value("${init.generate}")
	private boolean initGenerate;

	public String getVisionSpeechFilePath() {
		return visionSpeechFilePath;
	}

	public void setVisionSpeechFilePath(String visionSpeechFilePath) {
		this.visionSpeechFilePath = visionSpeechFilePath;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getImageUrlPath() {
		return imageUrlPath;
	}

	public void setImageUrlPath(String imageUrlPath) {
		this.imageUrlPath = imageUrlPath;
	}

	public String getProfileFilePath() {
		return profileFilePath;
	}

	public void setProfileFilePath(String profileFilePath) {
		this.profileFilePath = profileFilePath;
	}

	public String getProfileUrlPath() {
		return profileUrlPath;
	}

	public void setProfileUrlPath(String profileUrlPath) {
		this.profileUrlPath = profileUrlPath;
	}

	public String getVisionSpeechUrlPath() {
		return visionSpeechUrlPath;
	}

	public void setVisionSpeechUrlPath(String visionSpeechUrlPath) {
		this.visionSpeechUrlPath = visionSpeechUrlPath;
	}

	public boolean isInitGenerate() {
		return initGenerate;
	}

	public void setInitGenerate(boolean initGenerate) {
		this.initGenerate = initGenerate;
	}
}
