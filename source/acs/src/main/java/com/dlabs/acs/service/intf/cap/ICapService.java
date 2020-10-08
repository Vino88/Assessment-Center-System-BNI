package com.dlabs.acs.service.intf.cap;

import java.util.List;

import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.service.IService;

public interface ICapService  extends IService<Cap,Long >{
	
	public Long countBySearch(String search);
	
	public List<Cap> getBySearch(String search, int start, int num);
	
	public Cap getByQuestNumber(int number);
}
