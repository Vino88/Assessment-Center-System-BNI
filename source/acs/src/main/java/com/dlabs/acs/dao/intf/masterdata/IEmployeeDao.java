package com.dlabs.acs.dao.intf.masterdata;

import java.util.List;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.entity.masterdata.Employee;


public interface IEmployeeDao extends IAbstractDao <Employee,Long>{
	
	public Long countBySearch(String search);
	
	public List<Employee> getBySearch(String search, int start, int num);
	
	public Employee getByNik(String nik);
	
	public List<String> getNikExisting(List<String> niks);
}
