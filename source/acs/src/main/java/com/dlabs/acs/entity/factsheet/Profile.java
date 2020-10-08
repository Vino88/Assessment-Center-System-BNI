package com.dlabs.acs.entity.factsheet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;

@NamedQueries({
	@NamedQuery(name="Profile.getBySearch", query="FROM Profile where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="Profile.countBySearch", query="select count(*) FROM Profile where LOWER(id) like :search"),
})
@Entity
public class Profile extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;

	private String currentPositionName;
	
	private String currentPeriode;
	@Column(length=4000)
	private String currentResponsibility;
	
	private String currentDirectSupervisor;
	private String currentSupervisorEmail;
	
	private String workingArea;
	
	@Column(length=4000)
	private String careerAspiration;
	
	@Column(length=4000)
	private String mainResponsibility;
	
	@Column(length=4000)
	private String strong;
	
	@Column(length=4000)
	private String weakness;
	
	@Column(length=4000)
	private String personalCompetency;
	
	@Column(length=4000)
	private String leadershipCompetency;
	
	@Column(length=4000)
	private String functionalCompetency;
	
	@Column(length=4000)
	private String strongIg;
	
	@Column(length=4000)
	private String weaknessIg;
	
	@Column(length=4000)
	private String personalCompetencyIg;
	
	@Column(length=4000)
	private String leadershipCompetencyIg;
	
	@Column(length=4000)
	private String functionalCompetencyIg;

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

	public String getWorkingArea() {
		return workingArea;
	}

	public void setWorkingArea(String workingArea) {
		this.workingArea = workingArea;
	}

	public String getCareerAspiration() {
		return careerAspiration;
	}

	public void setCareerAspiration(String careerAspiration) {
		this.careerAspiration = careerAspiration;
	}

	public String getMainResponsibility() {
		return mainResponsibility;
	}

	public void setMainResponsibility(String mainResponsibility) {
		this.mainResponsibility = mainResponsibility;
	}

	public String getStrong() {
		return strong;
	}

	public void setStrong(String strong) {
		this.strong = strong;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}

	public String getPersonalCompetency() {
		return personalCompetency;
	}

	public void setPersonalCompetency(String personalCompetency) {
		this.personalCompetency = personalCompetency;
	}

	public String getLeadershipCompetency() {
		return leadershipCompetency;
	}

	public void setLeadershipCompetency(String leadershipCompetency) {
		this.leadershipCompetency = leadershipCompetency;
	}

	public String getFunctionalCompetency() {
		return functionalCompetency;
	}

	public void setFunctionalCompetency(String functionalCompetency) {
		this.functionalCompetency = functionalCompetency;
	}

	public String getStrongIg() {
		return strongIg;
	}

	public void setStrongIg(String strongIg) {
		this.strongIg = strongIg;
	}

	public String getWeaknessIg() {
		return weaknessIg;
	}

	public void setWeaknessIg(String weaknessIg) {
		this.weaknessIg = weaknessIg;
	}

	public String getPersonalCompetencyIg() {
		return personalCompetencyIg;
	}

	public void setPersonalCompetencyIg(String personalCompetencyIg) {
		this.personalCompetencyIg = personalCompetencyIg;
	}

	public String getLeadershipCompetencyIg() {
		return leadershipCompetencyIg;
	}

	public void setLeadershipCompetencyIg(String leadershipCompetencyIg) {
		this.leadershipCompetencyIg = leadershipCompetencyIg;
	}

	public String getFunctionalCompetencyIg() {
		return functionalCompetencyIg;
	}

	public void setFunctionalCompetencyIg(String functionalCompetencyIg) {
		this.functionalCompetencyIg = functionalCompetencyIg;
	}
}
