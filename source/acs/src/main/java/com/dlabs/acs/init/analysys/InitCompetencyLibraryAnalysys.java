package com.dlabs.acs.init.analysys;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.dto.analysys.CompetencyLibraryAnalysysDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.CompetencyLibraryAnalysys;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.intf.analysys.IAnalysysService;
import com.dlabs.acs.service.intf.analysys.ICompetencyLibraryAnalysysService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.excel.analysys.ExcelCompetencyLibraryAnalysys;

@Component
public class InitCompetencyLibraryAnalysys {
	@Autowired
	private ICompetencyLibraryAnalysysService competencyLibraryAnalysysService;
	
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	@Autowired
	private IAnalysysService analysysService;
	
	
	public void generate()
	{
		if(competencyLibraryAnalysysService.count().intValue() == 0)
		{
			
			
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/CompetencyLibraryAnalysys.xlsx");
			
			List<CompetencyLibraryAnalysysDto> list = ExcelCompetencyLibraryAnalysys.parse(is);
			
			List<CompetencyLibraryAnalysys> listCompetencyLibraryAnalysys = new ArrayList<CompetencyLibraryAnalysys>();
			CompetencyLibraryAnalysys  competencyLibraryAnalysys =  null;
			CompetencyLibrary competencyLibrary = null;
			Analysys analysys = null;
			
			Date now = new Date();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(StringPool.SCHEDULER);
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(StringPool.SCHEDULER);
			commonFields.setLastModifiedDate(now);
			
			for(CompetencyLibraryAnalysysDto competencyLibraryAnalysysDto : list)
			{
				competencyLibraryAnalysys = new CompetencyLibraryAnalysys();
				
				
				competencyLibrary = competencyLibraryService.getByCompetencyName(competencyLibraryAnalysysDto.getCompetencyName());
				analysys = analysysService.getByQuestNumber(competencyLibraryAnalysysDto.getNumber());
				
				competencyLibraryAnalysys.setCompetencyLibrary(competencyLibrary);
				competencyLibraryAnalysys.setAnalysys(analysys);
				competencyLibraryAnalysys.setCommonFields(commonFields);
				
				if(competencyLibraryAnalysys.getCompetencyLibrary() != null && competencyLibraryAnalysys.getAnalysys() != null)
				{
					listCompetencyLibraryAnalysys.add(competencyLibraryAnalysys);
				}
			}
			
			competencyLibraryAnalysysService.save(listCompetencyLibraryAnalysys);
		}
	}
}
