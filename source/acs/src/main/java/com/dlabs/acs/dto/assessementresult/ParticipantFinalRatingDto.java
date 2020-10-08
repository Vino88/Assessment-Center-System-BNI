package com.dlabs.acs.dto.assessementresult;

import java.util.List;

public class ParticipantFinalRatingDto {
	private List<ParticipantFinalRatingDetailDto> igRating;
	
	private Integer assessmentRating;
	private String strong;
	private String weakness;
	private String personalCompetency;
	private String leadershipCompetency;
	private String functionalCompetency;

	public List<ParticipantFinalRatingDetailDto> getIgRating() {
		return igRating;
	}

	public void setIgRating(List<ParticipantFinalRatingDetailDto> igRating) {
		this.igRating = igRating;
	}

	public Integer getAssessmentRating() {
		return assessmentRating;
	}

	public void setAssessmentRating(Integer assessmentRating) {
		this.assessmentRating = assessmentRating;
	}

	public String getStrong() {
		return strong;
	}

	public void setStrong(String strong) {
		this.strong = strong;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}

	public String getPersonalCompetency() {
		return personalCompetency;
	}

	public void setPersonalCompetency(String personalCompetency) {
		this.personalCompetency = personalCompetency;
	}

	public String getLeadershipCompetency() {
		return leadershipCompetency;
	}

	public void setLeadershipCompetency(String leadershipCompetency) {
		this.leadershipCompetency = leadershipCompetency;
	}

	public String getFunctionalCompetency() {
		return functionalCompetency;
	}

	public void setFunctionalCompetency(String functionalCompetency) {
		this.functionalCompetency = functionalCompetency;
	}
	
}
