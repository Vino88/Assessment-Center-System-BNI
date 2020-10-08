package com.dlabs.acs.rest.admin.administrator.config;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.config.SystemConfig;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.config.ISystemConfigService;

@RestController()
@RequestMapping("rest/admin/administrator/config/systemconfig")
public class AdminSystemConfigRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminSystemConfigRestController.class);
	@Autowired
	private ISystemConfigService systemconfigService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<SystemConfig> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<SystemConfig> dataTables = new DataTables<SystemConfig>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) systemconfigService.count();
		}
		else {
		    recordCount = (long) systemconfigService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<SystemConfig> entities = systemconfigService.getAllActive(start, length);
		    
		     dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<SystemConfig> entities = systemconfigService.getBySearch(sSearch, start, length);
		    
		    dataTables.setData(entities);
		    return dataTables;
		}
		
	}
}
