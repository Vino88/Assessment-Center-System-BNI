package com.dlabs.acs.dao.intf.config;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.config.SystemConfig;


public interface ISystemConfigDao extends IAbstractDao <SystemConfig,Long>{
	
	public Long countBySearch(String search);
	
	public List<SystemConfig> getBySearch(String search, int start, int num);
	
	public SystemConfig getByModuleAndSystemCode(String module, String systemCode);
	
	public List<SystemConfig> getByModule(String module);
}
