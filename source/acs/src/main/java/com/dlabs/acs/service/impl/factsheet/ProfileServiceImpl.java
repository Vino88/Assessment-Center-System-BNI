package com.dlabs.acs.service.impl.factsheet;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.factsheet.IProfileDao;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.factsheet.IProfileService;

@Service
@Transactional(readOnly = true)
public class ProfileServiceImpl extends AbstractServiceImpl<Profile, Long> implements IProfileService{
	private Logger log = Logger.getLogger(ProfileServiceImpl.class);
	
	@Autowired
	private IProfileDao profileDao;

	@Override
	public IAbstractDao<Profile, Long> getDao() {
		// TODO Auto-generated method stub
		return profileDao;
	}
	
	public Long countBySearch(String search){
		return profileDao.countBySearch(search);
	}
	
	public List<Profile> getBySearch(String search, int start, int num){
		return profileDao.getBySearch(search, start, num);
	}
	
	public Profile getByKey(String key){
		return profileDao.getByKey(key);
	}
}
