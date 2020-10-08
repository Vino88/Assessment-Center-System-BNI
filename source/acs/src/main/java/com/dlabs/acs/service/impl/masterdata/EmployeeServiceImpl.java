package com.dlabs.acs.service.impl.masterdata;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.masterdata.IEmployeeDao;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.masterdata.IEmployeeService;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl extends AbstractServiceImpl<Employee, Long> implements IEmployeeService{
	private Logger log = Logger.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private IEmployeeDao employeeDao;

	@Override
	public IAbstractDao<Employee, Long> getDao() {
		// TODO Auto-generated method stub
		return employeeDao;
	}
	
	public Long countBySearch(String search){
		return employeeDao.countBySearch(search);
	}
	
	public List<Employee> getBySearch(String search, int start, int num){
		return employeeDao.getBySearch(search, start, num);
	}
	
	public Employee getByNik(String nik){
		return employeeDao.getByNik(nik);
	}
	
	public List<String> getNikExisting(List<String> niks){
		return employeeDao.getNikExisting(niks);
	}
}
