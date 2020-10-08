package com.dlabs.acs.dto.assessement;

import java.util.List;

public class InbasketDto {
	private List<ParticipantInbasketDto> listParticipantInbasketDto;
	private Long inbasketInboxId;

	public Long getInbasketInboxId() {
		return inbasketInboxId;
	}

	public void setInbasketInboxId(Long inbasketInboxId) {
		this.inbasketInboxId = inbasketInboxId;
	}

	public List<ParticipantInbasketDto> getListParticipantInbasketDto() {
		return listParticipantInbasketDto;
	}

	public void setListParticipantInbasketDto(List<ParticipantInbasketDto> listParticipantInbasketDto) {
		this.listParticipantInbasketDto = listParticipantInbasketDto;
	}
	
	
}
