package com.dlabs.acs.dto.assessement;

import java.util.List;

public class CapDto {
	private List<ParticipantCapDto> listParticipantCapDto;
	private int fromStep;
	private int toStep;
	private String lastTimer;

	public List<ParticipantCapDto> getListParticipantCapDto() {
		return listParticipantCapDto;
	}

	public void setListParticipantCapDto(List<ParticipantCapDto> listParticipantCapDto) {
		this.listParticipantCapDto = listParticipantCapDto;
	}

	public int getFromStep() {
		return fromStep;
	}

	public void setFromStep(int fromStep) {
		this.fromStep = fromStep;
	}

	public int getToStep() {
		return toStep;
	}

	public void setToStep(int toStep) {
		this.toStep = toStep;
	}

	public String getLastTimer() {
		return lastTimer;
	}

	public void setLastTimer(String lastTimer) {
		this.lastTimer = lastTimer;
	}

	
}
