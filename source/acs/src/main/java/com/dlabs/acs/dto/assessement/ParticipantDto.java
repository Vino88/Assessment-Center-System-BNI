package com.dlabs.acs.dto.assessement;

import java.util.List;

import javax.validation.constraints.Size;

import com.dlabs.acs.dto.AbstractDto;
import com.dlabs.acs.dto.assessementresult.ParticipantFinalRatingDetailDto;

public class ParticipantDto extends AbstractDto{
	private Long id;
	
	@Size(max=255)
	private String fullName;
	
	@Size(max=255)
	private String nik;
	
	@Size(max=255)
	private String email;
	
	@Size(max=255)
	private String password;
	
	private Long batchId;
	
	private List<ParticipantFinalRatingDetailDto> personalityTestList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}	
	
	public List<ParticipantFinalRatingDetailDto> getPersonalityTestList() {
		return personalityTestList;
	}

	public void setPersonalityTestList(List<ParticipantFinalRatingDetailDto> personalityTestList) {
		this.personalityTestList = personalityTestList;
	}
	
}