package com.dlabs.acs.dao.intf.cap;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.cap.Cap;


public interface ICapDao extends IAbstractDao <Cap,Long>{
	
	public Long countBySearch(String search);
	
	public List<Cap> getBySearch(String search, int start, int num);
	
	public Cap getByQuestNumber(int number);
}
