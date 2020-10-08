package com.dlabs.acs.init.cap;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.dto.cap.CompetencyLibraryCapDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.entity.cap.CompetencyLibraryCap;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.intf.cap.ICapService;
import com.dlabs.acs.service.intf.cap.ICompetencyLibraryCapService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.excel.cap.ExcelCompetencyLibraryCap;

@Component
public class InitCompetencyLibraryCap {
	@Autowired
	private ICompetencyLibraryCapService competencyLibraryCapService;
	
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	@Autowired
	private ICapService capService;
	
	public void generate()
	{
		if(competencyLibraryCapService.count().intValue() == 0)
		{
			Date now = new Date();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(StringPool.SCHEDULER);
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(StringPool.SCHEDULER);
			commonFields.setLastModifiedDate(now);
			
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/CompetencyLibraryCap.xlsx");
			
			List<CompetencyLibraryCapDto> list = ExcelCompetencyLibraryCap.parse(is);
			
			List<CompetencyLibraryCap> listCompetencyLibraryCap = new ArrayList<CompetencyLibraryCap>();
			CompetencyLibraryCap  competencyLibraryCap =  null;
			CompetencyLibrary competencyLibrary = null;
			Cap cap = null;
			
			for(CompetencyLibraryCapDto competencyLibraryCapDto : list)
			{
				competencyLibraryCap = new CompetencyLibraryCap();
				
				
				competencyLibrary = competencyLibraryService.getByCompetencyName(competencyLibraryCapDto.getCompetencyName());
				cap = capService.getByQuestNumber(competencyLibraryCapDto.getNumber());
				
				competencyLibraryCap.setCompetencyLibrary(competencyLibrary);
				competencyLibraryCap.setCap(cap);
				competencyLibraryCap.setCommonFields(commonFields);
				
				if(competencyLibraryCap.getCompetencyLibrary() != null && competencyLibraryCap.getCap() != null)
				{
					listCompetencyLibraryCap.add(competencyLibraryCap);
				}
			}
			
			competencyLibraryCapService.save(listCompetencyLibraryCap);
		}
	}
}
