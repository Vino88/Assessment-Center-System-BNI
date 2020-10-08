package com.dlabs.acs.dto.assessementresult;

import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.competency.CompetencyLibrary;

public class ParticipantCompetencyResultDisplayDto {
	private CompetencyLibrary competencyLibrary;
	private ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult;
	
	public CompetencyLibrary getCompetencyLibrary() {
		return competencyLibrary;
	}
	public void setCompetencyLibrary(CompetencyLibrary competencyLibrary) {
		this.competencyLibrary = competencyLibrary;
	}
	public ParticipantCompetencyLibraryFinalResult getParticipantCompetencyLibraryFinalResult() {
		return participantCompetencyLibraryFinalResult;
	}
	public void setParticipantCompetencyLibraryFinalResult(
			ParticipantCompetencyLibraryFinalResult participantCompetencyLibraryFinalResult) {
		this.participantCompetencyLibraryFinalResult = participantCompetencyLibraryFinalResult;
	}
	
}
