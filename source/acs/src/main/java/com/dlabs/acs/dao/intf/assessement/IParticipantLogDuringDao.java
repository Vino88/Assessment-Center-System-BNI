package com.dlabs.acs.dao.intf.assessement;

import java.util.List;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;


public interface IParticipantLogDuringDao extends IAbstractParticipantDao <ParticipantLogDuring,Long>{
	
	public Long countBySearch(String search);
	
	public List<ParticipantLogDuring> getBySearch(String search, int start, int num);
	
}
