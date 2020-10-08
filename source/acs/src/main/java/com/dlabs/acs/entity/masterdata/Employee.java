package com.dlabs.acs.entity.masterdata;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;
import com.dlabs.acs.util.DateUtil;

@NamedQueries({
	@NamedQuery(name="Employee.getBySearch", query="FROM Employee where LOWER(fullName) like :search  OR LOWER(email) like :search  OR LOWER(nik) like :search  order by id asc"),
	@NamedQuery(name="Employee.countBySearch", query="select count(*) FROM Employee where LOWER(fullName) like :search  OR LOWER(email) like :search  OR LOWER(nik) like :search"),
	
	@NamedQuery(name="Employee.getByNik", query="FROM Employee where LOWER(nik) = :nik order by id asc"),
	@NamedQuery(name="Employee.getNikExisting", query="select nik FROM Employee where LOWER(nik) IN :niks order by id asc"),
})
@Entity
public class Employee extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = false)
	private String email;
	
	/**Credentials **/
	@Column(nullable = false, unique=true)
	private String nik;

	private String placeOfBirth;
	
	private Date dateOfBirth;
	
	private String phone;
	
	private String workingArea;
	
	private String currentPositionName;
	
	private String currentPeriode;
	@Column(length=4000)
	private String currentResponsibility;
	
	private String currentDirectSupervisor;
	
	private String currentSupervisorEmail;
	
	private String profilePicture;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public CommonFields getCommonFields() {
		return commonFields;
	}

	public void setCommonFields(CommonFields commonFields) {
		this.commonFields = commonFields;
	}

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

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public String getBirthDetail()
	{
		String pob = this.placeOfBirth;
		if(pob == null)
		{
			pob = "";
		}
		return this.placeOfBirth + ", " + DateUtil.dateToStr(this.dateOfBirth);
	}
	
}
