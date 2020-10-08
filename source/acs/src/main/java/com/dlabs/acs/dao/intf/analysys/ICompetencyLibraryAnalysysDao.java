package com.dlabs.acs.dao.intf.analysys;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.CompetencyLibraryAnalysys;
import com.dlabs.acs.entity.competency.CompetencyLibrary;


public interface ICompetencyLibraryAnalysysDao extends IAbstractDao <CompetencyLibraryAnalysys,Long>{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibraryAnalysys> getBySearch(String search, int start, int num);
	
	public List<CompetencyLibrary> getDistinctCompetencyLibrary();
	public List<Analysys> getAnalysisByCompetencyId(Long competencyLibraryId);
}
