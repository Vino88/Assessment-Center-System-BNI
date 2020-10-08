package com.dlabs.acs.dto.inbasket;

import com.dlabs.acs.dto.DataFormDto;

public class InbasketQuestionDto extends DataFormDto{
	private int number;
	private String question;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}