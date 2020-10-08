package com.dlabs.acs.service.impl.inbasket;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.inbasket.IInbasketQuestionDao;
import com.dlabs.acs.entity.inbasket.InbasketQuestion;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.inbasket.IInbasketQuestionService;

@Service
@Transactional(readOnly = true)
public class InbasketQuestionServiceImpl extends AbstractServiceImpl<InbasketQuestion, Long> implements IInbasketQuestionService{
	private Logger log = Logger.getLogger(InbasketQuestionServiceImpl.class);
	
	@Autowired
	private IInbasketQuestionDao inbasketquestionDao;

	@Override
	public IAbstractDao<InbasketQuestion, Long> getDao() {
		// TODO Auto-generated method stub
		return inbasketquestionDao;
	}
	
	public Long countBySearch(String search){
		return inbasketquestionDao.countBySearch(search);
	}
	
	public List<InbasketQuestion> getBySearch(String search, int start, int num){
		return inbasketquestionDao.getBySearch(search, start, num);
	}
	
	public InbasketQuestion getByQuestNumber(int number){
		return inbasketquestionDao.getByQuestNumber(number);
	}
}
