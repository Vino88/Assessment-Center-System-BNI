package com.dlabs.acs.dto.competency;

import com.dlabs.acs.dto.DataFormDto;

public class CompetencyLibraryBehaviourDto extends DataFormDto{
	private int behaviourLevel;
	private String behaviour;
	private Long competencyLibraryId;
	private String competencyName;
	
	public int getBehaviourLevel() {
		return behaviourLevel;
	}
	public void setBehaviourLevel(int behaviourLevel) {
		this.behaviourLevel = behaviourLevel;
	}
	public String getBehaviour() {
		return behaviour;
	}
	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
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
		return "CompetencyLibraryBehaviourDto [behaviourLevel=" + behaviourLevel + ", behaviour=" + behaviour
				+ ", competencyLibraryId=" + competencyLibraryId + ", competencyName=" + competencyName + "]";
	}
	
}