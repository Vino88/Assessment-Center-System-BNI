package com.dlabs.acs.init.competency;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.util.excel.competency.ExcelCompetencyLibrary;

@Component
public class InitCompetencyLibrary {
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	public void generte()
	{
		if(competencyLibraryService.count().intValue() == 0)
		{
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/CompetencyLibrary.xlsx");
//			InputStream is =getClass().getClassLoader().getResourceAsStream("init/ScbCompetencyLibrary.xlsx");
			
			List<CompetencyLibrary> list = ExcelCompetencyLibrary.parse(is);
			
			competencyLibraryService.save(list);
		}
	}
}
