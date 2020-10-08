package com.dlabs.acs.service.intf.assessement;

import java.util.List;
import java.util.Map;

import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.service.IService;

public interface IParticipantService  extends IService<Participant,Long >{
	
	public Long countBySearch(String search);
	
	public List<Participant> getBySearch(String search, int start, int num);
	
	public Participant login(String email, String password);
	
	public Profile getProfileByParticipantId(Long id);
	
	public List<Map<String,Object>> getByBatchBySearch(Long batchId,String search, int start, int num);
	public Long countByBatchBySearch(Long batchId,String search);
	
	public List<Map<String,Object>> getAllBySearch(String search, int start, int num);
	public Long countAllBySearch(String search);
	
	public Participant getByNikAndActive(String nik);
	
	public Batch getBatchByParticipantId(Long id);
	
	public List<Participant> getParticipantFinish(String search, int start, int num);
	public Long countParticipantFinish(String search);
	
	public Long countAssessementFinishByBatchId(Long batchId);
}
