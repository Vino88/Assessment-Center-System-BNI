package com.dlabs.acs.entity.analysys;

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

@NamedQueries({
	@NamedQuery(name="Analysys.getBySearch", query="FROM Analysys where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="Analysys.countBySearch", query="select count(*) FROM Analysys where LOWER(id) like :search"),
	
	@NamedQuery(name="Analysys.getByQuestNumber", query="FROM Analysys where commonFields.deleted = false AND number = :number"),
	@NamedQuery(name="Analysys.getByAnalysysType", query="FROM Analysys where analysysType=:analysysType order by id asc"),
	
})
@Entity
public class Analysys extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	@Column(nullable = false, unique=true)
	private int number;
	private String title;
	@Enumerated(EnumType.STRING)
	private AnalysysType analysysType;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AnalysysType getAnalysysType() {
		return analysysType;
	}

	public void setAnalysysType(AnalysysType analysysType) {
		this.analysysType = analysysType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
