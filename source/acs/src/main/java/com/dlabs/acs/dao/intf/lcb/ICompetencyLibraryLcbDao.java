package com.dlabs.acs.dao.intf.lcb;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.lcb.CompetencyLibraryLcb;


public interface ICompetencyLibraryLcbDao extends IAbstractDao <CompetencyLibraryLcb,Long>{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibraryLcb> getBySearch(String search, int start, int num);
	
	public CompetencyLibraryLcb getByCompetencyIdAndLcbId(Long competencyId, Long lcbId);
}
