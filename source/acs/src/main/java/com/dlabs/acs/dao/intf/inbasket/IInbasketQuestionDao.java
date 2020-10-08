package com.dlabs.acs.dao.intf.inbasket;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.inbasket.InbasketQuestion;


public interface IInbasketQuestionDao extends IAbstractDao <InbasketQuestion,Long>{
	
	public Long countBySearch(String search);
	
	public List<InbasketQuestion> getBySearch(String search, int start, int num);
	
	public InbasketQuestion getByQuestNumber(int number);
}
