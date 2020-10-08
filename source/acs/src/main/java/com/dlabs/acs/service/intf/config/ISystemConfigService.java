package com.dlabs.acs.service.intf.config;

import java.util.List;
import java.util.Map;

import com.dlabs.acs.entity.config.SystemConfig;
import com.dlabs.acs.service.IService;

public interface ISystemConfigService  extends IService<SystemConfig,Long >{
	
	public Long countBySearch(String search);
	
	public List<SystemConfig> getBySearch(String search, int start, int num);
	
	public SystemConfig getByModuleAndSystemCode(String module, String systemCode);
	
	public List<SystemConfig> getByModule(String module);
	
	public Map<String,String> getMapByModule(String module);
}
