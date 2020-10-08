package com.dlabs.acs.test.participant;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.service.intf.assessement.IParticipantService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext.xml"})
@WebAppConfiguration()
public class TestParticipant {
	@Autowired
	private IParticipantService participantService;
	
	@Test
	public void addParticipant(){
		
		
		Participant participant = new Participant();
		participant.setEmail("test@test.com");
		participant.setFullName("test");
		participant.setPassword("abc");
		Date now = new Date();
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(participant.getEmail());
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(participant.getEmail());
		commonFields.setLastModifiedDate(now);
		
		
		participant.setCommonFields(commonFields);
		
		participantService.save(participant);
	}
}
