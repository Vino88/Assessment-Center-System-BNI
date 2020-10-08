package com.dlabs.acs.entity.participant;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.assessement.Participant;

@NamedQueries({
	@NamedQuery(name="ParticipantLogPost.getBySearch", query="FROM ParticipantLogPost where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="ParticipantLogPost.countBySearch", query="select count(*) FROM ParticipantLogPost where LOWER(id) like :search"),
	
	
	@NamedQuery(name="ParticipantLogPost.countParticipantAssignToMe", query="select count(pl.participant.id) FROM ParticipantLogPost pl where (pl.participant.participantStatus = 'ASSIGNED_TO_ASSESSOR' OR pl.participant.participantStatus = 'PARTIAL_RATING') AND ((pl.capAssessor.id = :userAdminId AND assessCapFinishTime is null) OR (pl.simulationAssessor.id = :userAdminId AND assessSimulationFinishTime is null) OR (pl.analysysAssessor.id = :userAdminId AND assessAnalysisFinishTime is null) OR (pl.integrationGridAssessor.id = :userAdminId AND assessIntegrationGridTime is null) OR (pl.reviewer.id = :userAdminId AND assessReviewerFinishTime is null)) AND ( LOWER(pl.participant.nik) like :search OR LOWER(pl.participant.fullName) like :search ) "),
	@NamedQuery(name="ParticipantLogPost.getParticipantAssignToMe", query="select pl.participant FROM ParticipantLogPost pl where (pl.participant.participantStatus = 'ASSIGNED_TO_ASSESSOR' OR pl.participant.participantStatus = 'PARTIAL_RATING') AND ((pl.capAssessor.id = :userAdminId AND assessCapFinishTime is null) OR (pl.simulationAssessor.id = :userAdminId AND assessSimulationFinishTime is null) OR (pl.analysysAssessor.id = :userAdminId AND assessAnalysisFinishTime is null) OR (pl.integrationGridAssessor.id = :userAdminId AND assessIntegrationGridTime is null) OR (pl.reviewer.id = :userAdminId AND assessReviewerFinishTime is null)) AND ( LOWER(pl.participant.nik) like :search OR LOWER(pl.participant.fullName) like :search )  ORDER BY pl.assessStartTime ASC"),
	
	@NamedQuery(name="ParticipantLogPost.countParticipantHistory", query="select count(pl.participant.id) FROM ParticipantLogPost pl where pl.participant.participantStatus = 'ASSESSEMENT_COMPLETE' AND (pl.participant.batch.description like :search OR pl.reviewer.fullname like :search) "),
	@NamedQuery(name="ParticipantLogPost.getParticipantHistory", query="select pl.participant.id as participantId, pl.participant.batch.description as batchDescription, pl.assessFinishTime as reviewerFinishTime, pl.reviewer.fullname as reviewerName FROM ParticipantLogPost pl where pl.participant.participantStatus = 'ASSESSEMENT_COMPLETE' AND (pl.participant.batch.description like :search OR pl.reviewer.fullname like :search)  ORDER BY pl.participant.batch.id ASC, pl.assessStartTime ASC")
})

@NamedNativeQueries({
	@NamedNativeQuery(name="ParticipantLogPost.countAssessorDetail", query="SELECT count(*) FROM vw_assessor_task_detail v "),
	@NamedNativeQuery(name="ParticipantLogPost.getAssessorDetail", query="SELECT * FROM vw_assessor_task_detail v ORDER BY v.assessementFinishTime DESC, assessorName ASC"),
	
	@NamedNativeQuery(name="ParticipantLogPost.countAssessorDetailByAssessorId", query="SELECT count(*) FROM vw_assessor_task_detail v where v.assessorId IN (:assessorIds)"),
	@NamedNativeQuery(name="ParticipantLogPost.getAssessorDetailByAssessorId", query="SELECT * FROM vw_assessor_task_detail v where v.assessorId IN (:assessorIds) ORDER BY v.assessementFinishTime DESC, assessorName ASC"),
	
	@NamedNativeQuery(name="ParticipantLogPost.countAssessorDetailByBatchTime", query="SELECT count(*) FROM vw_assessor_task_detail v where (assessementSecondHalfStartTime BETWEEN :startTime AND :endTime) "),
	@NamedNativeQuery(name="ParticipantLogPost.getAssessorDetailByBatchTime", query="SELECT * FROM vw_assessor_task_detail v where (assessementSecondHalfStartTime BETWEEN :startTime AND :endTime) ORDER BY v.assessementFinishTime DESC, assessorName ASC"),
	
	@NamedNativeQuery(name="ParticipantLogPost.countAssessorDetailByAssessorIdAndBatchTime", query="SELECT count(*) FROM vw_assessor_task_detail v where (v.assessorId IN (:assessorIds)) AND (assessementSecondHalfStartTime BETWEEN :startTime AND :endTime)"),
	@NamedNativeQuery(name="ParticipantLogPost.getAssessorDetailByAssessorIdAndBatchTime", query="SELECT * FROM vw_assessor_task_detail v where (v.assessorId IN (:assessorIds)) AND (assessementSecondHalfStartTime BETWEEN :startTime AND :endTime) ORDER BY v.assessementFinishTime DESC, assessorName ASC"),
	
	
	
	
	@NamedNativeQuery(name="ParticipantLogPost.countAssessorDetailByDone", query="SELECT count(*) FROM vw_assessor_task_detail v where v.assessDone = :assessDone"),
	@NamedNativeQuery(name="ParticipantLogPost.getAssessorDetailByDone", query="SELECT * FROM vw_assessor_task_detail v where v.assessDone = :assessDone ORDER BY v.assessementFinishTime DESC, assessorName ASC"),
	
	@NamedNativeQuery(name="ParticipantLogPost.countAssessorDetailByAssessorIdByDone", query="SELECT count(*) FROM vw_assessor_task_detail v where (v.assessorId IN (:assessorIds) ) AND (v.assessDone = :assessDone)"),
	@NamedNativeQuery(name="ParticipantLogPost.getAssessorDetailByAssessorIdByDone", query="SELECT * FROM vw_assessor_task_detail v where (v.assessorId IN (:assessorIds) ) AND (v.assessDone = :assessDone) ORDER BY v.assessementFinishTime DESC, assessorName ASC"),
	
	@NamedNativeQuery(name="ParticipantLogPost.countAssessorDetailByBatchTimeByDone", query="SELECT count(*) FROM vw_assessor_task_detail v where (v.assessementSecondHalfStartTime BETWEEN :startTime AND :endTime) AND (v.assessDone = :assessDone)"),
	@NamedNativeQuery(name="ParticipantLogPost.getAssessorDetailByBatchTimeByDone", query="SELECT * FROM vw_assessor_task_detail v where (v.assessementSecondHalfStartTime BETWEEN :startTime AND :endTime) AND (v.assessDone = :assessDone) ORDER BY v.assessementFinishTime DESC, assessorName ASC"),
	
	
	
	@NamedNativeQuery(name="ParticipantLogPost.countAssessorDetailByAssessorIdAndBatchTimeByDone", query="SELECT count(*) FROM vw_assessor_task_detail v where (v.assessorId IN (:assessorIds)) AND (assessementSecondHalfStartTime BETWEEN :startTime AND :endTime) AND (v.assessDone = :assessDone)"),
	@NamedNativeQuery(name="ParticipantLogPost.getAssessorDetailByAssessorIdAndBatchTimeByDone", query="SELECT * FROM vw_assessor_task_detail v where (v.assessorId IN (:assessorIds)) AND (assessementSecondHalfStartTime BETWEEN :startTime AND :endTime) AND (v.assessDone = :assessDone) ORDER BY v.assessementFinishTime DESC, assessorName ASC"),
	
})
@Entity
public class ParticipantLogPost extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessCapFinishTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessSimulationFinishTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessAnalysisFinishTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessIntegrationGridTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessReviewerFinishTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date assessFinishTime;
	
	
	
	
	@JoinColumn
	@OneToOne
	@Basic(fetch=FetchType.LAZY)
	private Participant participant;
	
	@JoinColumn
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	private UserAdmin capAssessor;
	
	@JoinColumn
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	private UserAdmin simulationAssessor;
	
	@JoinColumn
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	private UserAdmin analysysAssessor;
	
	@JoinColumn
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	private UserAdmin integrationGridAssessor;
	
	@JoinColumn
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	private UserAdmin reviewer;


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

	public Date getAssessStartTime() {
		return assessStartTime;
	}

	public void setAssessStartTime(Date assessStartTime) {
		this.assessStartTime = assessStartTime;
	}

	public Date getAssessCapFinishTime() {
		return assessCapFinishTime;
	}

	public void setAssessCapFinishTime(Date assessCapFinishTime) {
		this.assessCapFinishTime = assessCapFinishTime;
	}

	public Date getAssessSimulationFinishTime() {
		return assessSimulationFinishTime;
	}

	public void setAssessSimulationFinishTime(Date assessSimulationFinishTime) {
		this.assessSimulationFinishTime = assessSimulationFinishTime;
	}

	public Date getAssessAnalysisFinishTime() {
		return assessAnalysisFinishTime;
	}

	public void setAssessAnalysisFinishTime(Date assessAnalysisFinishTime) {
		this.assessAnalysisFinishTime = assessAnalysisFinishTime;
	}

	public Date getAssessIntegrationGridTime() {
		return assessIntegrationGridTime;
	}

	public void setAssessIntegrationGridTime(Date assessIntegrationGridTime) {
		this.assessIntegrationGridTime = assessIntegrationGridTime;
	}

	public Date getAssessFinishTime() {
		return assessFinishTime;
	}

	public void setAssessFinishTime(Date assessFinishTime) {
		this.assessFinishTime = assessFinishTime;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public UserAdmin getCapAssessor() {
		return capAssessor;
	}

	public void setCapAssessor(UserAdmin capAssessor) {
		this.capAssessor = capAssessor;
	}

	public UserAdmin getSimulationAssessor() {
		return simulationAssessor;
	}

	public void setSimulationAssessor(UserAdmin simulationAssessor) {
		this.simulationAssessor = simulationAssessor;
	}

	public UserAdmin getAnalysysAssessor() {
		return analysysAssessor;
	}

	public void setAnalysysAssessor(UserAdmin analysysAssessor) {
		this.analysysAssessor = analysysAssessor;
	}

	public UserAdmin getIntegrationGridAssessor() {
		return integrationGridAssessor;
	}

	public void setIntegrationGridAssessor(UserAdmin integrationGridAssessor) {
		this.integrationGridAssessor = integrationGridAssessor;
	}

	public Date getAssessReviewerFinishTime() {
		return assessReviewerFinishTime;
	}

	public void setAssessReviewerFinishTime(Date assessReviewerFinishTime) {
		this.assessReviewerFinishTime = assessReviewerFinishTime;
	}

	public UserAdmin getReviewer() {
		return reviewer;
	}

	public void setReviewer(UserAdmin reviewer) {
		this.reviewer = reviewer;
	}
}
