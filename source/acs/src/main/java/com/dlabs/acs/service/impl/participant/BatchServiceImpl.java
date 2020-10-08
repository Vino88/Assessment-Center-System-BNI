package com.dlabs.acs.service.impl.participant;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.participant.IBatchDao;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.participant.IBatchService;

@Service
@Transactional(readOnly = true)
public class BatchServiceImpl extends AbstractServiceImpl<Batch, Long> implements IBatchService{
	private Logger log = Logger.getLogger(BatchServiceImpl.class);
	
	@Autowired
	private IBatchDao batchDao;

	@Override
	public IAbstractDao<Batch, Long> getDao() {
		// TODO Auto-generated method stub
		return batchDao;
	}
	
	public Long countBySearch(String search){
		return batchDao.countBySearch(search);
	}
	
	public List<Batch> getBySearch(String search, int start, int num){
		return batchDao.getBySearch(search, start, num);
	}

	@Override
	public List<Batch> getInprogressBySearch(String search, int start, int num) {
		// TODO Auto-generated method stub
		return batchDao.getInprogressBySearch(search, start, num);
	}

	@Override
	public Long countInprogressBySearch(String search) {
		// TODO Auto-generated method stub
		return batchDao.countInprogressBySearch(search);
	}
	
	public List<Batch> getInprogressByUserAdminIdBySearch(Long userAdminId, String search, int start, int num){
		return batchDao.getInprogressByUserAdminIdBySearch(userAdminId, search, start, num);
	}
	
	public Long countInprogressByUserAdminIdBySearch(Long userAdminId, String search){
		return batchDao.countInprogressByUserAdminIdBySearch(userAdminId, search);
	}
}
