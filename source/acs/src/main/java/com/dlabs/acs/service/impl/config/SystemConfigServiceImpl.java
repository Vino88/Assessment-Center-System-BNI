package com.dlabs.acs.service.impl.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.config.ISystemConfigDao;
import com.dlabs.acs.entity.config.SystemConfig;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.config.ISystemConfigService;

@Service
@Transactional(readOnly = true)
public class SystemConfigServiceImpl extends AbstractServiceImpl<SystemConfig, Long> implements ISystemConfigService{
	private Logger log = Logger.getLogger(SystemConfigServiceImpl.class);
	
	@Autowired
	private ISystemConfigDao systemconfigDao;

	@Override
	public IAbstractDao<SystemConfig, Long> getDao() {
		// TODO Auto-generated method stub
		return systemconfigDao;
	}
	
	public Long countBySearch(String search){
		return systemconfigDao.countBySearch(search);
	}
	
	public List<SystemConfig> getBySearch(String search, int start, int num){
		return systemconfigDao.getBySearch(search, start, num);
	}
	
	public SystemConfig getByModuleAndSystemCode(String module, String systemCode)
	{
		return systemconfigDao.getByModuleAndSystemCode(module, systemCode);
	}
	
	public List<SystemConfig> getByModule(String module) {
		return systemconfigDao.getByModule(module);
	}
	
	public Map<String,String> getMapByModule(String module) {
		List<SystemConfig> list = systemconfigDao.getByModule(module);
		Map<String, String> map = new HashMap<String, String> ();
		
		for(SystemConfig systemConfig :list)
		{
			map.put(systemConfig.getSystemCode(), systemConfig.getSystemValue());
		}
		
		return map;
	}
}
