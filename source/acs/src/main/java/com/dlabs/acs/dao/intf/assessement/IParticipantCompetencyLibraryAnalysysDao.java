package com.dlabs.acs.dao.intf.assessement;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.assessement.ParticipantCompetencyLibraryAnalysys;


public interface IParticipantCompetencyLibraryAnalysysDao extends IAbstractDao <ParticipantCompetencyLibraryAnalysys,Long>{
	
	public Long countBySearch(String search);
	
	public List<ParticipantCompetencyLibraryAnalysys> getBySearch(String search, int start, int num);
	
	public ParticipantCompetencyLibraryAnalysys getByPartIdAndClaId(Long participantId, Long claId);
}
