package com.dlabs.acs.entity.assessement;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;
import com.dlabs.acs.entity.assessement.enumeration.LogDuringStatus;

@NamedQueries({
	@NamedQuery(name="ParticipantLogDuring.getBySearch", query="FROM ParticipantLogDuring where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="ParticipantLogDuring.countBySearch", query="select count(*) FROM ParticipantLogDuring where LOWER(id) like :search")
})
@Entity
public class ParticipantLogDuring extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fistTimeLogin;
	
	@Enumerated(EnumType.STRING)
	private LogDuringStatus logDuringStatus;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date factsheetStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date factsheetLastDraftTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date factsheetFinishTime;
	
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date capStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date capEndTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date capLastDraftTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date capFinishTime;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date aspirationStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date aspirationLastDraftTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date aspirationEndTime;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lcbStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lcbEndTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lcbLastDraftTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lcbFinishTime;
	
	private String lcbOrder;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstSessionFinishTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date secondSessionLoginTime;
	
	private boolean articulateSrbRead;
	private boolean articulateSpmRead;
	private boolean articulateMemoRead;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date inbasketReadMemoStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date inbasketReadMemoEndTime;
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date inbasketStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date inbasketEndTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date inbasketLastDraftTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date inbasketFinishTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date inbasketVideoLinkOpened;
	@Temporal(TemporalType.TIMESTAMP)
	private Date inbasketWarning;
	@Temporal(TemporalType.TIMESTAMP)
	private Date inbasketRedirect;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date analysysStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date analysysEndTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date analysysLastDraftTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date analysysFinishTime;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date visionStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date visionEndTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date visionFinishTime;
	
	private boolean visionUploaded;
	private int visionCount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date answerFinishTime;
	
	@JoinColumn
	@OneToOne
	@Basic(fetch=FetchType.LAZY)
	private Participant participant;

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

	public Date getFistTimeLogin() {
		return fistTimeLogin;
	}

	public void setFistTimeLogin(Date fistTimeLogin) {
		this.fistTimeLogin = fistTimeLogin;
	}

	public LogDuringStatus getLogDuringStatus() {
		return logDuringStatus;
	}

	public void setLogDuringStatus(LogDuringStatus logDuringStatus) {
		this.logDuringStatus = logDuringStatus;
	}

	public Date getFactsheetStartTime() {
		return factsheetStartTime;
	}

	public void setFactsheetStartTime(Date factsheetStartTime) {
		this.factsheetStartTime = factsheetStartTime;
	}

	public Date getFactsheetLastDraftTime() {
		return factsheetLastDraftTime;
	}

	public void setFactsheetLastDraftTime(Date factsheetLastDraftTime) {
		this.factsheetLastDraftTime = factsheetLastDraftTime;
	}

	public Date getFactsheetFinishTime() {
		return factsheetFinishTime;
	}

	public void setFactsheetFinishTime(Date factsheetFinishTime) {
		this.factsheetFinishTime = factsheetFinishTime;
	}

	public Date getCapStartTime() {
		return capStartTime;
	}

	public void setCapStartTime(Date capStartTime) {
		this.capStartTime = capStartTime;
	}

	public Date getCapEndTime() {
		return capEndTime;
	}

	public void setCapEndTime(Date capEndTime) {
		this.capEndTime = capEndTime;
	}

	public Date getCapLastDraftTime() {
		return capLastDraftTime;
	}

	public void setCapLastDraftTime(Date capLastDraftTime) {
		this.capLastDraftTime = capLastDraftTime;
	}

	public Date getCapFinishTime() {
		return capFinishTime;
	}

	public void setCapFinishTime(Date capFinishTime) {
		this.capFinishTime = capFinishTime;
	}

	public Date getLcbStartTime() {
		return lcbStartTime;
	}

	public void setLcbStartTime(Date lcbStartTime) {
		this.lcbStartTime = lcbStartTime;
	}

	public Date getLcbEndTime() {
		return lcbEndTime;
	}

	public void setLcbEndTime(Date lcbEndTime) {
		this.lcbEndTime = lcbEndTime;
	}

	public Date getLcbLastDraftTime() {
		return lcbLastDraftTime;
	}

	public void setLcbLastDraftTime(Date lcbLastDraftTime) {
		this.lcbLastDraftTime = lcbLastDraftTime;
	}

	public Date getLcbFinishTime() {
		return lcbFinishTime;
	}

	public void setLcbFinishTime(Date lcbFinishTime) {
		this.lcbFinishTime = lcbFinishTime;
	}

	public String getLcbOrder() {
		return lcbOrder;
	}

	public void setLcbOrder(String lcbOrder) {
		this.lcbOrder = lcbOrder;
	}

	public Date getFirstSessionFinishTime() {
		return firstSessionFinishTime;
	}

	public void setFirstSessionFinishTime(Date firstSessionFinishTime) {
		this.firstSessionFinishTime = firstSessionFinishTime;
	}

	public Date getSecondSessionLoginTime() {
		return secondSessionLoginTime;
	}

	public void setSecondSessionLoginTime(Date secondSessionLoginTime) {
		this.secondSessionLoginTime = secondSessionLoginTime;
	}

	public Date getInbasketReadMemoStartTime() {
		return inbasketReadMemoStartTime;
	}

	public void setInbasketReadMemoStartTime(Date inbasketReadMemoStartTime) {
		this.inbasketReadMemoStartTime = inbasketReadMemoStartTime;
	}

	public Date getInbasketReadMemoEndTime() {
		return inbasketReadMemoEndTime;
	}

	public void setInbasketReadMemoEndTime(Date inbasketReadMemoEndTime) {
		this.inbasketReadMemoEndTime = inbasketReadMemoEndTime;
	}

	public Date getInbasketStartTime() {
		return inbasketStartTime;
	}

	public void setInbasketStartTime(Date inbasketStartTime) {
		this.inbasketStartTime = inbasketStartTime;
	}

	public Date getInbasketEndTime() {
		return inbasketEndTime;
	}

	public void setInbasketEndTime(Date inbasketEndTime) {
		this.inbasketEndTime = inbasketEndTime;
	}

	public Date getInbasketLastDraftTime() {
		return inbasketLastDraftTime;
	}

	public void setInbasketLastDraftTime(Date inbasketLastDraftTime) {
		this.inbasketLastDraftTime = inbasketLastDraftTime;
	}

	public Date getInbasketFinishTime() {
		return inbasketFinishTime;
	}

	public void setInbasketFinishTime(Date inbasketFinishTime) {
		this.inbasketFinishTime = inbasketFinishTime;
	}

	public Date getAnalysysStartTime() {
		return analysysStartTime;
	}

	public void setAnalysysStartTime(Date analysysStartTime) {
		this.analysysStartTime = analysysStartTime;
	}

	public Date getAnalysysEndTime() {
		return analysysEndTime;
	}

	public void setAnalysysEndTime(Date analysysEndTime) {
		this.analysysEndTime = analysysEndTime;
	}

	public Date getAnalysysLastDraftTime() {
		return analysysLastDraftTime;
	}

	public void setAnalysysLastDraftTime(Date analysysLastDraftTime) {
		this.analysysLastDraftTime = analysysLastDraftTime;
	}

	public Date getAnalysysFinishTime() {
		return analysysFinishTime;
	}

	public void setAnalysysFinishTime(Date analysysFinishTime) {
		this.analysysFinishTime = analysysFinishTime;
	}

	public Date getAnswerFinishTime() {
		return answerFinishTime;
	}

	public void setAnswerFinishTime(Date answerFinishTime) {
		this.answerFinishTime = answerFinishTime;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Date getInbasketVideoLinkOpened() {
		return inbasketVideoLinkOpened;
	}

	public void setInbasketVideoLinkOpened(Date inbasketVideoLinkOpened) {
		this.inbasketVideoLinkOpened = inbasketVideoLinkOpened;
	}

	public Date getInbasketWarning() {
		return inbasketWarning;
	}

	public void setInbasketWarning(Date inbasketWarning) {
		this.inbasketWarning = inbasketWarning;
	}

	public Date getInbasketRedirect() {
		return inbasketRedirect;
	}

	public void setInbasketRedirect(Date inbasketRedirect) {
		this.inbasketRedirect = inbasketRedirect;
	}

	public boolean isArticulateSrbRead() {
		return articulateSrbRead;
	}

	public void setArticulateSrbRead(boolean articulateSrbRead) {
		this.articulateSrbRead = articulateSrbRead;
	}

	public boolean isArticulateSpmRead() {
		return articulateSpmRead;
	}

	public void setArticulateSpmRead(boolean articulateSpmRead) {
		this.articulateSpmRead = articulateSpmRead;
	}

	public boolean isArticulateMemoRead() {
		return articulateMemoRead;
	}

	public void setArticulateMemoRead(boolean articulateMemoRead) {
		this.articulateMemoRead = articulateMemoRead;
	}

	public Date getVisionStartTime() {
		return visionStartTime;
	}

	public void setVisionStartTime(Date visionStartTime) {
		this.visionStartTime = visionStartTime;
	}

	public Date getVisionEndTime() {
		return visionEndTime;
	}

	public void setVisionEndTime(Date visionEndTime) {
		this.visionEndTime = visionEndTime;
	}
	
	public Date getVisionFinishTime() {
		return visionFinishTime;
	}

	public void setVisionFinishTime(Date visionFinishTime) {
		this.visionFinishTime = visionFinishTime;
	}

	public boolean isVisionUploaded() {
		return visionUploaded;
	}

	public void setVisionUploaded(boolean visionUploaded) {
		this.visionUploaded = visionUploaded;
	}

	public int getVisionCount() {
		return visionCount;
	}

	public void setVisionCount(int visionCount) {
		this.visionCount = visionCount;
	}

	public Date getAspirationStartTime() {
		return aspirationStartTime;
	}

	public void setAspirationStartTime(Date aspirationStartTime) {
		this.aspirationStartTime = aspirationStartTime;
	}

	public Date getAspirationLastDraftTime() {
		return aspirationLastDraftTime;
	}

	public void setAspirationLastDraftTime(Date aspirationLastDraftTime) {
		this.aspirationLastDraftTime = aspirationLastDraftTime;
	}

	public Date getAspirationEndTime() {
		return aspirationEndTime;
	}

	public void setAspirationEndTime(Date aspirationEndTime) {
		this.aspirationEndTime = aspirationEndTime;
	}
}
