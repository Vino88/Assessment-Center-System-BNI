package com.dlabs.acs.init.inbasket;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.dto.inbasket.InbasketInboxInbasketQuestionDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.inbasket.InbasketInbox;
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.inbasket.InbasketQuestion;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxInbasketQuestionService;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxService;
import com.dlabs.acs.service.intf.inbasket.IInbasketQuestionService;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.excel.inbasket.ExcelInbasketInboxInbasketQuestion;

@Component
public class InitInbasketInboxInbasketQuestion {
	@Autowired
	private IInbasketInboxService inbasketInboxService;
	@Autowired
	private IInbasketQuestionService inbasketQuestionService;
	@Autowired
	private IInbasketInboxInbasketQuestionService inbasketInboxInbasketQuestionService;
	
	public void generate()
	{
		if(inbasketInboxInbasketQuestionService.count().intValue() == 0)
		{
			Date now = new Date();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(StringPool.SCHEDULER);
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(StringPool.SCHEDULER);
			commonFields.setLastModifiedDate(now);
			
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/InbasketInboxInbasketQuestion.xlsx");
			
			List<InbasketInboxInbasketQuestionDto> listDto = ExcelInbasketInboxInbasketQuestion.parse(is);
			List<InbasketInboxInbasketQuestion> list = new ArrayList<InbasketInboxInbasketQuestion>();
			
			InbasketInboxInbasketQuestion inbasketInboxInbasketQuestion = null;
			InbasketInbox inbasketInbox= null;
			InbasketQuestion inbasketQuestion = null;
			
			for(InbasketInboxInbasketQuestionDto inbasketInboxInbasketQuestionDto : listDto)
			{
				inbasketInboxInbasketQuestion = new InbasketInboxInbasketQuestion();
				
				inbasketInbox= inbasketInboxService.getByQuestNumber(inbasketInboxInbasketQuestionDto.getInbasketInboxQuestionNumber());
				inbasketQuestion = inbasketQuestionService.getByQuestNumber(inbasketInboxInbasketQuestionDto.getInbasketQuestionQuestionNumber());
				
				inbasketInboxInbasketQuestion.setCommonFields(commonFields);
				inbasketInboxInbasketQuestion.setInbasketInbox(inbasketInbox);
				inbasketInboxInbasketQuestion.setInbasketQuestion(inbasketQuestion);
				
				if(inbasketInboxInbasketQuestion.getInbasketInbox() != null && inbasketInboxInbasketQuestion.getInbasketQuestion() != null)
				{
					list.add(inbasketInboxInbasketQuestion);
				}
			}
			inbasketInboxInbasketQuestionService.save(list);
		}
	}
}
