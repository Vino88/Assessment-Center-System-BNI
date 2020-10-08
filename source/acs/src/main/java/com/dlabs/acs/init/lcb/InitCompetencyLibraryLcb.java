package com.dlabs.acs.init.lcb;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.dto.lcb.CompetencyLibraryLcbDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.lcb.CompetencyLibraryLcb;
import com.dlabs.acs.entity.lcb.Lcb;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;
import com.dlabs.acs.service.intf.lcb.ICompetencyLibraryLcbService;
import com.dlabs.acs.service.intf.lcb.ILcbService;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.excel.lcb.ExcelCompetencyLibraryLcb;

@Component
public class InitCompetencyLibraryLcb {
	@Autowired
	private ICompetencyLibraryLcbService competencyLibraryLcbService;
	
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	@Autowired
	private ILcbService lcbService;
	
	public void generate()
	{
		if(competencyLibraryLcbService.count().intValue() == 0)
		{
			Date now = new Date();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(StringPool.SCHEDULER);
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(StringPool.SCHEDULER);
			commonFields.setLastModifiedDate(now);
			
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/CompetencyLibraryLcb.xlsx");
//			InputStream is =getClass().getClassLoader().getResourceAsStream("init/ScbCompetencyLibraryScb.xlsx");
			
			List<CompetencyLibraryLcbDto> list = ExcelCompetencyLibraryLcb.parse(is);
			
			List<CompetencyLibraryLcb> listCompetencyLibraryLcb = new ArrayList<CompetencyLibraryLcb>();
			CompetencyLibraryLcb  competencyLibraryLcb =  null;
			CompetencyLibrary competencyLibrary = null;
			Lcb lcb = null;
			
			for(CompetencyLibraryLcbDto competencyLibraryLcbDto : list)
			{
				competencyLibraryLcb = new CompetencyLibraryLcb();
				
				
				competencyLibrary = competencyLibraryService.getByCompetencyName(competencyLibraryLcbDto.getCompetencyName());
				lcb = lcbService.getByQuestNumber(competencyLibraryLcbDto.getQuestionNumber());
				
				competencyLibraryLcb.setCompetencyLibrary(competencyLibrary);
				competencyLibraryLcb.setLcb(lcb);
				competencyLibraryLcb.setCommonFields(commonFields);
				
				if(competencyLibraryLcb.getCompetencyLibrary() != null && competencyLibraryLcb.getLcb() != null)
				{
					listCompetencyLibraryLcb.add(competencyLibraryLcb);
				}
			}
			
			competencyLibraryLcbService.save(listCompetencyLibraryLcb);
		}
	}
}
