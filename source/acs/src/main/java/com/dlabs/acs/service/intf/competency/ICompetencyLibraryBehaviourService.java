package com.dlabs.acs.service.intf.competency;

import java.util.List;

import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;
import com.dlabs.acs.service.IService;

public interface ICompetencyLibraryBehaviourService  extends IService<CompetencyLibraryBehaviour,Long >{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibraryBehaviour> getBySearch(String search, int start, int num);
	
	public List<CompetencyLibraryBehaviour> getByCompetencyLibraryId(Long competencyLibraryId);
}
