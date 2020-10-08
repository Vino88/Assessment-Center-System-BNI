package com.dlabs.acs.service.intf.assessementresult;

import java.util.List;

import com.dlabs.acs.entity.assessementresult.ParticipantLcbCompetencyLibraryLcb;
import com.dlabs.acs.service.IService;

public interface IParticipantLcbCompetencyLibraryLcbService  extends IService<ParticipantLcbCompetencyLibraryLcb,Long >{
	
	public Long countBySearch(String search);
	
	public List<ParticipantLcbCompetencyLibraryLcb> getBySearch(String search, int start, int num);
}
