package com.dlabs.acs.dto.assessement;

public class ParticipantCapDto {
	private Long capId;
	private Long participantCapId;
	
	private String ansSituation;
	private String ansAction;
	private String ansResult;

	public Long getCapId() {
		return capId;
	}

	public void setCapId(Long capId) {
		this.capId = capId;
	}

	public Long getParticipantCapId() {
		return participantCapId;
	}

	public void setParticipantCapId(Long participantCapId) {
		this.participantCapId = participantCapId;
	}

	public String getAnsSituation() {
		return ansSituation;
	}

	public void setAnsSituation(String ansSituation) {
		this.ansSituation = ansSituation;
	}

	public String getAnsAction() {
		return ansAction;
	}

	public void setAnsAction(String ansAction) {
		this.ansAction = ansAction;
	}

	public String getAnsResult() {
		return ansResult;
	}

	public void setAnsResult(String ansResult) {
		this.ansResult = ansResult;
	}
}
