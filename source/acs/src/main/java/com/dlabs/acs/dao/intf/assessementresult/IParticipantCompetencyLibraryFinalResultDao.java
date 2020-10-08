package com.dlabs.acs.dao.intf.assessementresult;

import java.util.List;
import java.util.Map;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;


public interface IParticipantCompetencyLibraryFinalResultDao extends IAbstractParticipantDao <ParticipantCompetencyLibraryFinalResult,Long>{
	
	public Long countBySearch(String search);
	
	public List<ParticipantCompetencyLibraryFinalResult> getBySearch(String search, int start, int num);
	
	public List<ParticipantCompetencyLibraryFinalResult> getByParticipantIdOrderByCompetencyOrder(Long participantId);
	
	public ParticipantCompetencyLibraryFinalResult getByParticipantIdAndCompetencyLibraryId(Long participantId, Long competencyLibraryId);
	
	public List<Map<String,Object>> getReportBatchMeet(Long batchId);
	public List<Map<String,Object>> getReportBatchAbove(Long batchId);
	public List<Map<String,Object>> getReportBatchBelow(Long batchId);
	
	public List<Map<String,Object>> getReportGroup(Long batchId);
}
