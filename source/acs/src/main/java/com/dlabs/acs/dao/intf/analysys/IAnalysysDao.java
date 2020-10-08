package com.dlabs.acs.dao.intf.analysys;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.AnalysysType;


public interface IAnalysysDao extends IAbstractDao <Analysys,Long>{
	
	public Long countBySearch(String search);
	
	public List<Analysys> getBySearch(String search, int start, int num);
	
	public List<Analysys> getByAnalysysType(AnalysysType analysysType);
	
	public Analysys getByQuestNumber(int number);
}
