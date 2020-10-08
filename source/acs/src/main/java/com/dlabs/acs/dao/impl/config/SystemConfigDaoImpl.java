package com.dlabs.acs.dao.impl.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.config.ISystemConfigDao;
import com.dlabs.acs.entity.config.SystemConfig;

@Repository
public class SystemConfigDaoImpl extends AbstractDaoImpl<SystemConfig, Long> implements ISystemConfigDao {
	private Logger log = Logger.getLogger(SystemConfigDaoImpl.class);
	
	protected SystemConfigDaoImpl() {
		super(SystemConfig.class);
	}

	private static final String GET_BY_SEARCH = "SystemConfig.getBySearch";
	private static final String COUNT_BY_SEARCH = "SystemConfig.countBySearch";
	
	
	public List<SystemConfig> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<SystemConfig> result = query.list();
		if (result == null) {
			result = new ArrayList<SystemConfig>();
		}
		return result;
	}
	
	public Long countBySearch(String search) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");

		List<Long> result = query.list();
		
		return result.get(0);
	}
	
	
	private static final String GET_BY_MODULE_AND_SYSTEMCODE = "SystemConfig.getByModuleAndSystemCode";
	public SystemConfig getByModuleAndSystemCode(String module, String systemCode) {
		if(module == null || module.trim().length() == 0 || systemCode == null || systemCode.trim().length() == 0)
		{
			return null;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_MODULE_AND_SYSTEMCODE);
		query.setString("module", module);
		query.setString("systemCode", systemCode);
		
		query.setMaxResults(1);
		
		List<SystemConfig> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	
	private static final String GET_BY_MODULE = "SystemConfig.getByModule";
	public List<SystemConfig> getByModule(String module) {
		if(module == null || module.trim().length() == 0)
		{
			return Collections.EMPTY_LIST;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_MODULE);
		query.setString("module", module);
		
		List<SystemConfig> result = query.list();
		if (result == null) {
			result = new ArrayList<SystemConfig>();
		}
		return result;
	}
}
