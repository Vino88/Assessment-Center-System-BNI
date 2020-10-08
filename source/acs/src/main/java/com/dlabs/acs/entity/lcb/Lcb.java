package com.dlabs.acs.entity.lcb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;
import com.dlabs.acs.entity.lcb.enumeration.LcbCategory;

@NamedQueries({
	@NamedQuery(name="Lcb.getBySearch", query="FROM Lcb  where commonFields.deleted = false AND (  LOWER(question) like :search ) order by id asc"),
	@NamedQuery(name="Lcb.countBySearch", query="select count(*) FROM Lcb where commonFields.deleted = false AND (  LOWER(question) like :search )"),
	
	@NamedQuery(name="Lcb.getByQuestNumber", query="FROM Lcb where commonFields.deleted = false AND number = :number"),
})
@Entity
public class Lcb extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Column(nullable = false, unique=true)
	private int number;
	@Column(nullable = false)
	private LcbCategory lbCategory;
	@Column(nullable = false, length = 20000)
	private String question;
	@Column(nullable = false, length = 20000)
	private String choiceA;
	@Column(nullable = false, length = 20000)
	private String choiceB;
	@Column(length = 20000)
	private String choiceC;
	@Column(length = 20000)
	private String choiceD;
	@Column(length = 20000)
	private String choiceE;
	@Column(nullable = false)
	private Integer weightA;	
	@Column(nullable = false)
	private Integer weightB;
	@Column()
	private Integer weightC;
	@Column
	private Integer weightD;
	@Column
	private Integer weightE;


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

	public LcbCategory getLbCategory() {
		return lbCategory;
	}

	public void setLbCategory(LcbCategory lbCategory) {
		this.lbCategory = lbCategory;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChoiceA() {
		return choiceA;
	}

	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}

	public String getChoiceB() {
		return choiceB;
	}

	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}

	public String getChoiceC() {
		return choiceC;
	}

	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}

	public String getChoiceD() {
		return choiceD;
	}

	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}

	public String getChoiceE() {
		return choiceE;
	}

	public void setChoiceE(String choiceE) {
		this.choiceE = choiceE;
	}

	public Integer getWeightA() {
		return weightA;
	}

	public void setWeightA(Integer weightA) {
		this.weightA = weightA;
	}

	public Integer getWeightB() {
		return weightB;
	}

	public void setWeightB(Integer weightB) {
		this.weightB = weightB;
	}

	public Integer getWeightC() {
		return weightC;
	}

	public void setWeightC(Integer weightC) {
		this.weightC = weightC;
	}

	public Integer getWeightD() {
		return weightD;
	}

	public void setWeightD(Integer weightD) {
		this.weightD = weightD;
	}

	@Override
	public String toString() {
		return "Lcb [id=" + id + ", commonFields=" + commonFields + ", number=" + number + ", lbCategory=" + lbCategory
				+ ", weightA=" + weightA + ", weightB=" + weightB + ", weightC=" + weightC + ", weightD=" + weightD
				+ ", weightE=" + weightE + "]";
	}

	public Integer getWeightE() {
		return weightE;
	}

	public void setWeightE(Integer weightE) {
		this.weightE = weightE;
	}
}
