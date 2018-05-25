package com.mss.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mss.demo.entities.EmployeeMapping;

public interface EmployeeDAODataJPA extends JpaRepository<EmployeeMapping, Integer> {

	//public List<EmployeeMapping> findAllOrderByLastnameAsc();
//	public EmployeeMapping findByEmpId(Integer id);
// 	public List<EmployeeMapping> readByFirstNameIgnoringCaseOrLastNameIgnoringCase(String firstName, String lastName);
//	public List<EmployeeMapping> readByFirstNameOrLastName(String firstName, String lastName);
//	public EmployeeMapping addEmployee(EmployeeMapping employee);
	//public EmployeeMapping updateEmployee(EmployeeMapping employee);
	// public EmployeeMapping removeEmployee(Integer id);	 delete(Employee employee)
	// public boolean isEmployeeExists(int id);
}


// Verbs = get, read, find, and count.

/*@Query("select c from Customer c where c.email like '%gmail.com'")
Public List<Customer> findAllGmailCustomers();
*/
// count, delete, deleteAll, deleteAll, deleteById, existsById, findById, save
// https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html