package com.dlabs.acs.dto.lcb;

import com.dlabs.acs.dto.AbstractDto;

public class CompetencyLibraryLcbDto extends AbstractDto{
	private Integer questionNumber;
	private String competencyName;
	
	public Integer getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getCompetencyName() {
		return competencyName;
	}
	public void setCompetencyName(String competencyName) {
		this.competencyName = competencyName;
	}
	
	@Override
	public String toString() {
		return "CompetencyLibraryLcbDto [questionNumber=" + questionNumber + ", competencyName=" + competencyName + "]";
	}
	
	
}
