package com.dlabs.acs.dao.intf.assessement;

import java.util.List;
import java.util.Map;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.participant.Batch;


public interface IParticipantDao extends IAbstractDao <Participant,Long>{
	
	public Long countBySearch(String search);
	
	public List<Participant> getBySearch(String search, int start, int num);
	
	public Participant login(String email, String password);
	
	public Profile getProfileByParticipantId(Long id);
	
	public List<Map<String,Object>> getByBatchBySearch(Long batchId,String search, int start, int num);
	public Long countByBatchBySearch(Long batchId, String search);
	
	public List<Map<String,Object>> getAllBySearch(String search, int start, int num);
	public Long countAllBySearch(String search);
	
	public Participant getByNikAndActive(String nik);
	
	public Batch getBatchByParticipantId(Long id);
	
	public Long countAssessementFinishByBatchId(Long batchId);
	
	public List<Participant> getParticipantFinish(String search, int start, int num);
	public Long countParticipantFinish(String search);
}
