package com.dlabs.acs.dto.assessement;

public class ParticipantAnalysysDto {
	private Long analysysId;
	private Long participantAnalysysId;
	private String answer;
	private Long prevId;
	private Long nextId;
	
	public Long getAnalysysId() {
		return analysysId;
	}
	public void setAnalysysId(Long analysysId) {
		this.analysysId = analysysId;
	}
	public Long getParticipantAnalysysId() {
		return participantAnalysysId;
	}
	public void setParticipantAnalysysId(Long participantAnalysysId) {
		this.participantAnalysysId = participantAnalysysId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Long getPrevId() {
		return prevId;
	}
	public void setPrevId(Long prevId) {
		this.prevId = prevId;
	}
	public Long getNextId() {
		return nextId;
	}
	public void setNextId(Long nextId) {
		this.nextId = nextId;
	}
	
}
