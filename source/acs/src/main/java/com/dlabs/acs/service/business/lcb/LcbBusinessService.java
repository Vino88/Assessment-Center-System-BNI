package com.dlabs.acs.service.business.lcb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.intf.assessement.IParticipantLcbDao;
import com.dlabs.acs.dao.intf.assessementresult.IParticipantCompetencyLibraryFinalResultDao;
import com.dlabs.acs.dao.intf.competency.ICompetencyLibraryDao;
import com.dlabs.acs.dao.intf.lcb.ICompetencyLibraryLcbDao;
import com.dlabs.acs.dao.intf.lcb.ILcbDao;
import com.dlabs.acs.dto.assessement.ParticipantLcbDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantLcb;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.assessementresult.ParticipantLcbCompetencyLibraryLcb;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.lcb.CompetencyLibraryLcb;
import com.dlabs.acs.entity.lcb.Lcb;
import com.dlabs.acs.entity.lcb.enumeration.LcbCategory;
import com.dlabs.acs.service.intf.assessementresult.IParticipantLcbCompetencyLibraryLcbService;
import com.dlabs.acs.util.NumberUtil;
import com.dlabs.acs.util.lcb.LcbUtil;

@Service
@Transactional(readOnly = true)
public class LcbBusinessService {
	private Logger logger = Logger.getLogger(LcbBusinessService.class);
	
	@Autowired
	private ILcbDao lcbDao;
	@Autowired
	private IParticipantLcbDao participantLcbDao;
	@Autowired
	private ICompetencyLibraryLcbDao competencyLibraryLcbDao;
	@Autowired
	private ICompetencyLibraryDao competencyLibraryDao;
	@Autowired
	private IParticipantCompetencyLibraryFinalResultDao participantCompetencyLibraryFinalResultDao;
	@Autowired
	private IParticipantLcbCompetencyLibraryLcbService participantLcbCompetencyLibraryLcbService;
	
