package com.dlabs.acs.dto.assessement;

import com.dlabs.acs.dto.AbstractDto;
import com.dlabs.acs.entity.assessement.enumeration.LcbAnswer;

public class ParticipantLcbDto extends AbstractDto{
	private LcbAnswer lcbAnswer;
	
	private int questionNumber;

	public LcbAnswer getLcbAnswer() {
		return lcbAnswer;
	}

	public void setLcbAnswer(LcbAnswer lcbAnswer) {
		this.lcbAnswer = lcbAnswer;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	@Override
	public String toString() {
		return "ParticipantLcbDto [lcbAnswer=" + lcbAnswer + ", questionNumber=" + questionNumber + "]";
	}
	
	
}
