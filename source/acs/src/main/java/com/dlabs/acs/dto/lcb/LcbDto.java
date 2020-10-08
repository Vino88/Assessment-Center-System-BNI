package com.dlabs.acs.dto.lcb;

public class LcbDto {
	private Long id;
	
	private String question;
	
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;
	private String choiceE;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getChoiceA() {
		return choiceA;
	}
	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}
	public String getChoiceB() {
		return choiceB;
	}
	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}
	public String getChoiceC() {
		return choiceC;
	}
	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}
	public String getChoiceD() {
		return choiceD;
	}
	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}
	public String getChoiceE() {
		return choiceE;
	}
	public void setChoiceE(String choiceE) {
		this.choiceE = choiceE;
	}
}
