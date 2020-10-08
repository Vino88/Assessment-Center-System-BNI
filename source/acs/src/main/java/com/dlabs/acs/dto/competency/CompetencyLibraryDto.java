package com.dlabs.acs.dto.competency;

import javax.validation.constraints.Size;

import com.dlabs.acs.dto.DataFormDto;

public class CompetencyLibraryDto extends DataFormDto{
	@Size(min=1, max=255)
	private String category;
	@Size(min=1, max=255)
	private String competencyName;
	@Size(max=255)
	private String competencyNameBahasa;
	
	private String competencyDescription;
	
	private double displayOrder;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCompetencyName() {
		return competencyName;
	}

	public void setCompetencyName(String competencyName) {
		this.competencyName = competencyName;
	}

	public String getCompetencyNameBahasa() {
		return competencyNameBahasa;
	}

	public void setCompetencyNameBahasa(String competencyNameBahasa) {
		this.competencyNameBahasa = competencyNameBahasa;
	}

	public String getCompetencyDescription() {
		return competencyDescription;
	}

	public void setCompetencyDescription(String competencyDescription) {
		this.competencyDescription = competencyDescription;
	}

	public double getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(double displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	
}
