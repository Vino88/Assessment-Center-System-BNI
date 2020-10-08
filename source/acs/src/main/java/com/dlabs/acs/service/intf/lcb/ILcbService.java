package com.dlabs.acs.service.intf.lcb;

import java.util.List;

import com.dlabs.acs.entity.lcb.Lcb;
import com.dlabs.acs.service.IService;

public interface ILcbService  extends IService<Lcb,Long >{
	
	public Long countBySearch(String search);
	
	public List<Lcb> getBySearch(String search, int start, int num);
	
	public Lcb getByQuestNumber(int number);
}
