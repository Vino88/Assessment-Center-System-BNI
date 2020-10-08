package com.dlabs.acs.entity.competency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;

@NamedQueries({
	@NamedQuery(name="CompetencyLibrary.getAllActive", query="FROM CompetencyLibrary where commonFields.deleted = false  order by displayOrder asc, id asc"),
	
	@NamedQuery(name="CompetencyLibrary.getBySearch", query="FROM CompetencyLibrary where commonFields.deleted = false AND ( LOWER(category) like :search OR LOWER(competencyName) like :search OR LOWER(competencyNameBahasa) like :search) order by displayOrder asc, id asc"),
	@NamedQuery(name="CompetencyLibrary.countBySearch", query="select count(*) FROM CompetencyLibrary where commonFields.deleted = false AND ( LOWER(category) like :search OR LOWER(competencyName) like :search OR LOWER(competencyNameBahasa) like :search)"),
	
	@NamedQuery(name="CompetencyLibrary.getByCompetencyName", query="FROM CompetencyLibrary where LOWER(competencyName) = :competencyName"),
})
@Entity
public class CompetencyLibrary extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private String competencyName;
	
	@Column(nullable = false)
	private String competencyNameBahasa;
	
	@Column(length=20000)
	private String competencyDescription;
	
	private double displayOrder;

	public double getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(double displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CommonFields getCommonFields() {
		return commonFields;
	}

	public void setCommonFields(CommonFields commonFields) {
		this.commonFields = commonFields;
	}

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

	@Override
	public String toString() {
		return "CompetencyLibrary [id=" + id + ", category=" + category
				+ ", competencyName=" + competencyName + ", competencyNameBahasa=" + competencyNameBahasa
				+ "]";
	}
	
	
}