	@Transactional(readOnly = false)
	public void evaluate(List<ParticipantLcbDto> listDto, Participant participant)
	{
		Date now = new Date();
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(participant.getNik());
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(participant.getNik());
		commonFields.setLastModifiedDate(now);
		
		CompetencyLibraryLcb competencyLibraryLcb = null;
		
		ParticipantLcbCompetencyLibraryLcb participantLcbCompetencyLibraryLcb = null;
		ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult = null;
		
		int numOfCompetencyLibraryLcb1 = 0;
		int numOfCompetencyLibraryLcb2 = 0;
		int numOfCompetencyLibraryLcb3 = 0;
		int sumWeightPerCompetency1 = 0;
		int sumWeightPerCompetency2 = 0;
		int sumWeightPerCompetency3 = 0;
		
		List<ParticipantLcb> listParticipantLcb = dtoToEntity(listDto, participant); // dto to entity

		List<CompetencyLibrary> listCompetency = competencyLibraryDao.getAllActive();
		
		logger.debug("listDto.size = "+listDto.size());
		for(CompetencyLibrary competencyLibrary : listCompetency)
		{
			numOfCompetencyLibraryLcb1 = 0;
			numOfCompetencyLibraryLcb2 = 0;
			numOfCompetencyLibraryLcb3 = 0;
			sumWeightPerCompetency1 = 0;
			sumWeightPerCompetency2 = 0;
			sumWeightPerCompetency3 = 0;
			
			for(ParticipantLcb participantLcb : listParticipantLcb)
			{
				competencyLibraryLcb = competencyLibraryLcbDao.getByCompetencyIdAndLcbId(competencyLibrary.getId(), participantLcb.getLcb().getId());
				
				if(competencyLibraryLcb != null)
				{
					participantLcbCompetencyLibraryLcb = new ParticipantLcbCompetencyLibraryLcb();
					participantLcbCompetencyLibraryLcb.setCommonFields(commonFields);
					participantLcbCompetencyLibraryLcb.setCompetencyLibraryLcb(competencyLibraryLcb);
					participantLcbCompetencyLibraryLcb.setParticipantLcb(participantLcb);
					
					
					if(LcbCategory.LCB1.equals(participantLcb.getLcb().getLbCategory()))
					{
						numOfCompetencyLibraryLcb1 = numOfCompetencyLibraryLcb1 + 1;
						sumWeightPerCompetency1 = sumWeightPerCompetency1 + participantLcb.getLcbAnswerWeight();
					}else if(LcbCategory.LCB2.equals(participantLcb.getLcb().getLbCategory()))
					{
						numOfCompetencyLibraryLcb2 = numOfCompetencyLibraryLcb2 + 1;
						sumWeightPerCompetency2 = sumWeightPerCompetency2 + participantLcb.getLcbAnswerWeight();
					}else if(LcbCategory.LCB3.equals(participantLcb.getLcb().getLbCategory()))
					{
						numOfCompetencyLibraryLcb3 = numOfCompetencyLibraryLcb3 + 1;
						sumWeightPerCompetency3 = sumWeightPerCompetency3 + participantLcb.getLcbAnswerWeight();
					}
					
				}
					
			}
			
			
			if(numOfCompetencyLibraryLcb1 > 0 || numOfCompetencyLibraryLcb2 > 0 || numOfCompetencyLibraryLcb3 > 0)
			{
				participantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResultDao.getByParticipantIdAndCompetencyLibraryId(participant.getId(), competencyLibrary.getId());
				
				if(participantCompetencyLibraryFinalResult == null)
				{
					participantCompetencyLibraryFinalResult = new ParticipantCompetencyLibraryFinalResult();
					participantCompetencyLibraryFinalResult.setCommonFields(commonFields);
				}
				
				participantCompetencyLibraryFinalResult.getCommonFields().setLastModifiedBy(participant.getNik());
				participantCompetencyLibraryFinalResult.getCommonFields().setLastModifiedDate(now);
				
				int lcbRatingInt = 0;
				if(numOfCompetencyLibraryLcb1 > 0)
				{
					lcbRatingInt =sumWeightPerCompetency1;
				}else if(numOfCompetencyLibraryLcb2 > 0)
				{
					double lcbRating = (sumWeightPerCompetency2*1.0) / (numOfCompetencyLibraryLcb2*1.0);
					lcbRatingInt = NumberUtil.round(lcbRating);
				}else if(numOfCompetencyLibraryLcb3 > 0)
				{
					double lcbRating = (sumWeightPerCompetency3*1.0) / (numOfCompetencyLibraryLcb3*1.0);
					lcbRatingInt = NumberUtil.round(lcbRating);
				}
				
				if(lcbRatingInt == 0 )
				{
					lcbRatingInt = 1;
				}
				
				participantCompetencyLibraryFinalResult.setCompetencyLibrary(competencyLibrary);
				participantCompetencyLibraryFinalResult.setLcbRating(lcbRatingInt);
				participantCompetencyLibraryFinalResult.setParticipant(participant);
				
				participantCompetencyLibraryFinalResultDao.saveOrUpdate(participantCompetencyLibraryFinalResult);
			}
		}
		
		
		
		logger.debug("END");
	}
	@Transactional(readOnly = false)
	public List<ParticipantLcb> dtoToEntity(List<ParticipantLcbDto> listDto, Participant participant)
	{
		logger.debug("dtoToEntity:BEGIN");
		Date now = new Date();
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(participant.getNik());
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(participant.getNik());
		commonFields.setLastModifiedDate(now);
		
		List<ParticipantLcb> listParticipantLcb = participantLcbDao.getByParticipantId(participant.getId());
		Map<Integer, ParticipantLcb> mapParticipantLcb = new HashMap<Integer, ParticipantLcb>();
		
		for(ParticipantLcb plcb : listParticipantLcb)
		{
			mapParticipantLcb.put(plcb.getLcb().getNumber(), plcb);
		}
		
		Lcb lcb = null;
		ParticipantLcb participantLcb = null;
		
		Integer lcbAnswerWeight=null;
		
		boolean isnew = false;
		for(ParticipantLcbDto participantLcbDto : listDto)
		{
			lcb = lcbDao.getByQuestNumber(participantLcbDto.getQuestionNumber());
			if(lcb != null)
			{
				participantLcb = mapParticipantLcb.get(participantLcbDto.getQuestionNumber());
				if(participantLcb == null)
				{
					participantLcb = new ParticipantLcb();
					isnew = true;
				}
				else
				{
					isnew = false;
				}
				
				participantLcb.setLcb(lcb);
				participantLcb.setParticipant(participant);
				participantLcb.setLcbAnswer(participantLcbDto.getLcbAnswer());
				lcbAnswerWeight= LcbUtil.getWeightByAnswer(lcb, participantLcbDto.getLcbAnswer());
				participantLcb.setLcbAnswerWeight(lcbAnswerWeight);
				participantLcb.setCommonFields(commonFields);
				if(isnew)
				{
					listParticipantLcb.add(participantLcb);
				}
				
				participantLcbDao.saveOrUpdate(participantLcb);
			}
		}
		
		logger.debug("dtoToEntity:END");
		return listParticipantLcb;
	}
	
	
}
