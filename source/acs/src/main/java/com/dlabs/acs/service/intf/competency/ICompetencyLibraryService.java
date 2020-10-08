package com.dlabs.acs.service.intf.competency;

import java.util.List;

import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.IService;

public interface ICompetencyLibraryService  extends IService<CompetencyLibrary,Long >{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibrary> getBySearch(String search, int start, int num);
	
	public CompetencyLibrary getByCompetencyName(String competencyName);
}
