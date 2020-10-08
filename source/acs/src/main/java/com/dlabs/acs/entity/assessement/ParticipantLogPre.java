package com.dlabs.acs.entity.assessement;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@NamedQueries({
	@NamedQuery(name="ParticipantLogPre.getBySearch", query="FROM ParticipantLogPre where LOWER(id) like :search order by id asc"),
	@NamedQuery(name="ParticipantLogPre.countBySearch", query="select count(*) FROM ParticipantLogPre where LOWER(id) like :search"),
	
	@NamedQuery(name="ParticipantLogPre.getInvitationSentTimeNullByBatchId", query="select participant.id FROM ParticipantLogPre where commonFields.deleted = false AND invitationSent=false AND participant.batch.id=:batchId order by id asc"),
})
@Entity
public class ParticipantLogPre extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	// Invitation
	private int invitationNumberOfTry;
	private boolean invitationSent;
	@Temporal(TemporalType.TIMESTAMP)
	private Date invitationSentTime;
	
	// Confirmation
	@Temporal(TemporalType.TIMESTAMP)
	private Date confirmationVisitTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date confirmationTime;
	private boolean confirmationAgree;
	@Column(length=8000)
	private String confirmationRejectNotes;
	
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

	public int getInvitationNumberOfTry() {
		return invitationNumberOfTry;
	}

	public void setInvitationNumberOfTry(int invitationNumberOfTry) {
		this.invitationNumberOfTry = invitationNumberOfTry;
	}

	public boolean isInvitationSent() {
		return invitationSent;
	}

	public void setInvitationSent(boolean invitationSent) {
		this.invitationSent = invitationSent;
	}

	public Date getInvitationSentTime() {
		return invitationSentTime;
	}

	public void setInvitationSentTime(Date invitationSentTime) {
		this.invitationSentTime = invitationSentTime;
	}

	public Date getConfirmationVisitTime() {
		return confirmationVisitTime;
	}

	public void setConfirmationVisitTime(Date confirmationVisitTime) {
		this.confirmationVisitTime = confirmationVisitTime;
	}

	public Date getConfirmationTime() {
		return confirmationTime;
	}

	public void setConfirmationTime(Date confirmationTime) {
		this.confirmationTime = confirmationTime;
	}

	public boolean isConfirmationAgree() {
		return confirmationAgree;
	}

	public void setConfirmationAgree(boolean confirmationAgree) {
		this.confirmationAgree = confirmationAgree;
	}

	public String getConfirmationRejectNotes() {
		return confirmationRejectNotes;
	}

	public void setConfirmationRejectNotes(String confirmationRejectNotes) {
		this.confirmationRejectNotes = confirmationRejectNotes;
	}
	
	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
}
