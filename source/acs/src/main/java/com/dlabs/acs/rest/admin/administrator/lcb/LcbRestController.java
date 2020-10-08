package com.dlabs.acs.rest.admin.administrator.lcb;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.lcb.Lcb;
import com.dlabs.acs.rest.admin.administrator.factsheet.AdminProfileRestController;
import com.dlabs.acs.service.intf.lcb.ILcbService;

@RestController()
@RequestMapping("rest/admin/administrator/lcb")
public class LcbRestController {
	private Logger logger = Logger.getLogger(AdminProfileRestController.class);
	@Autowired
	private ILcbService lcbService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<Lcb> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Lcb> dataTables = new DataTables<Lcb>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) lcbService.count();
		}
		else {
		    recordCount = (long) lcbService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<Lcb> entities = lcbService.getAll(start, length);
		    
		    dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<Lcb> entities = lcbService.getBySearch(sSearch, start, length);
			dataTables.setRecordsFiltered(lcbService.countBySearch(sSearch));
			dataTables.setData(entities);
		    return dataTables;
		}
		
	}
}
