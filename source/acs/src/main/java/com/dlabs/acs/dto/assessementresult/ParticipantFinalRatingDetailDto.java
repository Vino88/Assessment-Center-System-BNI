package com.dlabs.acs.dto.assessementresult;

public class ParticipantFinalRatingDetailDto {
	private Long competencyId;
	private Integer capRating;
	private Integer inbasketRating;
	private Integer analysisRating;
	private Integer finalRating;
	private Integer otherRating;
	
	public Long getCompetencyId() {
		return competencyId;
	}
	public void setCompetencyId(Long competencyId) {
		this.competencyId = competencyId;
	}
	public Integer getCapRating() {
		return capRating;
	}
	public void setCapRating(Integer capRating) {
		this.capRating = capRating;
	}
	public Integer getInbasketRating() {
		return inbasketRating;
	}
	public void setInbasketRating(Integer inbasketRating) {
		this.inbasketRating = inbasketRating;
	}
	public Integer getAnalysisRating() {
		return analysisRating;
	}
	public void setAnalysisRating(Integer analysisRating) {
		this.analysisRating = analysisRating;
	}
	public Integer getFinalRating() {
		return finalRating;
	}
	public void setFinalRating(Integer finalRating) {
		this.finalRating = finalRating;
	}
	public Integer getOtherRating() {
		return otherRating;
	}
	public void setOtherRating(Integer otherRating) {
		this.otherRating = otherRating;
	}
	
}
