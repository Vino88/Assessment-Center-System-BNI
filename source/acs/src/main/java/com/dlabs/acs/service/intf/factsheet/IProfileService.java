package com.dlabs.acs.service.intf.factsheet;

import java.util.List;

import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.service.IService;

public interface IProfileService  extends IService<Profile,Long >{
	
	public Long countBySearch(String search);
	
	public List<Profile> getBySearch(String search, int start, int num);
	
	public Profile getByKey(String key);
}
