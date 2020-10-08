package com.dlabs.acs.service.intf.analysys;

import java.util.List;

import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.CompetencyLibraryAnalysys;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.IService;

public interface ICompetencyLibraryAnalysysService  extends IService<CompetencyLibraryAnalysys,Long >{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibraryAnalysys> getBySearch(String search, int start, int num);
	
	public List<CompetencyLibrary> getDistinctCompetencyLibrary();
	public List<Analysys> getAnalysisByCompetencyId(Long competencyLibraryId);
}
