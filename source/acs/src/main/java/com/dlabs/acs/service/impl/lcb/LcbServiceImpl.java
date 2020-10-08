package com.dlabs.acs.service.impl.lcb;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.lcb.ILcbDao;
import com.dlabs.acs.entity.lcb.Lcb;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.lcb.ILcbService;

@Service
@Transactional(readOnly = true)
public class LcbServiceImpl extends AbstractServiceImpl<Lcb, Long> implements ILcbService{
	private Logger log = Logger.getLogger(LcbServiceImpl.class);
	
	@Autowired
	private ILcbDao lcbDao;

	@Override
	public IAbstractDao<Lcb, Long> getDao() {
		// TODO Auto-generated method stub
		return lcbDao;
	}
	
	public Long countBySearch(String search){
		return lcbDao.countBySearch(search);
	}
	
	public List<Lcb> getBySearch(String search, int start, int num){
		return lcbDao.getBySearch(search, start, num);
	}
	
	public Lcb getByQuestNumber(int number){
		return lcbDao.getByQuestNumber(number);
	}
}
