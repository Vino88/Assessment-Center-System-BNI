package com.dlabs.acs.dto.analysys;

import com.dlabs.acs.dto.DataFormDto;
import com.dlabs.acs.embeddable.CommonFields;

public class CompetencyLibraryAnalysysDto extends DataFormDto{
	private CommonFields commonFields;
	
	private int number;
	
	private String competencyName;
	private Long competencyLibraryId;
	
	public CommonFields getCommonFields() {
		return commonFields;
	}
	public void setCommonFields(CommonFields commonFields) {
		this.commonFields = commonFields;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getCompetencyName() {
		return competencyName;
	}
	public void setCompetencyName(String competencyName) {
		this.competencyName = competencyName;
	}
	public Long getCompetencyLibraryId() {
		return competencyLibraryId;
	}
	public void setCompetencyLibraryId(Long competencyLibraryId) {
		this.competencyLibraryId = competencyLibraryId;
	}
	@Override
	public String toString() {
		return "CompetencyLibraryAnalysysDto [commonFields=" + commonFields + ", number=" + number + ", competencyName="
				+ competencyName + ", competencyLibraryId=" + competencyLibraryId + "]";
	}
	
	
}