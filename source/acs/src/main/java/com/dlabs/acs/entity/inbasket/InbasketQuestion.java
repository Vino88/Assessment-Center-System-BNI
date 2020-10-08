package com.dlabs.acs.entity.inbasket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;

@NamedQueries({
	@NamedQuery(name="InbasketQuestion.getBySearch", query="FROM InbasketQuestion where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="InbasketQuestion.countBySearch", query="select count(*) FROM InbasketQuestion where LOWER(id) like :search"),
	
	@NamedQuery(name="InbasketQuestion.getByQuestNumber", query="FROM InbasketQuestion where number = :number"),
})
@Entity
public class InbasketQuestion extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Column(nullable = false, unique=true)
	private int number;
	@Column(nullable = false, length = 20000)
	private String question;

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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "InbasketQuestion [id=" + id + ", number=" + number + ", question=" + question + "]";
	}
	
	
}
