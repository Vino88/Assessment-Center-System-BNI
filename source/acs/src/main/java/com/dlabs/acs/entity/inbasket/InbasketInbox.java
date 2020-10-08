package com.dlabs.acs.entity.inbasket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;
import com.dlabs.acs.entity.inbasket.enumeration.InboxType;

@NamedQueries({
	@NamedQuery(name="InbasketInbox.getBySearch", query="FROM InbasketInbox where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="InbasketInbox.countBySearch", query="select count(*) FROM InbasketInbox where LOWER(id) like :search"),
	
	@NamedQuery(name="InbasketInbox.getByQuestNumber", query="FROM InbasketInbox where number = :number"),
	
	@NamedQuery(name="InbasketInbox.getByInboxType", query="FROM InbasketInbox where inboxType = :inboxType"),
})
@Entity
public class InbasketInbox extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Enumerated(EnumType.STRING)
	private InboxType inboxType;
	private String title;
	@Column(nullable = false, unique=true)
	private int number;

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

	public InboxType getInboxType() {
		return inboxType;
	}

	public void setInboxType(InboxType inboxType) {
		this.inboxType = inboxType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "InbasketInbox [id=" + id + ", inboxType=" + inboxType + ", title=" + title + ", number=" + number + "]";
	}
	
	
}
