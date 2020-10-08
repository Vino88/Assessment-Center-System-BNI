package com.dlabs.acs.dto.assessement;

public class ParticipantInbasketDto {
	private Long iiqId;
	private Long participantIiqId;
	
	private String answer;

	public Long getIiqId() {
		return iiqId;
	}

	public void setIiqId(Long iiqId) {
		this.iiqId = iiqId;
	}

	public Long getParticipantIiqId() {
		return participantIiqId;
	}

	public void setParticipantIiqId(Long participantIiqId) {
		this.participantIiqId = participantIiqId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
