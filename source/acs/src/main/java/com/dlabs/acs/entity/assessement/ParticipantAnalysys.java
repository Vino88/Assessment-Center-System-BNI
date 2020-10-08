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
import com.dlabs.acs.entity.analysys.Analysys;

@NamedQueries({
	@NamedQuery(name="ParticipantAnalysys.getBySearch", query="FROM ParticipantAnalysys where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="ParticipantAnalysys.countBySearch", query="select count(*) FROM ParticipantAnalysys where LOWER(id) like :search"),
	
	@NamedQuery(name="ParticipantAnalysys.getByPartIdAndAnalysysId", query="FROM ParticipantAnalysys where participant.id= :participantId AND analysys.id = :claId"),
})
@Entity
public class ParticipantAnalysys extends AbstractEntity{
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
	private Analysys analysys;


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

	public Analysys getAnalysys() {
		return analysys;
	}

	public void setAnalysys(Analysys analysys) {
		this.analysys = analysys;
	}

}
