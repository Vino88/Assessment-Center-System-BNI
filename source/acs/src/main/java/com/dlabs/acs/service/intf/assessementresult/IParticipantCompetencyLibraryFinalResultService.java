package com.dlabs.acs.service.intf.assessementresult;

import java.util.List;
import java.util.Map;

import com.dlabs.acs.dto.assessementresult.report.GroupReportDto;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.intf.IAbstractParticipantService;

public interface IParticipantCompetencyLibraryFinalResultService  extends IAbstractParticipantService<ParticipantCompetencyLibraryFinalResult,Long >{
	
	public Long countBySearch(String search);
	
	public List<ParticipantCompetencyLibraryFinalResult> getBySearch(String search, int start, int num);
	
	public List<ParticipantCompetencyLibraryFinalResult> getByParticipantIdOrderByCompetencyOrder(Long participantId);
	
	public ParticipantCompetencyLibraryFinalResult getByParticipantIdAndCompetencyLibraryId(Long participantId, Long competencyLibraryId);
	
	public List<GroupReportDto> getReportBatch(List<CompetencyLibrary> competencyLibraries, Long batchId);
	
	
	public List<Map<String,Object>> getReportGroup(Long batchId);
}
