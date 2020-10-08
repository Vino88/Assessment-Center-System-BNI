package com.dlabs.acs.dao.intf.participant;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.participant.Batch;


public interface IBatchDao extends IAbstractDao <Batch,Long>{
	
	public Long countBySearch(String search);
	
	public List<Batch> getBySearch(String search, int start, int num);
	
	public List<Batch> getInprogressBySearch(String search, int start, int num);
	
	public Long countInprogressBySearch(String search);
	
	public List<Batch> getInprogressByUserAdminIdBySearch(Long userAdminId, String search, int start, int num);
	
	public Long countInprogressByUserAdminIdBySearch(Long userAdminId, String search);
}
