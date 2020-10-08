package com.dlabs.acs.entity.analysys;

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
	@NamedQuery(name="CompetencyLibraryAnalysys.getBySearch", query="FROM CompetencyLibraryAnalysys where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="CompetencyLibraryAnalysys.countBySearch", query="select count(*) FROM CompetencyLibraryAnalysys where LOWER(id) like :search"),
	
	@NamedQuery(name="CompetencyLibraryAnalysys.getDistinctCompetencyLibrary", query="select distinct(c.competencyLibrary) FROM CompetencyLibraryAnalysys c"),
	@NamedQuery(name="CompetencyLibraryAnalysys.getAnalysisByCompetencyId", query="select c.analysys FROM CompetencyLibraryAnalysys c WHERE c.competencyLibrary.id = :competencyLibraryId order by c.analysys.id asc"),
})
@Entity
public class CompetencyLibraryAnalysys extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@ManyToOne
    @JoinColumn()
	private Analysys analysys;
	
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

	public Analysys getAnalysys() {
		return analysys;
	}

	public void setAnalysys(Analysys analysys) {
		this.analysys = analysys;
	}

	public CompetencyLibrary getCompetencyLibrary() {
		return competencyLibrary;
	}

	public void setCompetencyLibrary(CompetencyLibrary competencyLibrary) {
		this.competencyLibrary = competencyLibrary;
	}
	
}
