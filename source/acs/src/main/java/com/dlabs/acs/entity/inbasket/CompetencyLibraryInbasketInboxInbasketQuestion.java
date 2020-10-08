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
import com.dlabs.acs.entity.competency.CompetencyLibrary;

@NamedQueries({
	@NamedQuery(name="CompetencyLibraryInbasketInboxInbasketQuestion.getBySearch", query="FROM CompetencyLibraryInbasketInboxInbasketQuestion where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="CompetencyLibraryInbasketInboxInbasketQuestion.countBySearch", query="select count(*) FROM CompetencyLibraryInbasketInboxInbasketQuestion where LOWER(id) like :search"),
	
	@NamedQuery(name="CompetencyLibraryInbasketInboxInbasketQuestion.getDistinctCompetencyLibrary", query="select distinct(c.competencyLibrary) FROM CompetencyLibraryInbasketInboxInbasketQuestion c "),
	@NamedQuery(name="CompetencyLibraryInbasketInboxInbasketQuestion.getIiiqByCompetencyId", query="select c.inbasketInboxInbasketQuestion FROM CompetencyLibraryInbasketInboxInbasketQuestion c WHERE c.competencyLibrary.id = :competencyLibraryId order by c.inbasketInboxInbasketQuestion.id asc"),
})
@Entity
public class CompetencyLibraryInbasketInboxInbasketQuestion extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;

	@ManyToOne
    @JoinColumn()
	private CompetencyLibrary competencyLibrary;
	@ManyToOne
    @JoinColumn()
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

	public CompetencyLibrary getCompetencyLibrary() {
		return competencyLibrary;
	}

	public void setCompetencyLibrary(CompetencyLibrary competencyLibrary) {
		this.competencyLibrary = competencyLibrary;
	}

	public InbasketInboxInbasketQuestion getInbasketInboxInbasketQuestion() {
		return inbasketInboxInbasketQuestion;
	}

	public void setInbasketInboxInbasketQuestion(InbasketInboxInbasketQuestion inbasketInboxInbasketQuestion) {
		this.inbasketInboxInbasketQuestion = inbasketInboxInbasketQuestion;
	}
}
