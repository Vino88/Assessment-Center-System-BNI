package com.dlabs.acs.service.intf.inbasket;

import java.util.List;

import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.inbasket.CompetencyLibraryInbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;
import com.dlabs.acs.service.IService;

public interface ICompetencyLibraryInbasketInboxInbasketQuestionService  extends IService<CompetencyLibraryInbasketInboxInbasketQuestion,Long >{
	
	public Long countBySearch(String search);
	
	public List<CompetencyLibraryInbasketInboxInbasketQuestion> getBySearch(String search, int start, int num);
	
	public List<CompetencyLibrary> getDistinctCompetencyLibrary();
	
	public List<InbasketInboxInbasketQuestion> getIiiqByCompetencyId(Long competencyLibraryId);
}
