package com.dlabs.acs.service.intf.inbasket;

import java.util.List;

import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;
import com.dlabs.acs.service.IService;

public interface IInbasketInboxInbasketQuestionService  extends IService<InbasketInboxInbasketQuestion,Long >{
	
	public Long countBySearch(String search);
	
	public List<InbasketInboxInbasketQuestion> getBySearch(String search, int start, int num);
	
	public InbasketInboxInbasketQuestion getByInboxQnumAndQuestionQnum(int inboxQnum, int questionQnum);
	
	public List<InbasketInboxInbasketQuestion> getByInboxId(Long inboxId);
}
