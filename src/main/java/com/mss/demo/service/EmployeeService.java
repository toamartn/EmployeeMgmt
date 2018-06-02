package com.mss.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mss.demo.bean.Employee;
import com.mss.demo.bean.EmployeeLocation;
import com.mss.demo.bean.WorkLocation;
import com.mss.demo.controller.Sender;
import com.mss.demo.dao.EmployeeDAODataJPA;
import com.mss.demo.entities.EmployeeMapping;

@Component
public class EmployeeService {

	final String GET_LOC_URL = "http://192.168.0.3:8082/locMgmt";
	private static final Log logger = LogFactory.getLog(EmployeeService.class);

	// @Autowired
	private EmployeeDAODataJPA employeeDAO;

	// @Autowired
	private RestTemplate restTemplate;

	Sender sender;
	
	@Autowired
	public EmployeeService(EmployeeDAODataJPA employeeDAO, RestTemplateBuilder restTemplateBuilder, Sender sender) {
		super();
		this.employeeDAO = employeeDAO;
		this.restTemplate = restTemplateBuilder.build();
		this.sender = sender;
	}

	public List<Employee> getAllEmployees() {

		List<EmployeeMapping> empMapList = employeeDAO.findAll();
		List<Employee> employeeList = new ArrayList<Employee>();

		for (EmployeeMapping rec : empMapList) {
			Employee emp = new Employee();
			employeeList.add(emp);
			BeanUtils.copyProperties(rec, emp);
		}
		return employeeList;
	}

	public Employee getEmployee(int id) {
		EmployeeMapping empMapping = employeeDAO.findOne(id);
		Employee emp = new Employee();
		BeanUtils.copyProperties(empMapping, emp);
		return emp;
	}

	public Employee createEmployee(Employee emp) {
		EmployeeMapping employeeMapping = new EmployeeMapping();
		BeanUtils.copyProperties(emp, employeeMapping);
		employeeMapping = employeeDAO.save(employeeMapping);
		BeanUtils.copyProperties(employeeMapping, emp);
		return emp;
	}

	public Employee updateEmployee(Employee employee) {

		if (employee == null || !employeeDAO.exists(employee.getEmpId())) {
			System.out.println(employeeDAO.exists(employee.getEmpId()));
			System.out.println("No record exist with id " + employee.getEmpId());
			return null;
		}
		EmployeeMapping employeeMapping = new EmployeeMapping();
		BeanUtils.copyProperties(employee, employeeMapping);
		employeeMapping = employeeDAO.save(employeeMapping);
		BeanUtils.copyProperties(employeeMapping, employee);
		
		/////////////////////////////////////// Sending new location to LocMgmtQ QUEUE ////////////////////////////
		Map<String, Object> deskDetails = new HashMap<String, Object>();
		deskDetails.put("LOCATION_NUMBER", employee.getLocationNumber());
		deskDetails.put("EMPLOYEEID", employee.getEmpId());
		sender.send(deskDetails);

		return employee;
	}

	public void updateLocation(int locNum, int empId) {

		System.out.println("Request recieved to update Location details in Employee  " + locNum + " emp " + empId);
		EmployeeMapping employeeMapping = employeeDAO.findOne(empId);
		employeeMapping.setLocationNumber(locNum);
		employeeDAO.save(employeeMapping);
	}

	public Employee deleteEmployee(int id) {

		EmployeeMapping employeeMapping = employeeDAO.findOne(id);
		if (employeeMapping != null) {
			employeeDAO.delete(new Integer(id));
		}
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeMapping, employee);
		return employee;
	}

	public EmployeeLocation getEmpLoc(int empId) {
		EmployeeLocation empLoc = new EmployeeLocation();
		EmployeeMapping empMapping = employeeDAO.findOne(empId);
		BeanUtils.copyProperties(empMapping, empLoc, getNullPropertyNames(empMapping));
		WorkLocation location = null;
		try {
			System.out.println("Calling location URL:::  " + GET_LOC_URL + "/get/" + empMapping.getLocationNumber()); 
			// get location details by location number
			location = restTemplate.getForObject(GET_LOC_URL + "/get/" + empMapping.getLocationNumber(),
					WorkLocation.class);
			logger.info("calling fares to get fare " + location);
		} catch (Exception e) {
			logger.error("Location SERVICE IS NOT AVAILABLE");
		}
		BeanUtils.copyProperties(location, empLoc, getNullPropertyNames(location));
		return empLoc;
	}

	/**
	 * Returns an array of null properties of an object
	 * 
	 * @param source
	 * @return
	 */
	private String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set emptyNames = new HashSet();
		for (java.beans.PropertyDescriptor pd : pds) {
			// check if value of this property is null then add it to the collection
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return (String[]) emptyNames.toArray(result);
	}
}