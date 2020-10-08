package com.dlabs.acs.entity.assessement;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;
import com.dlabs.acs.entity.assessement.enumeration.LcbAnswer;
import com.dlabs.acs.entity.lcb.Lcb;

@NamedQueries({
	@NamedQuery(name="ParticipantLcb.getBySearch", query="FROM ParticipantLcb where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="ParticipantLcb.countBySearch", query="select count(*) FROM ParticipantLcb where LOWER(id) like :search")
})
@Entity
public class ParticipantLcb extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Enumerated(EnumType.STRING)
	@Column(length=1)
	private LcbAnswer lcbAnswer;
	
	private int lcbAnswerWeight;

	@JoinColumn
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	private Participant participant;
	
	@JoinColumn
	@ManyToOne
	private Lcb lcb;

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

	public LcbAnswer getLcbAnswer() {
		return lcbAnswer;
	}

	public void setLcbAnswer(LcbAnswer lcbAnswer) {
		this.lcbAnswer = lcbAnswer;
	}

	public int getLcbAnswerWeight() {
		return lcbAnswerWeight;
	}

	public void setLcbAnswerWeight(int lcbAnswerWeight) {
		this.lcbAnswerWeight = lcbAnswerWeight;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Lcb getLcb() {
		return lcb;
	}

	public void setLcb(Lcb lcb) {
		this.lcb = lcb;
	}
}
