package com.dlabs.acs.rest.admin.administrator.credential;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.lcb.enumeration.Role;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.IUserAdminService;

@RestController()
@RequestMapping("rest/admin/administrator/credential/useradmin")
public class AdminUserAdminRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminUserAdminRestController.class);
	@Autowired
	private IUserAdminService useradminService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<UserAdmin> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<UserAdmin> dataTables = new DataTables<UserAdmin>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) useradminService.count();
		}
		else {
		    recordCount = (long) useradminService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<UserAdmin> entities = useradminService.getAllActive(start, length);
		    
		     dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<UserAdmin> entities = useradminService.getBySearch(sSearch, start, length);
		    
		    dataTables.setData(entities);
		    return dataTables;
		}
	}
	
	
	
	@RequestMapping(value = "/listoperator", method = RequestMethod.GET)
	public DataTables<UserAdmin> listoperator(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<UserAdmin> dataTables = new DataTables<UserAdmin>();
		
		Long recordCount = null;
		
		recordCount = (long) useradminService.countByRoleAndUsername(Role.OPERATOR, sSearch);
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		
		
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		List<UserAdmin> entities = useradminService.getByRoleAndUsername(Role.OPERATOR, sSearch, start, length);
	    
	    dataTables.setData(entities);
	    return dataTables;
		
	}
	
	
	@RequestMapping(value = "/listassessor", method = RequestMethod.GET)
	public DataTables<UserAdmin> listassessor(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<UserAdmin> dataTables = new DataTables<UserAdmin>();
		
		Long recordCount = null;
		
		recordCount = (long) useradminService.countByRoleAndUsername(Role.ASSESSOR, sSearch);
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		
		
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		List<UserAdmin> entities = useradminService.getByRoleAndUsername(Role.ASSESSOR, sSearch, start, length);
	    
	    dataTables.setData(entities);
	    return dataTables;
		
	}
	
	
	@RequestMapping(value = "/listasignassessor", method = RequestMethod.GET)
	public DataTables listasignassessor(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables dataTables = new DataTables();
		
		Long recordCount = null;
		
		recordCount = (long) useradminService.countAssessorNumberOfTask(sSearch);
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		
		
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		List<UserAdmin> entities = useradminService.getAssessorNumberOfTask(sSearch, start, length);
	    
	    dataTables.setData(entities);
	    return dataTables;
		
	}
}
