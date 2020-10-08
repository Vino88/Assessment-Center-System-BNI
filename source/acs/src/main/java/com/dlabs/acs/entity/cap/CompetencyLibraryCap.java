package com.dlabs.acs.entity.cap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;
import com.dlabs.acs.entity.competency.CompetencyLibrary;

@NamedQueries({
	@NamedQuery(name="CompetencyLibraryCap.getBySearch", query="FROM CompetencyLibraryCap where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="CompetencyLibraryCap.countBySearch", query="select count(*) FROM CompetencyLibraryCap where LOWER(id) like :search"),
	
	@NamedQuery(name="CompetencyLibraryCap.getDistinctCompetencyLibrary", query="select distinct(c.competencyLibrary) FROM CompetencyLibraryCap c  order by c.competencyLibrary.id asc"),
	@NamedQuery(name="CompetencyLibraryCap.getCapByCompetencyId", query="select c.cap FROM CompetencyLibraryCap c WHERE c.competencyLibrary.id = :competencyLibraryId order by c.cap.id asc"),
	
	@NamedQuery(name="CompetencyLibraryCap.getCompetenciesByCapId", query="select c.competencyLibrary FROM CompetencyLibraryCap c WHERE c.cap.id = :capId order by c.competencyLibrary.id asc"),
})
@Entity
public class CompetencyLibraryCap extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@ManyToOne
    @JoinColumn()
	private Cap cap;

	@ManyToOne
    @JoinColumn()
	private CompetencyLibrary competencyLibrary;


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

	public Cap getCap() {
		return cap;
	}

	public void setCap(Cap cap) {
		this.cap = cap;
	}

	public CompetencyLibrary getCompetencyLibrary() {
		return competencyLibrary;
	}

	public void setCompetencyLibrary(CompetencyLibrary competencyLibrary) {
		this.competencyLibrary = competencyLibrary;
	}
}
