package com.dlabs.acs.service.intf.participant;

import java.util.List;

import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.service.IService;

public interface IBatchService  extends IService<Batch,Long >{
	
	public Long countBySearch(String search);
	
	public List<Batch> getBySearch(String search, int start, int num);
	
	public List<Batch> getInprogressBySearch(String search, int start, int num);
	
	public Long countInprogressBySearch(String search);
	
	public List<Batch> getInprogressByUserAdminIdBySearch(Long userAdminId, String search, int start, int num);
	
	public Long countInprogressByUserAdminIdBySearch(Long userAdminId, String search);
}
