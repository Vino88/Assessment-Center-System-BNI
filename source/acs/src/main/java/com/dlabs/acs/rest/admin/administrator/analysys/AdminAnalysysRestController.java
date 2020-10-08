package com.dlabs.acs.rest.admin.administrator.analysys;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.analysys.IAnalysysService;

@RestController()
@RequestMapping("rest/admin/administrator/analysys/analysys")
public class AdminAnalysysRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminAnalysysRestController.class);
	@Autowired
	private IAnalysysService analysysService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<Analysys> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Analysys> dataTables = new DataTables<Analysys>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) analysysService.count();
		}
		else {
		    recordCount = (long) analysysService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<Analysys> entities = analysysService.getAllActive(start, length);
		    
		     dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<Analysys> entities = analysysService.getBySearch(sSearch, start, length);
		    
		    dataTables.setData(entities);
		    return dataTables;
		}
		
	}
}
