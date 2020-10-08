package com.dlabs.acs.init.inbasket;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.entity.inbasket.InbasketQuestion;
import com.dlabs.acs.service.intf.inbasket.IInbasketQuestionService;
import com.dlabs.acs.util.excel.inbasket.ExcelInbasketQuestion;

@Component
public class InitInbasketQuestion {
	@Autowired
	private IInbasketQuestionService inbasketQuestionService;
	
	public void generate()
	{
		if(inbasketQuestionService.count().intValue() == 0)
		{
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/InbasketQuestion.xlsx");
			
			List<InbasketQuestion> list = ExcelInbasketQuestion.parse(is);
			
			inbasketQuestionService.save(list);
		}
	}
}
