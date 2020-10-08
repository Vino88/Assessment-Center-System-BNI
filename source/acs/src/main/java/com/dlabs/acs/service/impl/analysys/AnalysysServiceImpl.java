package com.dlabs.acs.service.impl.analysys;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.analysys.IAnalysysDao;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.AnalysysType;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.analysys.IAnalysysService;

@Service
@Transactional(readOnly = true)
public class AnalysysServiceImpl extends AbstractServiceImpl<Analysys, Long> implements IAnalysysService{
	private Logger log = Logger.getLogger(AnalysysServiceImpl.class);
	
	@Autowired
	private IAnalysysDao analysysDao;

	@Override
	public IAbstractDao<Analysys, Long> getDao() {
		// TODO Auto-generated method stub
		return analysysDao;
	}
	
	public Long countBySearch(String search){
		return analysysDao.countBySearch(search);
	}
	
	public List<Analysys> getBySearch(String search, int start, int num){
		return analysysDao.getBySearch(search, start, num);
	}
	
	public List<Analysys> getByAnalysysType(AnalysysType analysysType){
		return analysysDao.getByAnalysysType(analysysType);
	}
	
	public Analysys getByQuestNumber(int number){
		return analysysDao.getByQuestNumber(number);
	}
}
