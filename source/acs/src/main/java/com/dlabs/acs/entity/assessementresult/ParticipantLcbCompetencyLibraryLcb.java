package com.dlabs.acs.entity.assessementresult;

import javax.persistence.Basic;
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
import com.dlabs.acs.entity.assessement.ParticipantLcb;
import com.dlabs.acs.entity.lcb.CompetencyLibraryLcb;

@NamedQueries({
	@NamedQuery(name="ParticipantLcbCompetencyLibraryLcb.getBySearch", query="FROM ParticipantLcbCompetencyLibraryLcb where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="ParticipantLcbCompetencyLibraryLcb.countBySearch", query="select count(*) FROM ParticipantLcbCompetencyLibraryLcb where LOWER(id) like :search")
})
@Entity
public class ParticipantLcbCompetencyLibraryLcb extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;

	@JoinColumn
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	private ParticipantLcb ParticipantLcb;
	
	@ManyToOne
    @JoinColumn()
	private CompetencyLibraryLcb competencyLibraryLcb;

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

	public ParticipantLcb getParticipantLcb() {
		return ParticipantLcb;
	}

	public void setParticipantLcb(ParticipantLcb participantLcb) {
		ParticipantLcb = participantLcb;
	}

	public CompetencyLibraryLcb getCompetencyLibraryLcb() {
		return competencyLibraryLcb;
	}

	public void setCompetencyLibraryLcb(CompetencyLibraryLcb competencyLibraryLcb) {
		this.competencyLibraryLcb = competencyLibraryLcb;
	}
}
