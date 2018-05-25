package com.mss.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class EmployeeMapping implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="EMPID")
	private Integer empId;
	
	@Column(name="LOCATION_NUMBER")
	private Integer locationNumber;
	
	@Column(name="FIRSTNAME")
	String firstName;
	
	@Column(name="LASTNAME")
	String lastName;
	
	@Column(name="DEPT")
	String dept;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getLocationNumber() {
		return locationNumber;
	}

	public void setLocationNumber(Integer locationNumber) {
		this.locationNumber = locationNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EmployeeMapping [empId=" + empId + ", locationNumber=" + locationNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dept=" + dept + "]";
	}

	public EmployeeMapping(Integer empId, Integer locationNumber, String firstName, String lastName, String dept) {
		super();
		this.empId = empId;
		this.locationNumber = locationNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dept = dept;
	}

	public EmployeeMapping() {
		
	}
}