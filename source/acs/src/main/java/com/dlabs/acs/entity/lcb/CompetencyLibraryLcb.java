package com.dlabs.acs.entity.lcb;

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
	@NamedQuery(name="CompetencyLibraryLcb.getBySearch", query="FROM CompetencyLibraryLcb where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="CompetencyLibraryLcb.countBySearch", query="select count(*) FROM CompetencyLibraryLcb where LOWER(id) like :search"),
	
	@NamedQuery(name="CompetencyLibraryLcb.getByCompetencyIdAndLcbId", query="FROM CompetencyLibraryLcb where competencyLibrary.id = :competencyId AND lcb.id=:lcbId"),
})
@Entity
public class CompetencyLibraryLcb extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@ManyToOne
    @JoinColumn()
	private Lcb lcb;

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

	public Lcb getLcb() {
		return lcb;
	}

	public void setLcb(Lcb lcb) {
		this.lcb = lcb;
	}

	public CompetencyLibrary getCompetencyLibrary() {
		return competencyLibrary;
	}

	public void setCompetencyLibrary(CompetencyLibrary competencyLibrary) {
		this.competencyLibrary = competencyLibrary;
	}
}
