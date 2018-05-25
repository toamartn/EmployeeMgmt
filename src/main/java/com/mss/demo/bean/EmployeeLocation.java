package com.mss.demo.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeLocation {

	@XmlElement
	int empId;

	@XmlElement
	String firstName;

	@XmlElement
	String lastName;

	@XmlElement
	String dept;

	@XmlElement
	int locationNumber;

	@XmlElement
	int floor;

	@XmlElement
	String branch;

	@XmlElement
	String city;

	@Override
	public String toString() {
		return "EmployeeLocation [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", dept="
				+ dept + ", locationNumber=" + locationNumber + ", floor=" + floor + ", branch=" + branch + ", city="
				+ city + "]";
	}

	public EmployeeLocation(int empId, String firstName, String lastName, String dept, int locationNumber, int floor,
			String branch, String city) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dept = dept;
		this.locationNumber = locationNumber;
		this.floor = floor;
		this.branch = branch;
		this.city = city;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public int getLocationNumber() {
		return locationNumber;
	}

	public void setLocationNumber(int locationNumber) {
		this.locationNumber = locationNumber;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public EmployeeLocation() {

	}
}
