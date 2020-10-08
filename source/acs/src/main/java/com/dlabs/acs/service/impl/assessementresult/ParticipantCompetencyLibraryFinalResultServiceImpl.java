package com.dlabs.acs.service.impl.assessementresult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.intf.IAbstractParticipantDao;
import com.dlabs.acs.dao.intf.assessementresult.IParticipantCompetencyLibraryFinalResultDao;
import com.dlabs.acs.dto.assessementresult.report.GroupReportDto;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.impl.AbstractParticipantServiceImpl;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;

@Service
@Transactional(readOnly = true)
public class ParticipantCompetencyLibraryFinalResultServiceImpl extends AbstractParticipantServiceImpl<ParticipantCompetencyLibraryFinalResult, Long> implements IParticipantCompetencyLibraryFinalResultService{
	private Logger log = Logger.getLogger(ParticipantCompetencyLibraryFinalResultServiceImpl.class);
	
	@Autowired
	private IParticipantCompetencyLibraryFinalResultDao participantcompetencylibraryfinalresultDao;

	@Override
	public IAbstractParticipantDao<ParticipantCompetencyLibraryFinalResult, Long> getDao() {
		// TODO Auto-generated method stub
		return participantcompetencylibraryfinalresultDao;
	}
	
	public Long countBySearch(String search){
		return participantcompetencylibraryfinalresultDao.countBySearch(search);
	}
	
	public List<ParticipantCompetencyLibraryFinalResult> getBySearch(String search, int start, int num){
		return participantcompetencylibraryfinalresultDao.getBySearch(search, start, num);
	}
	
	public List<ParticipantCompetencyLibraryFinalResult> getByParticipantIdOrderByCompetencyOrder(Long participantId){
		return participantcompetencylibraryfinalresultDao.getByParticipantIdOrderByCompetencyOrder(participantId);
	}
	
	public ParticipantCompetencyLibraryFinalResult getByParticipantIdAndCompetencyLibraryId(Long participantId, Long competencyLibraryId)
	{
		return participantcompetencylibraryfinalresultDao.getByParticipantIdAndCompetencyLibraryId(participantId, competencyLibraryId);
	}
	
	public List<GroupReportDto> getReportBatch(List<CompetencyLibrary> competencyLibraries, Long batchId)
	{
		List<GroupReportDto> list = new ArrayList<GroupReportDto>();
		GroupReportDto groupReportDto = null;
		
		List<Map<String, Object>> below = participantcompetencylibraryfinalresultDao.getReportBatchBelow(batchId);
		List<Map<String, Object>> meet = participantcompetencylibraryfinalresultDao.getReportBatchMeet(batchId);
		List<Map<String, Object>> above = participantcompetencylibraryfinalresultDao.getReportBatchAbove(batchId);
		
		for(CompetencyLibrary c : competencyLibraries)
		{
			groupReportDto = new GroupReportDto();
			groupReportDto.setCompetencyLibrary(c);
			
			groupReportDto.setBelow(getRating(below, c.getId()));
			groupReportDto.setMeet(getRating(meet, c.getId()));
			groupReportDto.setAbove(getRating(above, c.getId()));
			
			
			list.add(groupReportDto);
		}
		
		return list;
	}
	
	private int getRating(List<Map<String, Object>> list, Long id)
	{
		int result = 0;
		try
		{
			for(Map<String, Object> map : list )
			{
				if(id.equals(map.get("competencyLibraryId")))
				{
					result = ((Long) map.get("countComp")).intValue();
					return result;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public List<Map<String,Object>> getReportGroup(Long batchId){
		return participantcompetencylibraryfinalresultDao.getReportGroup(batchId);
	}
}
