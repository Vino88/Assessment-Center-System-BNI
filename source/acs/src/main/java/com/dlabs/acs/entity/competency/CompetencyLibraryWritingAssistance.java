package com.dlabs.acs.entity.competency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;

@NamedQueries({
	@NamedQuery(name="CompetencyLibraryWritingAssistance.getBySearch", query="FROM CompetencyLibraryWritingAssistance where commonFields.deleted = false AND (LOWER(description) like :search OR LOWER(competencyLibrary.competencyName) like :search) order by competencyLibrary.id asc, level asc"),
	@NamedQuery(name="CompetencyLibraryWritingAssistance.countBySearch", query="select count(*) FROM CompetencyLibraryWritingAssistance where commonFields.deleted = false AND (LOWER(description) like :search OR LOWER(competencyLibrary.competencyName) like :search)"),
	
	
	
	@NamedQuery(name="CompetencyLibraryWritingAssistance.countByCompetencyLibraryId", query="select count(*) FROM CompetencyLibraryWritingAssistance where commonFields.deleted = false AND competencyLibrary.id = :competencyLibraryId "),
	@NamedQuery(name="CompetencyLibraryWritingAssistance.countByLevel", query="select count(*) FROM CompetencyLibraryWritingAssistance where commonFields.deleted = false AND level = :level"),
	@NamedQuery(name="CompetencyLibraryWritingAssistance.countByCompetencyLibraryIdAndLevel", query="select count(*) FROM CompetencyLibraryWritingAssistance where commonFields.deleted = false AND competencyLibrary.id = :competencyLibraryId AND level = :level"),
	
	
	@NamedQuery(name="CompetencyLibraryWritingAssistance.getByCompetencyLibraryId", query="FROM CompetencyLibraryWritingAssistance where commonFields.deleted = false AND competencyLibrary.id = :competencyLibraryId order by competencyLibrary.id asc, level asc"),
	@NamedQuery(name="CompetencyLibraryWritingAssistance.getByLevel", query="FROM CompetencyLibraryWritingAssistance where commonFields.deleted = false AND level = :level order by competencyLibrary.id asc, level asc"),
	@NamedQuery(name="CompetencyLibraryWritingAssistance.getByCompetencyLibraryIdAndLevel", query="FROM CompetencyLibraryWritingAssistance where commonFields.deleted = false AND competencyLibrary.id = :competencyLibraryId AND level = :level order by competencyLibrary.id asc, level asc"),
})
@Entity
public class CompetencyLibraryWritingAssistance extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Column(nullable = false, length=20000)
	private String description;
	
	@Column(nullable = false)
	private int level;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public CompetencyLibrary getCompetencyLibrary() {
		return competencyLibrary;
	}

	public void setCompetencyLibrary(CompetencyLibrary competencyLibrary) {
		this.competencyLibrary = competencyLibrary;
	}
}
