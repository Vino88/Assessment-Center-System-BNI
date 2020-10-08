package com.dlabs.acs.dto.cap;

public class CompetencyLibraryCapDto {
	private String competencyName;
	private int number;
	public String getCompetencyName() {
		return competencyName;
	}
	public void setCompetencyName(String competencyName) {
		this.competencyName = competencyName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "CompetencyLibaryCapDto [competencyName=" + competencyName + ", number=" + number + "]";
	}
	
	
}
