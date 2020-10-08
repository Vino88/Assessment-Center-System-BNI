package com.dlabs.acs.dao.intf.competency;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.competency.CompetencyLibraryWritingAssistance;


public interface ICompetencyLibraryWritingAssistanceDao extends IAbstractDao <CompetencyLibraryWritingAssistance,Long>{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibraryWritingAssistance> getBySearch(String search, int start, int num);
	
	public Long countByCompetencyLibraryId(Long competencyLibraryId);
	public Long countByLevel(int level) ;
	public Long countByCompetencyLibraryIdAndLevel(Long competencyLibraryId, int level);
	
	public List<CompetencyLibraryWritingAssistance> getByCompetencyLibraryId(Long competencyLibraryId, int start, int num);
	
	public List<CompetencyLibraryWritingAssistance> getByLevel(int level, int start, int num);
	
	public List<CompetencyLibraryWritingAssistance> getByCompetencyLibraryIdAndLevel(Long competencyLibraryId, int level, int start, int num);
}
