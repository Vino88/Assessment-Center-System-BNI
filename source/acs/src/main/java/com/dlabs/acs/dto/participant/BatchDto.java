package com.dlabs.acs.dto.participant;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dlabs.acs.dto.DataFormDto;
import com.dlabs.acs.dto.credential.UserAdminDto;
import com.dlabs.acs.util.DateUtil;

public class BatchDto extends DataFormDto{
	private String description;
	@Size(max=255)
	private String location;
	
	@Size(max=255)
	private String locationSecond;
	@NotNull
	@Min(0)
	private Integer maxQuota;
	@Size(max=2000)
	private String additionalInformation;

	private Date assessementFirstHalfStartTime;
	
	private Date assessementFirstHalfEndTime;
	
	private Date assessementSecondHalfStartTime;
	
	private Date assessementFinishTime;	
	
	private String assessementFirstHalfStartTimeStr;
	
	private String assessementFirstHalfEndTimeStr;
	
	private String assessementSecondHalfStartTimeStr;
	
	private String assessementFinishTimeStr;
	
	private Long userId;
	
	private UserAdminDto userAdmin;
	
	private boolean inprogress;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(Integer maxQuota) {
		this.maxQuota = maxQuota;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public Date getAssessementFirstHalfStartTime() {
		return assessementFirstHalfStartTime;
	}

	public void setAssessementFirstHalfStartTime(Date assessementFirstHalfStartTime) {
		this.assessementFirstHalfStartTime = assessementFirstHalfStartTime;
		this.assessementFirstHalfStartTimeStr = DateUtil.dateTimeToString(this.assessementFirstHalfStartTime);
	}

	public Date getAssessementFirstHalfEndTime() {
		return assessementFirstHalfEndTime;
	}

	public void setAssessementFirstHalfEndTime(Date assessementFirstHalfEndTime) {
		this.assessementFirstHalfEndTime = assessementFirstHalfEndTime;
		this.assessementFirstHalfEndTimeStr = DateUtil.dateTimeToString(this.assessementFirstHalfEndTime);
	}

	public Date getAssessementSecondHalfStartTime() {
		return assessementSecondHalfStartTime;
	}

	public void setAssessementSecondHalfStartTime(Date assessementSecondHalfStartTime) {
		this.assessementSecondHalfStartTime = assessementSecondHalfStartTime;
		this.assessementSecondHalfStartTimeStr = DateUtil.dateTimeToString(this.assessementSecondHalfStartTime);
	}

	public Date getAssessementFinishTime() {
		return assessementFinishTime;
	}

	public void setAssessementFinishTime(Date assessementFinishTime) {
		this.assessementFinishTime = assessementFinishTime;
		this.assessementFinishTimeStr = DateUtil.dateTimeToString(this.assessementFinishTime);
	}

	public String getAssessementFirstHalfStartTimeStr() {
		return assessementFirstHalfStartTimeStr;
	}

	public void setAssessementFirstHalfStartTimeStr(String assessementFirstHalfStartTimeStr) {
		this.assessementFirstHalfStartTimeStr = assessementFirstHalfStartTimeStr;
		this.assessementFirstHalfStartTime = DateUtil.stringToDateTime(this.assessementFirstHalfStartTimeStr);
	}

	public String getAssessementFirstHalfEndTimeStr() {
		return assessementFirstHalfEndTimeStr;
	}

	public void setAssessementFirstHalfEndTimeStr(String assessementFirstHalfEndTimeStr) {
		this.assessementFirstHalfEndTimeStr = assessementFirstHalfEndTimeStr;
		this.assessementFirstHalfEndTime = DateUtil.stringToDateTime(this.assessementFirstHalfEndTimeStr);
	}

	public String getAssessementSecondHalfStartTimeStr() {
		return assessementSecondHalfStartTimeStr;
	}

	public void setAssessementSecondHalfStartTimeStr(String assessementSecondHalfStartTimeStr) {
		this.assessementSecondHalfStartTimeStr = assessementSecondHalfStartTimeStr;
		this.assessementSecondHalfStartTime = DateUtil.stringToDateTime(this.assessementSecondHalfStartTimeStr);
	}

	public String getAssessementFinishTimeStr() {
		return assessementFinishTimeStr;
	}

	public void setAssessementFinishTimeStr(String assessementFinishTimeStr) {
		this.assessementFinishTimeStr = assessementFinishTimeStr;
		this.assessementFinishTime = DateUtil.stringToDateTime(this.assessementFinishTimeStr);
	}

	public String getLocationSecond() {
		return locationSecond;
	}

	public void setLocationSecond(String locationSecond) {
		this.locationSecond = locationSecond;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserAdminDto getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(UserAdminDto userAdmin) {
		this.userAdmin = userAdmin;
	}

	public boolean isInprogress() {
		return inprogress;
	}

	public void setInprogress(boolean inprogress) {
		this.inprogress = inprogress;
	}
	
}