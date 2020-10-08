package com.dlabs.acs.dto.assessementresult;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ParticipantAssessmentResultDto {
	@NotNull
	private Long participantId;
	@NotNull
	private Long competencyId;
	
	@NotNull
	@Min(0)
	private Integer rating;
	
	private String notes;
	
	private String asessmentFlag;

	public Long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}

	public Long getCompetencyId() {
		return competencyId;
	}

	public void setCompetencyId(Long competencyId) {
		this.competencyId = competencyId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAsessmentFlag() {
		return asessmentFlag;
	}

	public void setAsessmentFlag(String asessmentFlag) {
		this.asessmentFlag = asessmentFlag;
	}
}
