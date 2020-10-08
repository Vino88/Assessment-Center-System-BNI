package com.dlabs.acs.service.intf.cap;

import java.util.List;

import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.entity.cap.CompetencyLibraryCap;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.IService;

public interface ICompetencyLibraryCapService  extends IService<CompetencyLibraryCap,Long >{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibraryCap> getBySearch(String search, int start, int num);
	
	public List<CompetencyLibrary> getDistinctCompetencyLibrary();
	
	public Cap getCapByCompetencyId(Long competencyLibraryId);
	
	public List<CompetencyLibrary> getCompetenciesByCapId(Long capId);
}
