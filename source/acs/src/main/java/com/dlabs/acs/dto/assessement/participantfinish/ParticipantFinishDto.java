package com.dlabs.acs.dto.assessement.participantfinish;

import javax.validation.constraints.NotNull;

public class ParticipantFinishDto {
	@NotNull
	private Long participantId;
	
	
	private Long capAssessorUserId;
	
	private Long simulationAssessorUserId;
	
	private Long analysysAssessorUserId;
	
	private Long integrationGridAssessorUserId;
	
	private Long reviewerAssessorUserId;
	
	public Long getParticipantId() {
		return participantId;
	}
	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}
	public Long getCapAssessorUserId() {
		return capAssessorUserId;
	}
	public void setCapAssessorUserId(Long capAssessorUserId) {
		this.capAssessorUserId = capAssessorUserId;
	}
	public Long getSimulationAssessorUserId() {
		return simulationAssessorUserId;
	}
	public void setSimulationAssessorUserId(Long simulationAssessorUserId) {
		this.simulationAssessorUserId = simulationAssessorUserId;
	}
	public Long getAnalysysAssessorUserId() {
		return analysysAssessorUserId;
	}
	public void setAnalysysAssessorUserId(Long analysysAssessorUserId) {
		this.analysysAssessorUserId = analysysAssessorUserId;
	}
	public Long getIntegrationGridAssessorUserId() {
		return integrationGridAssessorUserId;
	}
	public void setIntegrationGridAssessorUserId(Long integrationGridAssessorUserId) {
		this.integrationGridAssessorUserId = integrationGridAssessorUserId;
	}
	public Long getReviewerAssessorUserId() {
		return reviewerAssessorUserId;
	}
	public void setReviewerAssessorUserId(Long reviewerAssessorUserId) {
		this.reviewerAssessorUserId = reviewerAssessorUserId;
	}
	
	
}
