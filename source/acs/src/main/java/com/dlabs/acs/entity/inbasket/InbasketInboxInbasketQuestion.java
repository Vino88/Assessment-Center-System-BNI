package com.dlabs.acs.entity.inbasket;

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
	@NamedQuery(name="InbasketInboxInbasketQuestion.getBySearch", query="FROM InbasketInboxInbasketQuestion where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="InbasketInboxInbasketQuestion.countBySearch", query="select count(*) FROM InbasketInboxInbasketQuestion where LOWER(id) like :search"),
	
	@NamedQuery(name="InbasketInboxInbasketQuestion.getByInboxQnumAndQuestionQnum", query="FROM InbasketInboxInbasketQuestion i where i.inbasketInbox.number = :inboxNumber AND i.inbasketQuestion.number= :questionNumber"),
	@NamedQuery(name="InbasketInboxInbasketQuestion.getByInboxId", query="FROM InbasketInboxInbasketQuestion i where i.inbasketInbox.id = :inboxId"),
})
@Entity
public class InbasketInboxInbasketQuestion extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;

	@ManyToOne
    @JoinColumn()
	private InbasketInbox inbasketInbox;

	@ManyToOne
    @JoinColumn()
	private InbasketQuestion inbasketQuestion;
	
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

	public InbasketInbox getInbasketInbox() {
		return inbasketInbox;
	}

	public void setInbasketInbox(InbasketInbox inbasketInbox) {
		this.inbasketInbox = inbasketInbox;
	}

	public InbasketQuestion getInbasketQuestion() {
		return inbasketQuestion;
	}

	public void setInbasketQuestion(InbasketQuestion inbasketQuestion) {
		this.inbasketQuestion = inbasketQuestion;
	}
}
