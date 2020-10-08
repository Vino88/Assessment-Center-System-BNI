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
import com.dlabs.acs.entity.inbasket.InbasketInboxInbasketQuestion;

@NamedQueries({
	@NamedQuery(name="ParticipantInbasketInboxInbasketQuestion.getBySearch", query="FROM ParticipantInbasketInboxInbasketQuestion where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="ParticipantInbasketInboxInbasketQuestion.countBySearch", query="select count(*) FROM ParticipantInbasketInboxInbasketQuestion where LOWER(id) like :search"),
	
	@NamedQuery(name="ParticipantInbasketInboxInbasketQuestion.getByPartIdAndIiibId", query="FROM ParticipantInbasketInboxInbasketQuestion where participant.id= :participantId AND inbasketInboxInbasketQuestion.id = :iiibId"),
	
	@NamedQuery(name="ParticipantInbasketInboxInbasketQuestion.countMemoByPartId", query="select distinct(p.inbasketInboxInbasketQuestion.inbasketInbox) FROM ParticipantInbasketInboxInbasketQuestion p where p.participant.id= :participantId AND p.inbasketInboxInbasketQuestion.inbasketInbox.inboxType = 'MEMO'"),
})
@Entity
public class ParticipantInbasketInboxInbasketQuestion extends AbstractEntity{
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
	@Basic(fetch=FetchType.LAZY)
	private InbasketInboxInbasketQuestion inbasketInboxInbasketQuestion;

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

	public InbasketInboxInbasketQuestion getInbasketInboxInbasketQuestion() {
		return inbasketInboxInbasketQuestion;
	}

	public void setInbasketInboxInbasketQuestion(InbasketInboxInbasketQuestion inbasketInboxInbasketQuestion) {
		this.inbasketInboxInbasketQuestion = inbasketInboxInbasketQuestion;
	}
	
}
