package com.dlabs.acs.dao.intf.assessement;

import java.util.List;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.entity.assessement.ParticipantLcb;


public interface IParticipantLcbDao extends IAbstractParticipantDao <ParticipantLcb,Long>{
	
	public Long countBySearch(String search);
	
	public List<ParticipantLcb> getBySearch(String search, int start, int num);
	
}
