package com.dlabs.acs.dto.inbasket;

public class InbasketInboxInbasketQuestionDto {
	private int inbasketInboxQuestionNumber;
	private int inbasketQuestionQuestionNumber;
	private String competencyName;
	
	public int getInbasketInboxQuestionNumber() {
		return inbasketInboxQuestionNumber;
	}
	public void setInbasketInboxQuestionNumber(int inbasketInboxQuestionNumber) {
		this.inbasketInboxQuestionNumber = inbasketInboxQuestionNumber;
	}
	public int getInbasketQuestionQuestionNumber() {
		return inbasketQuestionQuestionNumber;
	}
	public void setInbasketQuestionQuestionNumber(int inbasketQuestionQuestionNumber) {
		this.inbasketQuestionQuestionNumber = inbasketQuestionQuestionNumber;
	}
	public String getCompetencyName() {
		return competencyName;
	}
	public void setCompetencyName(String competencyName) {
		this.competencyName = competencyName;
	}
	@Override
	public String toString() {
		return "InbasketInboxInbasketQuestionDto [inbasketInboxQuestionNumber=" + inbasketInboxQuestionNumber
				+ ", inbasketQuestionQuestionNumber=" + inbasketQuestionQuestionNumber + "]";
	}
	
	
}
