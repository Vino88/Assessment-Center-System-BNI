package com.dlabs.acs.rest.admin.administrator.masterdata;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.masterdata.IEmployeeService;

@RestController()
@RequestMapping("rest/admin/administrator/masterdata/employee")
public class AdminEmployeeRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminEmployeeRestController.class);
	@Autowired
	private IEmployeeService employeeService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<Employee> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Employee> dataTables = new DataTables<Employee>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) employeeService.count();
		}
		else {
		    recordCount = (long) employeeService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<Employee> entities = employeeService.getAllActive(start, length);
		    
		     dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<Employee> entities = employeeService.getBySearch(sSearch, start, length);
		    
		    dataTables.setData(entities);
		    return dataTables;
		}
		
	}
	
	@RequestMapping(value = "/searchemployee", method = RequestMethod.GET)
	public Employee searchemployee(Model model, @RequestParam("nik") String nik) {
		Employee employee = employeeService.getByNik(nik);
		
		return employee;
	}
	
}
