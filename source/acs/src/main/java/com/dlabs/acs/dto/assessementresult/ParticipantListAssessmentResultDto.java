package com.dlabs.acs.dto.assessementresult;

import java.util.List;

import javax.validation.constraints.NotNull;

public class ParticipantListAssessmentResultDto {
	@NotNull
	private Long participantId;
	@NotNull
	private Long simulationId;
	
	private List<ParticipantAssessmentResultDto> listResult;

	public Long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}

	public Long getSimulationId() {
		return simulationId;
	}

	public void setSimulationId(Long simulationId) {
		this.simulationId = simulationId;
	}

	public List<ParticipantAssessmentResultDto> getListResult() {
		return listResult;
	}

	public void setListResult(List<ParticipantAssessmentResultDto> listResult) {
		this.listResult = listResult;
	}
	
	
}
