package com.dlabs.acs.dto.cap;

import com.dlabs.acs.dto.DataFormDto;

public class CapDto extends DataFormDto{
	private int number;
	private String question;
	
	private String qSituation;
	private String qAction;
	private String qResult;
	
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
	public String getqSituation() {
		return qSituation;
	}
	public void setqSituation(String qSituation) {
		this.qSituation = qSituation;
	}
	public String getqAction() {
		return qAction;
	}
	public void setqAction(String qAction) {
		this.qAction = qAction;
	}
	public String getqResult() {
		return qResult;
	}
	public void setqResult(String qResult) {
		this.qResult = qResult;
	}
}