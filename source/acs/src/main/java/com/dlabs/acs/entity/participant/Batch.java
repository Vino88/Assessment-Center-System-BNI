package com.dlabs.acs.entity.participant;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;
import com.dlabs.acs.entity.UserAdmin;

@NamedQueries({
	@NamedQuery(name="Batch.getBySearch", query="FROM Batch where commonFields.deleted = false AND (  LOWER(description) like :search ) order by id asc"),
	@NamedQuery(name="Batch.countBySearch", query="select count(*) FROM Batch where commonFields.deleted = false AND (  LOWER(description) like :search )"),
	
	@NamedQuery(name="Batch.getInprogressBySearch", query="FROM Batch where commonFields.deleted = false AND inprogress = true AND (  LOWER(description) like :search ) order by id asc"),
	@NamedQuery(name="Batch.countInprogressBySearch", query="select count(*) FROM Batch where commonFields.deleted = false AND inprogress = true AND (  LOWER(description) like :search )"),
	
	
	@NamedQuery(name="Batch.getInprogressByUserAdminIdBySearch", query="FROM Batch where commonFields.deleted = false AND inprogress = true AND (  LOWER(description) like :search ) AND userAdmin.id = :userAdminId order by id asc"),
	@NamedQuery(name="Batch.countInprogressByUserAdminIdBySearch", query="select count(*) FROM Batch where commonFields.deleted = false AND inprogress = true AND (  LOWER(description) like :search ) AND userAdmin.id = :userAdminId")
})
@Entity
public class Batch extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	private String description;
	
	private String location;
	private String locationSecond;
	private Integer maxQuota;
	
	@Column(length=2000)
	private String additionalInformation;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessementFirstHalfStartTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessementFirstHalfEndTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessementSecondHalfStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessementFinishTime;
	
	private boolean assessementFirstHalfStart;
	private boolean assessementSecondHalfStart;
	
	private boolean inprogress;
	
	@JoinColumn
	@OneToOne
	@Basic(fetch=FetchType.LAZY)
	private UserAdmin userAdmin;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(Integer maxQuota) {
		this.maxQuota = maxQuota;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public Date getAssessementFirstHalfStartTime() {
		return assessementFirstHalfStartTime;
	}

	public void setAssessementFirstHalfStartTime(Date assessementFirstHalfStartTime) {
		this.assessementFirstHalfStartTime = assessementFirstHalfStartTime;
	}

	public Date getAssessementSecondHalfStartTime() {
		return assessementSecondHalfStartTime;
	}

	public void setAssessementSecondHalfStartTime(Date assessementSecondHalfStartTime) {
		this.assessementSecondHalfStartTime = assessementSecondHalfStartTime;
	}

	public Date getAssessementFinishTime() {
		return assessementFinishTime;
	}

	public void setAssessementFinishTime(Date assessementFinishTime) {
		this.assessementFinishTime = assessementFinishTime;
	}

	public Date getAssessementFirstHalfEndTime() {
		return assessementFirstHalfEndTime;
	}

	public void setAssessementFirstHalfEndTime(Date assessementFirstHalfEndTime) {
		this.assessementFirstHalfEndTime = assessementFirstHalfEndTime;
	}

	public boolean isAssessementFirstHalfStart() {
		return assessementFirstHalfStart;
	}

	public void setAssessementFirstHalfStart(boolean assessementFirstHalfStart) {
		this.assessementFirstHalfStart = assessementFirstHalfStart;
	}

	public boolean isAssessementSecondHalfStart() {
		return assessementSecondHalfStart;
	}

	public void setAssessementSecondHalfStart(boolean assessementSecondHalfStart) {
		this.assessementSecondHalfStart = assessementSecondHalfStart;
	}

	public String getLocationSecond() {
		return locationSecond;
	}

	public void setLocationSecond(String locationSecond) {
		this.locationSecond = locationSecond;
	}

	public UserAdmin getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(UserAdmin userAdmin) {
		this.userAdmin = userAdmin;
	}

	public boolean isInprogress() {
		return inprogress;
	}

	public void setInprogress(boolean inprogress) {
		this.inprogress = inprogress;
	}
}
