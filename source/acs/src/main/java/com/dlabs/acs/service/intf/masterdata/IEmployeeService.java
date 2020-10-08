package com.dlabs.acs.service.intf.masterdata;

import java.util.List;

import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.service.IService;

public interface IEmployeeService  extends IService<Employee,Long >{
	
	public Long countBySearch(String search);
	
	public List<Employee> getBySearch(String search, int start, int num);
	
	public Employee getByNik(String nik);
	
	public List<String> getNikExisting(List<String> niks);
}
