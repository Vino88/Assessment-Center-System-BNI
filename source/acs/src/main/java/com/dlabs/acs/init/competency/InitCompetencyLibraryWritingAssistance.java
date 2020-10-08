package com.dlabs.acs.init.competency;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.dto.competency.CompetencyLibraryWritingAssistanceDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.CompetencyLibraryWritingAssistance;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryWritingAssistanceService;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.excel.competency.ExcelCompetencyLibraryWritingAssitance;

@Component
public class InitCompetencyLibraryWritingAssistance {
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	@Autowired
	private ICompetencyLibraryWritingAssistanceService competencyLibraryWritingAssistanceService;
	
	public void generate()
	{
		if(competencyLibraryWritingAssistanceService.count().intValue() == 0)
		{
			Date now = new Date();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(StringPool.SCHEDULER);
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(StringPool.SCHEDULER);
			commonFields.setLastModifiedDate(now);
			
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/CompetencyLibraryWritingAssistance.xlsx");
			
			List<CompetencyLibraryWritingAssistanceDto> list = ExcelCompetencyLibraryWritingAssitance.parse(is);
			
			List<CompetencyLibraryWritingAssistance> listCompetencyLibraryWritingAssistance = new ArrayList<CompetencyLibraryWritingAssistance>();
			CompetencyLibraryWritingAssistance competencyLibraryWritingAssistance =  null;
			CompetencyLibrary competencyLibrary = null;
			
			
			for(CompetencyLibraryWritingAssistanceDto competencyLibraryWritingAssistanceDto : list)
			{
				competencyLibraryWritingAssistance = new CompetencyLibraryWritingAssistance();
				
				
				competencyLibrary = competencyLibraryService.getByCompetencyName(competencyLibraryWritingAssistanceDto.getCompetencyName());
				
				competencyLibraryWritingAssistance.setCompetencyLibrary(competencyLibrary);
				competencyLibraryWritingAssistance.setCommonFields(commonFields);
				
				competencyLibraryWritingAssistance.setLevel(competencyLibraryWritingAssistanceDto.getLevel());
				competencyLibraryWritingAssistance.setDescription(competencyLibraryWritingAssistanceDto.getDescription());
				
				if(competencyLibraryWritingAssistance.getCompetencyLibrary() != null && competencyLibraryWritingAssistance.getLevel() > 0)
				{
					listCompetencyLibraryWritingAssistance.add(competencyLibraryWritingAssistance);
				}
			}
			
			competencyLibraryWritingAssistanceService.save(listCompetencyLibraryWritingAssistance);
		}
	}
	
}
