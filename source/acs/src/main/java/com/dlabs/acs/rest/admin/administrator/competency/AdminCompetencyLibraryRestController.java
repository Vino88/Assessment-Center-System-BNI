package com.dlabs.acs.rest.admin.administrator.competency;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;

@RestController()
@RequestMapping("rest/admin/administrator/competency/competencylibrary")
public class AdminCompetencyLibraryRestController {
	private Logger logger = Logger.getLogger(AdminCompetencyLibraryRestController.class);
	@Autowired
	private ICompetencyLibraryService competencyLibraryService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<CompetencyLibrary> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<CompetencyLibrary> dataTables = new DataTables<CompetencyLibrary>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) competencyLibraryService.count();
		}
		else {
		    recordCount = (long) competencyLibraryService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<CompetencyLibrary> entities = competencyLibraryService.getAllActive(start, length);
		    
		    dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<CompetencyLibrary> entities = competencyLibraryService.getBySearch(sSearch, start, length);
			dataTables.setRecordsFiltered(competencyLibraryService.countBySearch(sSearch));
			dataTables.setData(entities);
		    return dataTables;
		}
		
	}
}
