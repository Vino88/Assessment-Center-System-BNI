package com.dlabs.acs.dao.intf.lcb;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.lcb.Lcb;


public interface ILcbDao extends IAbstractDao <Lcb,Long>{
	
	public Long countBySearch(String search);
	
	public List<Lcb> getBySearch(String search, int start, int num);
	
	public Lcb getByQuestNumber(int number);
}
