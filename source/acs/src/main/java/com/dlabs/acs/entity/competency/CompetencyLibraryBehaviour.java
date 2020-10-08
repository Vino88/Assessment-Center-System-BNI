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
	@NamedQuery(name="CompetencyLibraryBehaviour.getBySearch", query="FROM CompetencyLibraryBehaviour where commonFields.deleted = false AND (LOWER(behaviour) like :search OR LOWER(competencyLibrary.competencyName) like :search )order by id asc"),
	@NamedQuery(name="CompetencyLibraryBehaviour.countBySearch", query="select count(*) FROM CompetencyLibraryBehaviour where commonFields.deleted = false AND (LOWER(behaviour) like :search OR LOWER(competencyLibrary.competencyName) like :search)"),
	
	
	@NamedQuery(name="CompetencyLibraryBehaviour.getByCompetencyLibraryId", query="FROM CompetencyLibraryBehaviour where commonFields.deleted = false AND competencyLibrary.id = :competencyLibraryId order by behaviourLevel asc"),
})
@Entity
public class CompetencyLibraryBehaviour extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	private int behaviourLevel;
	@Column(length=20000)
	private String behaviour;
	
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

	public int getBehaviourLevel() {
		return behaviourLevel;
	}

	public void setBehaviourLevel(int behaviourLevel) {
		this.behaviourLevel = behaviourLevel;
	}

	public String getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	public CompetencyLibrary getCompetencyLibrary() {
		return competencyLibrary;
	}

	public void setCompetencyLibrary(CompetencyLibrary competencyLibrary) {
		this.competencyLibrary = competencyLibrary;
	}
}
