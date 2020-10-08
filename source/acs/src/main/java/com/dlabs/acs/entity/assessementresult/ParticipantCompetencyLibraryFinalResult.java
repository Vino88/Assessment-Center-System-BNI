package com.dlabs.acs.entity.assessementresult;

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
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.competency.CompetencyLibrary;

@NamedQueries({
	@NamedQuery(name="ParticipantCompetencyLibraryFinalResult.getBySearch", query="FROM ParticipantCompetencyLibraryFinalResult where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="ParticipantCompetencyLibraryFinalResult.countBySearch", query="select count(*) FROM ParticipantCompetencyLibraryFinalResult where LOWER(id) like :search"),
	
	@NamedQuery(name="ParticipantCompetencyLibraryFinalResult.getByParticipantIdOrderByCompetencyOrder", query="FROM ParticipantCompetencyLibraryFinalResult where participant.id = :participantId order by competencyLibrary.displayOrder asc, competencyLibrary.id asc"),
	
	@NamedQuery(name="ParticipantCompetencyLibraryFinalResult.getByParticipantIdAndCompetencyLibraryId", query="FROM ParticipantCompetencyLibraryFinalResult where participant.id=:participantId and competencyLibrary.id=:competencyLibraryId"),
	
	@NamedQuery(name="ParticipantCompetencyLibraryFinalResult.getReportBatchMeet", query="select pf.competencyLibrary.id as competencyLibraryId, COUNT(pf.competencyLibrary.id) as countComp FROM ParticipantCompetencyLibraryFinalResult pf where pf.participant.batch.id=:batchId AND pf.finalRating=4  group by pf.competencyLibrary"),
	@NamedQuery(name="ParticipantCompetencyLibraryFinalResult.getReportBatchAbove", query="select pf.competencyLibrary.id as competencyLibraryId, COUNT(pf.competencyLibrary.id) as countComp FROM ParticipantCompetencyLibraryFinalResult pf where pf.participant.batch.id=:batchId AND pf.finalRating>4  group by pf.competencyLibrary"),
	@NamedQuery(name="ParticipantCompetencyLibraryFinalResult.getReportBatchBelow", query="select pf.competencyLibrary.id as competencyLibraryId, COUNT(pf.competencyLibrary.id) as countComp FROM ParticipantCompetencyLibraryFinalResult pf where pf.participant.batch.id=:batchId AND pf.finalRating > 0  AND pf.finalRating<4  group by pf.competencyLibrary"),
	
	
	@NamedQuery(name="ParticipantCompetencyLibraryFinalResult.getReportGroup", query="select pf.competencyLibrary.id as competencyId, pf.competencyLibrary.competencyName as competencyName, pf.finalRating as finalRating, pf.participant.nik as nik, pf.participant.id as participantId  FROM ParticipantCompetencyLibraryFinalResult pf where pf.participant.batch.id=:batchId AND pf.participant.participantStatus = 'ASSESSEMENT_COMPLETE' ORDER BY participantId ASC, competencyId ASC"),
})
@Entity
public class ParticipantCompetencyLibraryFinalResult extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	private int lcbRating;
	private int capRating;
	private int inbasketRating;
	private int analysisRating;
	
	private int otherRating;
	
	private int integrationGridRating;
	
	private int finalRating;
	
	@Basic(fetch=FetchType.LAZY)
	@Column(length=20000)
	private String capNotes;
	
	private String capFlag;
	
	@Basic(fetch=FetchType.LAZY)
	@Column(length=20000)
	private String inbasketNotes;
	
	private String inbasketFlag;
	
	@Basic(fetch=FetchType.LAZY)
	@Column(length=20000)
	private String analysisNotes;
	
	private String analysisFlag;

	@JoinColumn
	@ManyToOne
	@Basic(fetch=FetchType.LAZY)
	private Participant participant;
	
	@ManyToOne
    @JoinColumn()
	private CompetencyLibrary competencyLibrary;
	
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

	public int getLcbRating() {
		return lcbRating;
	}

	public void setLcbRating(int lcbRating) {
		this.lcbRating = lcbRating;
	}

	public int getInbasketRating() {
		return inbasketRating;
	}

	public void setInbasketRating(int inbasketRating) {
		this.inbasketRating = inbasketRating;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public CompetencyLibrary getCompetencyLibrary() {
		return competencyLibrary;
	}

	public void setCompetencyLibrary(CompetencyLibrary competencyLibrary) {
		this.competencyLibrary = competencyLibrary;
	}

	public int getCapRating() {
		return capRating;
	}

	public void setCapRating(int capRating) {
		this.capRating = capRating;
	}
	
	public int getAnalysisRating() {
		return analysisRating;
	}

	public void setAnalysisRating(int analysisRating) {
		this.analysisRating = analysisRating;
	}

	public String getCapNotes() {
		return capNotes;
	}

	public void setCapNotes(String capNotes) {
		this.capNotes = capNotes;
	}

	public String getInbasketNotes() {
		return inbasketNotes;
	}

	public void setInbasketNotes(String inbasketNotes) {
		this.inbasketNotes = inbasketNotes;
	}

	public String getAnalysisNotes() {
		return analysisNotes;
	}

	public void setAnalysisNotes(String analysisNotes) {
		this.analysisNotes = analysisNotes;
	}

	public int getFinalRating() {
		return finalRating;
	}

	public void setFinalRating(int finalRating) {
		this.finalRating = finalRating;
	}

	public String getCapFlag() {
		return capFlag;
	}

	public void setCapFlag(String capFlag) {
		this.capFlag = capFlag;
	}

	public String getInbasketFlag() {
		return inbasketFlag;
	}

	public void setInbasketFlag(String inbasketFlag) {
		this.inbasketFlag = inbasketFlag;
	}

	public String getAnalysisFlag() {
		return analysisFlag;
	}

	public void setAnalysisFlag(String analysisFlag) {
		this.analysisFlag = analysisFlag;
	}

	public int getOtherRating() {
		return otherRating;
	}

	public void setOtherRating(int otherRating) {
		this.otherRating = otherRating;
	}

	public int getIntegrationGridRating() {
		return integrationGridRating;
	}

	public void setIntegrationGridRating(int integrationGridRating) {
		this.integrationGridRating = integrationGridRating;
	}

	@Override
	public String toString() {
		return "ParticipantCompetencyLibraryFinalResult [id=" + id + ", commonFields=" + commonFields + ", lcbRating="
				+ lcbRating + ", capRating=" + capRating + ", participant=" + participant + ", competencyLibrary="
				+ competencyLibrary + "]";
	}
	
	
}
