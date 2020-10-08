package com.dlabs.acs.entity.cap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;

@NamedQueries({
	@NamedQuery(name="Cap.getBySearch", query="FROM Cap where commonFields.deleted = false AND (LOWER(question) like :search) order by id asc"),
	@NamedQuery(name="Cap.countBySearch", query="select count(*) FROM Cap where commonFields.deleted = false AND (LOWER(question) like :search)"),
	
	@NamedQuery(name="Cap.getByQuestNumber", query="FROM Cap where commonFields.deleted = false AND number = :number"),
})
@Entity
public class Cap extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Column(nullable = false, unique=true)
	private int number;
	@Column(nullable = false, length = 20000)
	private String question;
	
	@Column(nullable = false, length = 20000)
	private String qSituation;
	@Column(nullable = false, length = 20000)
	private String qAction;
	@Column(nullable = false, length = 20000)
	private String qResult;


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
	
	public String getqSituation() {
		return qSituation;
	}

	public void setqSituation(String qSituation) {
		this.qSituation = qSituation;
	}

	public String getqAction() {
		return qAction;
	}

	public void setqAction(String qAction) {
		this.qAction = qAction;
	}

	public String getqResult() {
		return qResult;
	}

	public void setqResult(String qResult) {
		this.qResult = qResult;
	}

	@Override
	public String toString() {
		return "Cap [id=" + id + ", number=" + number + ", question=" + question + "]";
	}
}
