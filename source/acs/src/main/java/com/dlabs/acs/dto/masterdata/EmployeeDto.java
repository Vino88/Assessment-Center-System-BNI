package com.dlabs.acs.dto.masterdata;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dlabs.acs.dto.DataFormDto;
import com.dlabs.acs.util.DateUtil;

public class EmployeeDto extends DataFormDto{
	@NotNull
	@Size(max=255)
	private String fullName;
	
	@NotNull
	@Size(max=255)
	private String email;

	@NotNull(message="NPP can not be empty")
	@Size(max=255, message="NPP Maximum 255 characters")
	private String nik;

	@Size(max=255)
	private String placeOfBirth;
	
	private Date dateOfBirth;
	private String dateOfBirthStr;
	
	@Size(max=255)
	private String phone;
	
	@Size(max=255)
	private String workingArea;
	
	@Size(max=255)
	private String currentPositionName;
	
	@Size(max=255)
	private String currentPeriode;
	
	@Size(max=255)
	private String currentResponsibility;
	
	@Size(max=255)
	private String currentDirectSupervisor;
	
	@Size(max=255)
	private String currentSupervisorEmail;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		this.dateOfBirthStr = DateUtil.dateToStr(dateOfBirth);
	}

	public String getDateOfBirthStr() {
		return dateOfBirthStr;
	}

	public void setDateOfBirthStr(String dateOfBirthStr) {
		this.dateOfBirthStr = dateOfBirthStr;
		this.dateOfBirth = DateUtil.stringToDate(dateOfBirthStr);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWorkingArea() {
		return workingArea;
	}

	public void setWorkingArea(String workingArea) {
		this.workingArea = workingArea;
	}

	public String getCurrentPositionName() {
		return currentPositionName;
	}

	public void setCurrentPositionName(String currentPositionName) {
		this.currentPositionName = currentPositionName;
	}

	public String getCurrentPeriode() {
		return currentPeriode;
	}

	public void setCurrentPeriode(String currentPeriode) {
		this.currentPeriode = currentPeriode;
	}

	public String getCurrentResponsibility() {
		return currentResponsibility;
	}

	public void setCurrentResponsibility(String currentResponsibility) {
		this.currentResponsibility = currentResponsibility;
	}

	public String getCurrentDirectSupervisor() {
		return currentDirectSupervisor;
	}

	public void setCurrentDirectSupervisor(String currentDirectSupervisor) {
		this.currentDirectSupervisor = currentDirectSupervisor;
	}

	public String getCurrentSupervisorEmail() {
		return currentSupervisorEmail;
	}

	public void setCurrentSupervisorEmail(String currentSupervisorEmail) {
		this.currentSupervisorEmail = currentSupervisorEmail;
	}

	@Override
	public String toString() {
		return "EmployeeDto [fullName=" + fullName + ", email=" + email + ", nik=" + nik + ", placeOfBirth="
				+ placeOfBirth + ", dateOfBirth=" + dateOfBirth + ", dateOfBirthStr=" + dateOfBirthStr + ", phone="
				+ phone + ", workingArea=" + workingArea + ", currentPositionName=" + currentPositionName
				+ ", currentPeriode=" + currentPeriode + ", currentResponsibility=" + currentResponsibility
				+ ", currentDirectSupervisor=" + currentDirectSupervisor + "]";
	}	
	
}