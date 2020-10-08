package com.dlabs.acs.service.intf.lcb;

import java.util.List;

import com.dlabs.acs.entity.lcb.CompetencyLibraryLcb;
import com.dlabs.acs.service.IService;

public interface ICompetencyLibraryLcbService  extends IService<CompetencyLibraryLcb,Long >{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibraryLcb> getBySearch(String search, int start, int num);
	
	public CompetencyLibraryLcb getByCompetencyIdAndLcbId(Long competencyId, Long lcbId);
}
