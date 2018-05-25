package com.mss.demo.bean;

import java.io.Serializable;

public class WorkLocation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Integer locationNumber=0;
	Integer employeeId=0;
	Integer floor;
	String branch;
	String city;
	Integer occupied;

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
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

	public Integer getOccupied() {
		return occupied;
	}

	public void setOccupied(Integer occupied) {
		this.occupied = occupied;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "WorkLocationMapping [locationNumber=" + locationNumber + ", employeeID=" + employeeId + ", floor="
				+ floor + ", branch=" + branch + ", city=" + city + ", occupied=" + occupied + "]";
	}

	public WorkLocation(Integer locationNumber, Integer employeeId, Integer floor, String branch, String city,
			Integer occupied) {
		super();
		this.locationNumber = locationNumber;
		this.employeeId = employeeId;
		this.floor = floor;
		this.branch = branch;
		this.city = city;
		this.occupied = occupied;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getLocationNumber() {
		return locationNumber;
	}

	public void setLocationNumber(Integer locationNumber) {
		this.locationNumber = locationNumber;
	}

	public WorkLocation() {
	}
}