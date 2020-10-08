package com.dlabs.acs.entity.assessement;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;
import com.dlabs.acs.entity.assessement.enumeration.DisabledReason;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.entity.participant.Batch;
import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedQueries({
	@NamedQuery(name="Participant.getBySearch", query="FROM Participant where LOWER(nik) like :search OR LOWER(fullName) like :search order by id asc"),
	@NamedQuery(name="Participant.countBySearch", query="select count(*) FROM Participant where LOWER(nik) like :search OR LOWER(fullName) like :search"),
	
	
	@NamedQuery(name="Participant.getAllBySearch", query="select p.id as id, p.nik as nik, p.password as password, p.fullName as fullName, p.commonFields.createdDate as createdDate, p.participantStatus as participantStatus, p.participantLogDuring.logDuringStatus as logDuringStatus, p.batch.description as batchDescription FROM Participant p LEFT JOIN  p.participantLogDuring LEFT JOIN p.batch where p.commonFields.deleted = false AND (LOWER(p.nik) like :search OR LOWER(p.fullName) like :search) order by p.id asc"),
	@NamedQuery(name="Participant.countAllBySearch", query="select count(*) FROM Participant where commonFields.deleted = false AND (LOWER(nik) like :search OR LOWER(fullName) like :search)"),
	
	@NamedQuery(name="Participant.getByBatchBySearch", query="select p.id as id, p.nik as nik, p.password as password,  p.fullName as fullName, p.commonFields.createdDate as createdDate, p.participantStatus as participantStatus, p.participantLogDuring.logDuringStatus as logDuringStatus, p.batch.description as batchDescription FROM Participant p LEFT JOIN  p.participantLogDuring LEFT JOIN p.batch where p.batch.id = :batchId AND p.commonFields.deleted = false AND (LOWER(p.nik) like :search OR LOWER(p.fullName) like :search) order by p.id asc"),
	@NamedQuery(name="Participant.countByBatchBySearch", query="select count(*) FROM Participant where batch.id = :batchId AND commonFields.deleted = false AND (LOWER(nik) like :search OR LOWER(fullName) like :search)"),
	
	@NamedQuery(name="Participant.login", query="FROM Participant where LOWER(nik) =:nik and password=:password and commonFields.deleted=false and disabledTime is null"),
	
	@NamedQuery(name="Participant.getProfileByParticipantId", query="select p.profile FROM Participant p where p.id=:id"),
	
	@NamedQuery(name="Participant.getByNikAndActive", query="FROM Participant where LOWER(nik) =:nik and commonFields.deleted=false and disabledTime is null"),
	
	@NamedQuery(name="Participant.getBatchByParticipantId", query="select p.batch FROM Participant p where p.id=:id"),
	
	@NamedQuery(name="Participant.countAssessementFinishByBatchId", query="select count(*) FROM Participant p WHERE p.participantStatus <> 'ASSESSEMENT_COMPLETE' AND p.batch.id = :batchId "),
	
	@NamedQuery(name="Participant.countParticipantFinish", query="select count(pl.participant.id) FROM ParticipantLogDuring pl where pl.participant.participantStatus = 'PARTICIPANT_FINISH' AND ( LOWER(pl.participant.nik) like :search OR LOWER(pl.participant.fullName) like :search ) "),
	@NamedQuery(name="Participant.getParticipantFinish", query="select pl.participant FROM ParticipantLogDuring pl where pl.participant.participantStatus = 'PARTICIPANT_FINISH' AND ( LOWER(pl.participant.nik) like :search OR LOWER(pl.participant.fullName) like :search ) ORDER BY pl.answerFinishTime ASC")
})
@Entity
public class Participant extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = false)
	private String email;
	
	/**Credentials **/
	@Column(nullable = false)
	private String nik;
	
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private DisabledReason disabledReason;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date disabledTime;
	
	@Enumerated(EnumType.STRING)
	private ParticipantStatus participantStatus;
	
	private Integer assessmentRating;
	
	@JsonIgnore
	@JoinColumn
	@ManyToOne(fetch=FetchType.LAZY)
	private ParticipantLogDuring participantLogDuring;
	
	@JsonIgnore
	@JoinColumn
	@ManyToOne(fetch=FetchType.LAZY)
	private Profile profile;
	
	@JsonIgnore
	@JoinColumn
	@ManyToOne(fetch=FetchType.LAZY)
	private Employee employee;
	
	@JoinColumn
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	private Batch batch;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DisabledReason getDisabledReason() {
		return disabledReason;
	}

	public void setDisabledReason(DisabledReason disabledReason) {
		this.disabledReason = disabledReason;
	}

	public Date getDisabledTime() {
		return disabledTime;
	}

	public void setDisabledTime(Date disabledTime) {
		this.disabledTime = disabledTime;
	}

	public ParticipantStatus getParticipantStatus() {
		return participantStatus;
	}

	public void setParticipantStatus(ParticipantStatus participantStatus) {
		this.participantStatus = participantStatus;
	}

	public Integer getAssessmentRating() {
		return assessmentRating;
	}

	public void setAssessmentRating(Integer assessmentRating) {
		this.assessmentRating = assessmentRating;
	}

	public ParticipantLogDuring getParticipantLogDuring() {
		return participantLogDuring;
	}

	public void setParticipantLogDuring(ParticipantLogDuring participantLogDuring) {
		this.participantLogDuring = participantLogDuring;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	
}
