 package com.mss.demo.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mss.demo.bean.Employee;
import com.mss.demo.bean.EmployeeLocation;
import com.mss.demo.bean.Employees;
import com.mss.demo.service.EmployeeService;

@CrossOrigin
@Controller
@RequestMapping("/empMgmt")	//	PATH
public class EmployeeController {
	
	@Autowired(required=true)
	private EmployeeService employeeService;
	
	private static final Log log = LogFactory.getLog(EmployeeController.class);
	
	@PostMapping(value = "/create")	//	END POINT
	public @ResponseBody Employee createEmployee(@RequestBody Employee employee) {
		Employee employeeResponse = employeeService.createEmployee(employee);
		return employeeResponse;
	}

	@GetMapping(value = "/getAll")
	public @ResponseBody Employees getAllSchedules() {
		log.info("got request for ");
		List<Employee> emps = employeeService.getAllEmployees();
		Employees employees = new Employees();
		employees.setEmployees(emps);
		return employees;
	}

	@GetMapping(value = "/get/{id}")
	public @ResponseBody Employee getEmployee(@PathVariable ("id") int id) {
		log.info("got request for "+ id);
		Employee  employee = employeeService.getEmployee(id);
		return employee;
	}
	
	@GetMapping(value = "/getEmpLoc/{id}")
	public @ResponseBody EmployeeLocation getEmployeeLocation(@PathVariable ("id") int empId) {
		log.info("got request for "+ empId);
		EmployeeLocation  empLoc = employeeService.getEmpLoc(empId);
		return empLoc;
	}

	@PutMapping(value = "/update")
	public @ResponseBody Employee updateEmployee(@RequestBody Employee employee) {
		Employee employeeResponse = employeeService.updateEmployee(employee);
		return employeeResponse;
	}

	@DeleteMapping(value = "/delete/{id}")
	public @ResponseBody Employee deleteeEmployee(@PathVariable("id") int id) {
		Employee employee = employeeService.deleteEmployee(id);
		return employee;
	}
}