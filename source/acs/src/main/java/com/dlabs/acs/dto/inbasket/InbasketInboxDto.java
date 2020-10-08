package com.dlabs.acs.dto.inbasket;

import com.dlabs.acs.dto.DataFormDto;
import com.dlabs.acs.entity.inbasket.enumeration.InboxType;

public class InbasketInboxDto extends DataFormDto{
	private InboxType inboxType;
	private String title;
	private int number;
	
	public InboxType getInboxType() {
		return inboxType;
	}
	public void setInboxType(InboxType inboxType) {
		this.inboxType = inboxType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}