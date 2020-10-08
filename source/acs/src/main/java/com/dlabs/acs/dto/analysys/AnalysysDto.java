package com.dlabs.acs.dto.analysys;

import com.dlabs.acs.dto.DataFormDto;
import com.dlabs.acs.entity.analysys.AnalysysType;

public class AnalysysDto extends DataFormDto{
	private int number;
	private String title;
	private AnalysysType analysysType;
	private String question;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public AnalysysType getAnalysysType() {
		return analysysType;
	}
	public void setAnalysysType(AnalysysType analysysType) {
		this.analysysType = analysysType;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "AnalysysDto [number=" + number + ", title=" + title + ", analysysType=" + analysysType + ", question="
				+ question + "]";
	}
	
	
}