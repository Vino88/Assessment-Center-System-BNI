package com.dlabs.acs.dao.intf.factsheet;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.factsheet.Profile;


public interface IProfileDao extends IAbstractDao <Profile,Long>{
	
	public Long countBySearch(String search);
	
	public List<Profile> getBySearch(String search, int start, int num);
	
	public Profile getByKey(String key);
}
