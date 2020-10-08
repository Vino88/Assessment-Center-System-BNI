package com.dlabs.acs.service.intf.inbasket;

import java.util.List;

import com.dlabs.acs.entity.inbasket.InbasketQuestion;
import com.dlabs.acs.service.IService;

public interface IInbasketQuestionService  extends IService<InbasketQuestion,Long >{
	
	public Long countBySearch(String search);
	
	public List<InbasketQuestion> getBySearch(String search, int start, int num);
	
	public InbasketQuestion getByQuestNumber(int number);
}
