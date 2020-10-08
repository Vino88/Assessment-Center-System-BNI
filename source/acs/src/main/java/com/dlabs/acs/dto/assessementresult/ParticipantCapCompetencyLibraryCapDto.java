package com.dlabs.acs.dto.assessementresult;

import java.util.List;

import com.dlabs.acs.dto.AbstractDto;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;

public class ParticipantCapCompetencyLibraryCapDto extends AbstractDto{
	private Long id;
	private Cap cap;
	private boolean finished;
	
	private List<CompetencyLibraryBehaviour> competencyLibraryBehaviours;
	private ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult;
	private CompetencyLibrary competencyLibrary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cap getCap() {
		return cap;
	}

	public void setCap(Cap cap) {
		this.cap = cap;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public List<CompetencyLibraryBehaviour> getCompetencyLibraryBehaviours() {
		return competencyLibraryBehaviours;
	}

	public void setCompetencyLibraryBehaviours(List<CompetencyLibraryBehaviour> competencyLibraryBehaviours) {
		this.competencyLibraryBehaviours = competencyLibraryBehaviours;
	}

	public ParticipantCompetencyLibraryFinalResult getParticipantCompetencyLibraryFinalResult() {
		return participantCompetencyLibraryFinalResult;
	}

	public void setParticipantCompetencyLibraryFinalResult(
			ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult) {
		this.participantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResult;
	}

	public CompetencyLibrary getCompetencyLibrary() {
		return competencyLibrary;
	}

	public void setCompetencyLibrary(CompetencyLibrary competencyLibrary) {
		this.competencyLibrary = competencyLibrary;
	}	
}