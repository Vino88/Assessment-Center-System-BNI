package com.dlabs.acs.service.intf.inbasket;

import java.util.List;

import com.dlabs.acs.entity.inbasket.InbasketInbox;
import com.dlabs.acs.entity.inbasket.enumeration.InboxType;
import com.dlabs.acs.service.IService;

public interface IInbasketInboxService  extends IService<InbasketInbox,Long >{
	
	public Long countBySearch(String search);
	
	public List<InbasketInbox> getBySearch(String search, int start, int num);
	
	public InbasketInbox getByQuestNumber(int number);
	
	public List<InbasketInbox> getByInboxType(InboxType inboxType);
}
