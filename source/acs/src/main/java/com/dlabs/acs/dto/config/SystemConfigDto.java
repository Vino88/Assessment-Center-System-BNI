package com.dlabs.acs.dto.config;

import javax.validation.constraints.Size;

import com.dlabs.acs.dto.DataFormDto;

public class SystemConfigDto extends DataFormDto{
	@Size(max=100)
	private String module;
	@Size(max=100)
	private String systemCode;
	@Size(max=100)
	private String systemValue;
	@Size(max=255)
	private String description;
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getSystemValue() {
		return systemValue;
	}
	public void setSystemValue(String systemValue) {
		this.systemValue = systemValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}