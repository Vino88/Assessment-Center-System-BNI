package com.dlabs.acs.dao.intf.assessementresult;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.assessementresult.ParticipantLcbCompetencyLibraryLcb;


public interface IParticipantLcbCompetencyLibraryLcbDao extends IAbstractDao <ParticipantLcbCompetencyLibraryLcb,Long>{
	
	public Long countBySearch(String search);
	
	public List<ParticipantLcbCompetencyLibraryLcb> getBySearch(String search, int start, int num);
	
}
