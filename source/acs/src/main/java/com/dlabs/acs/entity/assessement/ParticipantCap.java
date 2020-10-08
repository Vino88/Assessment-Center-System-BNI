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
import com.dlabs.acs.entity.cap.Cap;

@NamedQueries({
	@NamedQuery(name="ParticipantCap.getBySearch", query="FROM ParticipantCap where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="ParticipantCap.countBySearch", query="select count(*) FROM ParticipantCap where LOWER(id) like :search"),
	
	
	@NamedQuery(name="ParticipantCap.getByParticipantIdAndCapId", query="SELECT p FROM ParticipantCap p where p.participant.id=:participantId AND p.cap.id=:capId order by p.id asc"),
})
@Entity
public class ParticipantCap extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Column(length = 20000)
	private String ansSituation;
	@Column(length = 20000)
	private String ansAction;
	@Column(length = 20000)
	private String ansResult;
	
	private String lastTimer;
	
	@JoinColumn
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	private Participant participant;
	
	@JoinColumn
	@ManyToOne
	private Cap cap;


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

	public String getLastTimer() {
		return lastTimer;
	}

	public void setLastTimer(String lastTimer) {
		this.lastTimer = lastTimer;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Cap getCap() {
		return cap;
	}

	public void setCap(Cap cap) {
		this.cap = cap;
	}

	public String getAnsSituation() {
		return ansSituation;
	}

	public void setAnsSituation(String ansSituation) {
		this.ansSituation = ansSituation;
	}

	public String getAnsAction() {
		return ansAction;
	}

	public void setAnsAction(String ansAction) {
		this.ansAction = ansAction;
	}

	public String getAnsResult() {
		return ansResult;
	}

	public void setAnsResult(String ansResult) {
		this.ansResult = ansResult;
	}
	
}
