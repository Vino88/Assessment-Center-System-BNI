package com.dlabs.acs.dao.intf.competency;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.competency.CompetencyLibrary;


public interface ICompetencyLibraryDao extends IAbstractDao <CompetencyLibrary,Long>{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibrary> getBySearch(String search, int start, int num);
	
	public CompetencyLibrary getByCompetencyName(String competencyName);
}
