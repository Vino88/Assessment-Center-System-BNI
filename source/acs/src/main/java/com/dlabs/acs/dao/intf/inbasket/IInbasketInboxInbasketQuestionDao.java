package com.dlabs.acs.dao.intf.inbasket;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;


public interface IInbasketInboxInbasketQuestionDao extends IAbstractDao <InbasketInboxInbasketQuestion,Long>{
	
	public Long countBySearch(String search);
	
	public List<InbasketInboxInbasketQuestion> getBySearch(String search, int start, int num);
	
	public InbasketInboxInbasketQuestion getByInboxQnumAndQuestionQnum(int inboxQnum, int questionQnum);
	
	public List<InbasketInboxInbasketQuestion> getByInboxId(Long inboxId);
}
