package com.dlabs.acs.embeddable;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class CommonFields implements Serializable
{
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date lastModifiedDate;
	private String createdBy;
	private String lastModifiedBy;

	private boolean deleted;

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate()
	{
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate)
	{
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy()
	{
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy)
	{
		this.lastModifiedBy = lastModifiedBy;
	}

	public boolean isDeleted()
	{
		return deleted;
	}

	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	@Override
	public String toString()
	{
		return "CommonFields [createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", createdBy=" + createdBy + ", lastModifiedBy=" + lastModifiedBy + ", deleted=" + deleted + "]";
	}

}
