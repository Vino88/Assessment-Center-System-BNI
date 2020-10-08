package com.dlabs.acs.service.impl.inbasket;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.inbasket.ICompetencyLibraryInbasketInboxInbasketQuestionDao;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.inbasket.CompetencyLibraryInbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.inbasket.ICompetencyLibraryInbasketInboxInbasketQuestionService;

@Service
@Transactional(readOnly = true)
public class CompetencyLibraryInbasketInboxInbasketQuestionServiceImpl extends AbstractServiceImpl<CompetencyLibraryInbasketInboxInbasketQuestion, Long> implements ICompetencyLibraryInbasketInboxInbasketQuestionService{
	private Logger log = Logger.getLogger(CompetencyLibraryInbasketInboxInbasketQuestionServiceImpl.class);
	
	@Autowired
	private ICompetencyLibraryInbasketInboxInbasketQuestionDao competencylibraryinbasketinboxinbasketquestionDao;

	@Override
	public IAbstractDao<CompetencyLibraryInbasketInboxInbasketQuestion, Long> getDao() {
		// TODO Auto-generated method stub
		return competencylibraryinbasketinboxinbasketquestionDao;
	}
	
	public Long countBySearch(String search){
		return competencylibraryinbasketinboxinbasketquestionDao.countBySearch(search);
	}
	
	public List<CompetencyLibraryInbasketInboxInbasketQuestion> getBySearch(String search, int start, int num){
		return competencylibraryinbasketinboxinbasketquestionDao.getBySearch(search, start, num);
	}
	
	public List<CompetencyLibrary> getDistinctCompetencyLibrary(){
		return competencylibraryinbasketinboxinbasketquestionDao.getDistinctCompetencyLibrary();
	}
	
	public List<InbasketInboxInbasketQuestion> getIiiqByCompetencyId(Long competencyLibraryId){
		return competencylibraryinbasketinboxinbasketquestionDao.getIiiqByCompetencyId(competencyLibraryId);
	}
}
