package com.dlabs.acs.rest.admin.administrator.participant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.participant.IBatchService;

@RestController()
@RequestMapping("rest/admin/administrator/participant/batch")
public class AdminBatchRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminBatchRestController.class);
	@Autowired
	private IBatchService batchService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<Batch> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Batch> dataTables = new DataTables<Batch>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) batchService.count();
		}
		else {
		    recordCount = (long) batchService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<Batch> entities = batchService.getAllActive(start, length);
		    
		     dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<Batch> entities = batchService.getBySearch(sSearch, start, length);
		    
		    dataTables.setData(entities);
		    return dataTables;
		}
	}
	
	
	@RequestMapping(value = "/listinprogress", method = RequestMethod.GET)
	public DataTables<Batch> listinprogress(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Batch> dataTables = new DataTables<Batch>();
		
		Long recordCount = (long) batchService.countInprogressBySearch(sSearch);
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		List<Batch> entities = batchService.getInprogressBySearch(sSearch, start, length);
	    
	    dataTables.setData(entities);
	    return dataTables;
	}
	
	
	@RequestMapping(value = "/listinprogressbyuser", method = RequestMethod.GET)
	public DataTables<Batch> listinprogressbyuser(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch, HttpServletRequest request)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Batch> dataTables = new DataTables<Batch>();
		
		UserAdmin userAdmin = (UserAdmin)request.getSession().getAttribute(SessionConstants.USER_ADMIN);
		
		Long recordCount = (long) batchService.countInprogressByUserAdminIdBySearch(userAdmin.getId(), sSearch);
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		List<Batch> entities = batchService.getInprogressByUserAdminIdBySearch(userAdmin.getId(), sSearch, start, length);
	    
	    dataTables.setData(entities);
	    return dataTables;
	}
}
