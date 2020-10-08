package com.dlabs.acs.init.inbasket;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.dto.inbasket.InbasketInboxInbasketQuestionDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.inbasket.CompetencyLibraryInbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.inbasket.ICompetencyLibraryInbasketInboxInbasketQuestionService;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxInbasketQuestionService;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.excel.inbasket.ExcelCompetencyLibraryInbasketInboxInbasketQuestion;

@Component
public class InitCompetencyLibraryInbasketInboxInbasketQuestion {
	@Autowired
	private IInbasketInboxInbasketQuestionService inbasketInboxInbasketQuestionService;
	@Autowired
	private ICompetencyLibraryInbasketInboxInbasketQuestionService competencyLibraryInbasketInboxInbasketQuestionService;
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	public void generate()
	{
		if(competencyLibraryInbasketInboxInbasketQuestionService.count().intValue() == 0)
		{
			Date now = new Date();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(StringPool.SCHEDULER);
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(StringPool.SCHEDULER);
			commonFields.setLastModifiedDate(now);
			
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/CompetencyLibraryInbasketInboxInbasketQuestion.xlsx");
			
			List<InbasketInboxInbasketQuestionDto> listDto = ExcelCompetencyLibraryInbasketInboxInbasketQuestion.parse(is);
			List<CompetencyLibraryInbasketInboxInbasketQuestion> list = new ArrayList<CompetencyLibraryInbasketInboxInbasketQuestion>();
			
			CompetencyLibraryInbasketInboxInbasketQuestion competencyLibraryInbasketInboxInbasketQuestion = null;
			CompetencyLibrary competencyLibrary = null;
			InbasketInboxInbasketQuestion inbasketInboxInbasketQuestion = null;
			
			for(InbasketInboxInbasketQuestionDto inbasketInboxInbasketQuestionDto : listDto)
			{
				competencyLibraryInbasketInboxInbasketQuestion = new CompetencyLibraryInbasketInboxInbasketQuestion();
				
				inbasketInboxInbasketQuestion= inbasketInboxInbasketQuestionService.getByInboxQnumAndQuestionQnum(inbasketInboxInbasketQuestionDto.getInbasketInboxQuestionNumber(), inbasketInboxInbasketQuestionDto.getInbasketQuestionQuestionNumber());
				competencyLibrary = competencyLibraryService.getByCompetencyName(inbasketInboxInbasketQuestionDto.getCompetencyName());
				
				competencyLibraryInbasketInboxInbasketQuestion.setCommonFields(commonFields);
				competencyLibraryInbasketInboxInbasketQuestion.setCompetencyLibrary(competencyLibrary);
				competencyLibraryInbasketInboxInbasketQuestion.setInbasketInboxInbasketQuestion(inbasketInboxInbasketQuestion);
				
				if(competencyLibraryInbasketInboxInbasketQuestion.getCompetencyLibrary() != null && competencyLibraryInbasketInboxInbasketQuestion.getInbasketInboxInbasketQuestion() != null)
				{
					list.add(competencyLibraryInbasketInboxInbasketQuestion);
				}
			}
			competencyLibraryInbasketInboxInbasketQuestionService.save(list);
		}
	}
}
