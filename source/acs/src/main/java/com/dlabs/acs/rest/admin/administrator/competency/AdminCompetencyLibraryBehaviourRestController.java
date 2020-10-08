package com.dlabs.acs.rest.admin.administrator.competency;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryBehaviourService;

@RestController()
@RequestMapping("rest/admin/administrator/competency/competencylibrarybehaviour")
public class AdminCompetencyLibraryBehaviourRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminCompetencyLibraryBehaviourRestController.class);
	@Autowired
	private ICompetencyLibraryBehaviourService competencylibrarybehaviourService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<CompetencyLibraryBehaviour> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<CompetencyLibraryBehaviour> dataTables = new DataTables<CompetencyLibraryBehaviour>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) competencylibrarybehaviourService.count();
		}
		else {
		    recordCount = (long) competencylibrarybehaviourService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<CompetencyLibraryBehaviour> entities = competencylibrarybehaviourService.getAllActive(start, length);
		    
		     dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<CompetencyLibraryBehaviour> entities = competencylibrarybehaviourService.getBySearch(sSearch, start, length);
		    
		    dataTables.setData(entities);
		    return dataTables;
		}
		
	}
}
