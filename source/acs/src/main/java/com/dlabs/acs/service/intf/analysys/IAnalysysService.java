package com.dlabs.acs.service.intf.analysys;

import java.util.List;

import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.AnalysysType;
import com.dlabs.acs.service.IService;

public interface IAnalysysService  extends IService<Analysys,Long >{
	
	public Long countBySearch(String search);
	
	public List<Analysys> getBySearch(String search, int start, int num);
	
	public List<Analysys> getByAnalysysType(AnalysysType analysysType);
	
	public Analysys getByQuestNumber(int number);
}
