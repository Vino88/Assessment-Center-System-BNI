package com.dlabs.acs.entity.assessement;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;
import com.dlabs.acs.entity.analysys.CompetencyLibraryAnalysys;

@NamedQueries({
	@NamedQuery(name="ParticipantCompetencyLibraryAnalysys.getBySearch", query="FROM ParticipantCompetencyLibraryAnalysys where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="ParticipantCompetencyLibraryAnalysys.countBySearch", query="select count(*) FROM ParticipantCompetencyLibraryAnalysys where LOWER(id) like :search"),
	
	@NamedQuery(name="ParticipantCompetencyLibraryAnalysys.getByPartIdAndClaId", query="FROM ParticipantCompetencyLibraryAnalysys where participant.id= :participantId AND competencyLibraryAnalysys.id = :claId"),
})
@Entity
public class ParticipantCompetencyLibraryAnalysys extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Column(length = 20000)
	private String answer;
	
	@JoinColumn
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	private Participant participant;
	
	@JoinColumn
	@ManyToOne
	private CompetencyLibraryAnalysys competencyLibraryAnalysys;


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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public CompetencyLibraryAnalysys getCompetencyLibraryAnalysys() {
		return competencyLibraryAnalysys;
	}

	public void setCompetencyLibraryAnalysys(CompetencyLibraryAnalysys competencyLibraryAnalysys) {
		this.competencyLibraryAnalysys = competencyLibraryAnalysys;
	}
}
