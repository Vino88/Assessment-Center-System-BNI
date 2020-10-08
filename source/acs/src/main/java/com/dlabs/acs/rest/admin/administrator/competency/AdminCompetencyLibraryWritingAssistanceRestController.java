package com.dlabs.acs.rest.admin.administrator.competency;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.competency.CompetencyLibraryWritingAssistance;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryWritingAssistanceService;

@RestController()
@RequestMapping("rest/admin/administrator/competency/competencylibrarywritingassistance")
public class AdminCompetencyLibraryWritingAssistanceRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminCompetencyLibraryWritingAssistanceRestController.class);
	@Autowired
	private ICompetencyLibraryWritingAssistanceService competencylibrarywritingassistanceService;
	
	
	@RequestMapping(value = "/list/{writingCompetency}/{writingLevel}", method = RequestMethod.GET)
	public DataTables<CompetencyLibraryWritingAssistance> pageListInstallment(@PathVariable("writingCompetency") Long writingCompetency,@PathVariable("writingLevel") Integer writingLevel,@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<CompetencyLibraryWritingAssistance> dataTables = new DataTables<CompetencyLibraryWritingAssistance>();
		
		Long recordCount = null;
		
		if(writingCompetency == 0L && writingLevel == 0)
		{
			recordCount = (long) competencylibrarywritingassistanceService.count();
		}else if(writingCompetency > 0L && writingLevel > 0)
		{
			recordCount = (long) competencylibrarywritingassistanceService.countByCompetencyLibraryIdAndLevel(writingCompetency, writingLevel);
		}else if(writingCompetency == 0L && writingLevel > 0)
		{
			recordCount = (long) competencylibrarywritingassistanceService.countByLevel(writingLevel);
		}else if(writingCompetency > 0L && writingLevel == 0)
		{
			recordCount = (long) competencylibrarywritingassistanceService.countByCompetencyLibraryId(writingCompetency);
		}
		
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		
		List<CompetencyLibraryWritingAssistance> entities = Collections.EMPTY_LIST;
		
		if(writingCompetency == 0L && writingLevel == 0)
		{
			entities = competencylibrarywritingassistanceService.getBySearch(sSearch, start, length);
		}else if(writingCompetency > 0L && writingLevel > 0)
		{
			entities = competencylibrarywritingassistanceService.getByCompetencyLibraryIdAndLevel(writingCompetency, writingLevel, start, length);
		}else if(writingCompetency == 0L && writingLevel > 0)
		{
			entities = competencylibrarywritingassistanceService.getByLevel(writingLevel, start, length);
		}else if(writingCompetency > 0L && writingLevel == 0)
		{
			entities = competencylibrarywritingassistanceService.getByCompetencyLibraryId(writingCompetency, start, length);
		}
		
	    dataTables.setData(entities);
	    return dataTables;
		
	}
	
	
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	public DataTables<CompetencyLibraryWritingAssistance> list(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<CompetencyLibraryWritingAssistance> dataTables = new DataTables<CompetencyLibraryWritingAssistance>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) competencylibrarywritingassistanceService.count();
		}
		else {
		    recordCount = (long) competencylibrarywritingassistanceService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		List<CompetencyLibraryWritingAssistance> entities = competencylibrarywritingassistanceService.getBySearch(sSearch, start, length);
	    
	    dataTables.setData(entities);
	    return dataTables;
		
	}
}
