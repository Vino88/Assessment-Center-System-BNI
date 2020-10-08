package com.dlabs.acs.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.util.mail.MailUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext.xml",  "classpath:spring/sql-view.xml", })
@WebAppConfiguration()
public class MailTest {
	@Autowired
	private MailUtil mailUtil;
	
	@Test
	public void addParticipant(){
		Participant participantCredential = new Participant();
		participantCredential.setEmail("syauqi.qasthalani@dayalima.com");
		
		System.out.println("testtt");
		System.out.println(mailUtil.sendEmailInvitation(participantCredential, null));
		
	}
}
