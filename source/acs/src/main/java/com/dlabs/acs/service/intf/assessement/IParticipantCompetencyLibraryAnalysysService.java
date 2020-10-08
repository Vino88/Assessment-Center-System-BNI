package com.dlabs.acs.service.intf.assessement;

import java.util.List;

import com.dlabs.acs.entity.assessement.ParticipantCompetencyLibraryAnalysys;
import com.dlabs.acs.service.IService;

public interface IParticipantCompetencyLibraryAnalysysService  extends IService<ParticipantCompetencyLibraryAnalysys,Long >{
	
	public Long countBySearch(String search);
	
	public List<ParticipantCompetencyLibraryAnalysys> getBySearch(String search, int start, int num);
	
	public ParticipantCompetencyLibraryAnalysys getByPartIdAndClaId(Long participantId, Long claId);
}
