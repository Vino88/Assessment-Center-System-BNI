package com.dlabs.acs.init.competency;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.dto.competency.CompetencyLibraryBehaviourDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;
import com.dlabs.acs.entity.lcb.Lcb;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryBehaviourService;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.excel.competency.ExcelCompetencyLibraryBehaviour;

@Component
public class InitCompetencyLibraryBehaviour {
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	@Autowired
	private ICompetencyLibraryBehaviourService competencyLibraryBehaviourService;
	
	public void generate()
	{
		if(competencyLibraryBehaviourService.count().intValue() == 0)
		{
			Date now = new Date();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(StringPool.SCHEDULER);
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(StringPool.SCHEDULER);
			commonFields.setLastModifiedDate(now);
			
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/CompetencyLibraryBehaviour.xlsx");
			
			List<CompetencyLibraryBehaviourDto> list = ExcelCompetencyLibraryBehaviour.parse(is);
			
			List<CompetencyLibraryBehaviour> listCompetencyLibraryBehaviour = new ArrayList<CompetencyLibraryBehaviour>();
			CompetencyLibraryBehaviour competencyLibraryBehaviour =  null;
			CompetencyLibrary competencyLibrary = null;
			Lcb lcb = null;
			
			for(CompetencyLibraryBehaviourDto competencyLibraryBehaviourDto : list)
			{
				competencyLibraryBehaviour = new CompetencyLibraryBehaviour();
				
				
				competencyLibrary = competencyLibraryService.getByCompetencyName(competencyLibraryBehaviourDto.getCompetencyName());
				
				competencyLibraryBehaviour.setCompetencyLibrary(competencyLibrary);
				competencyLibraryBehaviour.setCommonFields(commonFields);
				
				competencyLibraryBehaviour.setBehaviourLevel(competencyLibraryBehaviourDto.getBehaviourLevel());
				competencyLibraryBehaviour.setBehaviour(competencyLibraryBehaviourDto.getBehaviour());
				
				if(competencyLibraryBehaviour.getCompetencyLibrary() != null && competencyLibraryBehaviour.getBehaviourLevel() > 0)
				{
					listCompetencyLibraryBehaviour.add(competencyLibraryBehaviour);
				}
			}
			
			competencyLibraryBehaviourService.save(listCompetencyLibraryBehaviour);
		}
	}
}
