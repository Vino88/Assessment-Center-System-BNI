package com.dlabs.acs.service.impl.cap;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.cap.ICapDao;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.cap.ICapService;

@Service
@Transactional(readOnly = true)
public class CapServiceImpl extends AbstractServiceImpl<Cap, Long> implements ICapService{
	private Logger log = Logger.getLogger(CapServiceImpl.class);
	
	@Autowired
	private ICapDao capDao;

	@Override
	public IAbstractDao<Cap, Long> getDao() {
		// TODO Auto-generated method stub
		return capDao;
	}
	
	public Long countBySearch(String search){
		return capDao.countBySearch(search);
	}
	
	public List<Cap> getBySearch(String search, int start, int num){
		return capDao.getBySearch(search, start, num);
	}
	
	public Cap getByQuestNumber(int number){
		return capDao.getByQuestNumber(number);
	}
}
