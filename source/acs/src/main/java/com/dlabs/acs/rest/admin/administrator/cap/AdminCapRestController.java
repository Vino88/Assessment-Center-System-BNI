package com.dlabs.acs.rest.admin.administrator.cap;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.cap.ICapService;

@RestController()
@RequestMapping("rest/admin/administrator/cap/cap")
public class AdminCapRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminCapRestController.class);
	@Autowired
	private ICapService capService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<Cap> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Cap> dataTables = new DataTables<Cap>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) capService.count();
		}
		else {
		    recordCount = (long) capService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<Cap> entities = capService.getAllActive(start, length);
		    
		     dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<Cap> entities = capService.getBySearch(sSearch, start, length);
		    
		    dataTables.setData(entities);
		    return dataTables;
		}
		
	}
}
