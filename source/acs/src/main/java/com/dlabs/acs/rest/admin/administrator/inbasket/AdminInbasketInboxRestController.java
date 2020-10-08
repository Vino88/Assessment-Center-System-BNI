package com.dlabs.acs.rest.admin.administrator.inbasket;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.inbasket.InbasketInbox;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxService;

@RestController()
@RequestMapping("rest/admin/administrator/inbasket/inbasketinbox")
public class AdminInbasketInboxRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminInbasketInboxRestController.class);
	@Autowired
	private IInbasketInboxService inbasketinboxService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<InbasketInbox> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<InbasketInbox> dataTables = new DataTables<InbasketInbox>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) inbasketinboxService.count();
		}
		else {
		    recordCount = (long) inbasketinboxService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<InbasketInbox> entities = inbasketinboxService.getAllActive(start, length);
		    
		     dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<InbasketInbox> entities = inbasketinboxService.getBySearch(sSearch, start, length);
		    
		    dataTables.setData(entities);
		    return dataTables;
		}
		
	}
}
