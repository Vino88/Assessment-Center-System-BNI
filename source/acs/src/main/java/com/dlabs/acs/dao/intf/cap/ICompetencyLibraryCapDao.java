package com.dlabs.acs.dao.intf.cap;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.entity.cap.CompetencyLibraryCap;
import com.dlabs.acs.entity.competency.CompetencyLibrary;


public interface ICompetencyLibraryCapDao extends IAbstractDao <CompetencyLibraryCap,Long>{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibraryCap> getBySearch(String search, int start, int num);
	public List<CompetencyLibrary> getDistinctCompetencyLibrary();
	
	public Cap getCapByCompetencyId(Long competencyLibraryId);
	
	public List<CompetencyLibrary> getCompetenciesByCapId(Long capId);
}
