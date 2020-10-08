package com.dlabs.acs.service.impl.inbasket;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.inbasket.IInbasketInboxInbasketQuestionDao;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxInbasketQuestionService;

@Service
@Transactional(readOnly = true)
public class InbasketInboxInbasketQuestionServiceImpl extends AbstractServiceImpl<InbasketInboxInbasketQuestion, Long> implements IInbasketInboxInbasketQuestionService{
	private Logger log = Logger.getLogger(InbasketInboxInbasketQuestionServiceImpl.class);
	
	@Autowired
	private IInbasketInboxInbasketQuestionDao inbasketinboxinbasketquestionDao;

	@Override
	public IAbstractDao<InbasketInboxInbasketQuestion, Long> getDao() {
		// TODO Auto-generated method stub
		return inbasketinboxinbasketquestionDao;
	}
	
	public Long countBySearch(String search){
		return inbasketinboxinbasketquestionDao.countBySearch(search);
	}
	
	public List<InbasketInboxInbasketQuestion> getBySearch(String search, int start, int num){
		return inbasketinboxinbasketquestionDao.getBySearch(search, start, num);
	}
	public InbasketInboxInbasketQuestion getByInboxQnumAndQuestionQnum(int inboxQnum, int questionQnum){
		return inbasketinboxinbasketquestionDao.getByInboxQnumAndQuestionQnum(inboxQnum, questionQnum);
	}
	
	public List<InbasketInboxInbasketQuestion> getByInboxId(Long inboxId){
		return inbasketinboxinbasketquestionDao.getByInboxId(inboxId);
	}
}
