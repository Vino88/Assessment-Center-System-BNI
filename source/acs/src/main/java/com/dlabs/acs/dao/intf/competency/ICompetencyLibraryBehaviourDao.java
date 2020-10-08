package com.dlabs.acs.dao.intf.competency;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;


public interface ICompetencyLibraryBehaviourDao extends IAbstractDao <CompetencyLibraryBehaviour,Long>{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibraryBehaviour> getBySearch(String search, int start, int num);
	
	public List<CompetencyLibraryBehaviour> getByCompetencyLibraryId(Long competencyLibraryId);
	
}
