package com.dlabs.acs.rest.admin.administrator.analysys;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.analysys.CompetencyLibraryAnalysys;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.analysys.ICompetencyLibraryAnalysysService;

@RestController()
@RequestMapping("rest/admin/administrator/analysys/competencylibraryanalysys")
public class AdminCompetencyLibraryAnalysysRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminCompetencyLibraryAnalysysRestController.class);
	@Autowired
	private ICompetencyLibraryAnalysysService competencylibraryanalysysService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<CompetencyLibraryAnalysys> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<CompetencyLibraryAnalysys> dataTables = new DataTables<CompetencyLibraryAnalysys>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) competencylibraryanalysysService.count();
		}
		else {
		    recordCount = (long) competencylibraryanalysysService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<CompetencyLibraryAnalysys> entities = competencylibraryanalysysService.getAllActive(start, length);
		    
		     dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<CompetencyLibraryAnalysys> entities = competencylibraryanalysysService.getBySearch(sSearch, start, length);
		    
		    dataTables.setData(entities);
		    return dataTables;
		}
		
	}
}
