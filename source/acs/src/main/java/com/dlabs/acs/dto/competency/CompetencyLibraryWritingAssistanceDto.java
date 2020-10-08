package com.dlabs.acs.dto.competency;

import com.dlabs.acs.dto.DataFormDto;

public class CompetencyLibraryWritingAssistanceDto extends DataFormDto{
	private String description;
	private int level;
	private Long competencyLibraryId;
	private String competencyName;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Long getCompetencyLibraryId() {
		return competencyLibraryId;
	}
	public void setCompetencyLibraryId(Long competencyLibraryId) {
		this.competencyLibraryId = competencyLibraryId;
	}
	public String getCompetencyName() {
		return competencyName;
	}
	public void setCompetencyName(String competencyName) {
		this.competencyName = competencyName;
	}
	@Override
	public String toString() {
		return "CompetencyLibraryWritingAssistanceDto [description=" + description + ", level=" + level
				+ ", competencyLibraryId=" + competencyLibraryId + ", competencyName=" + competencyName + "]";
	}
	
	
}