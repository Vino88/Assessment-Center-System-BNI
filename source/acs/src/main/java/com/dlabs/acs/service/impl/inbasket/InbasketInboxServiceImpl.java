package com.dlabs.acs.service.impl.inbasket;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.inbasket.IInbasketInboxDao;
import com.dlabs.acs.entity.inbasket.InbasketInbox;
import com.dlabs.acs.entity.inbasket.enumeration.InboxType;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxService;

@Service
@Transactional(readOnly = true)
public class InbasketInboxServiceImpl extends AbstractServiceImpl<InbasketInbox, Long> implements IInbasketInboxService{
	private Logger log = Logger.getLogger(InbasketInboxServiceImpl.class);
	
	@Autowired
	private IInbasketInboxDao inbasketinboxDao;

	@Override
	public IAbstractDao<InbasketInbox, Long> getDao() {
		// TODO Auto-generated method stub
		return inbasketinboxDao;
	}
	
	public Long countBySearch(String search){
		return inbasketinboxDao.countBySearch(search);
	}
	
	public List<InbasketInbox> getBySearch(String search, int start, int num){
		return inbasketinboxDao.getBySearch(search, start, num);
	}
	
	public InbasketInbox getByQuestNumber(int number)
	{
		return inbasketinboxDao.getByQuestNumber(number);
	}
	
	public List<InbasketInbox> getByInboxType(InboxType inboxType)
	{
		return inbasketinboxDao.getByInboxType(inboxType);
	}
}
