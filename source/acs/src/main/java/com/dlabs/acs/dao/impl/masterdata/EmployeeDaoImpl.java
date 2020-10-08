package com.dlabs.acs.dao.impl.masterdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dlabs.acs.dao.impl.AbstractDaoImpl;
import com.dlabs.acs.dao.intf.masterdata.IEmployeeDao;
import com.dlabs.acs.entity.masterdata.Employee;

@Repository
public class EmployeeDaoImpl extends AbstractDaoImpl<Employee, Long> implements IEmployeeDao {
	private Logger log = Logger.getLogger(EmployeeDaoImpl.class);
	
	protected EmployeeDaoImpl() {
		super(Employee.class);
	}

	private static final String GET_BY_SEARCH = "Employee.getBySearch";
	private static final String COUNT_BY_SEARCH = "Employee.countBySearch";
	
	
	public List<Employee> getBySearch(String search, int start, int num) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		List<Employee> result = query.list();
		if (result == null) {
			result = new ArrayList<Employee>();
		}
		return result;
	}
	
	public Long countBySearch(String search) {
		if(search == null || search.trim().length() == 0)
		{
			search="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(COUNT_BY_SEARCH);
		query.setString("search", "%"+search.toLowerCase()+"%");

		List<Long> result = query.list();
		
		return result.get(0);
	}
	
	private static final String GET_BY_NIK = "Employee.getByNik";
	public Employee getByNik(String nik) {
		if(nik == null || nik.trim().length() == 0)
		{
			nik="";
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_BY_NIK);
		query.setString("nik", nik.toLowerCase());

		List<Employee> result = query.list();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	private static final String GET_NIK_EXISTING = "Employee.getNikExisting";
	public List<String> getNikExisting(List<String> niks) {
		if(niks == null || niks.size() == 0)
		{
			return Collections.EMPTY_LIST;
		}
		Session session = getCurrentSession();
		Query query = session.getNamedQuery(GET_NIK_EXISTING);
		query.setParameterList("niks", niks);
		
		List<String> result = query.list();
		if (result == null) {
			result = new ArrayList<String>();
		}
		return result;
	}
}
