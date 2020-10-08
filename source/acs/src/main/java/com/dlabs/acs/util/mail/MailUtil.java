package com.dlabs.acs.util.mail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.util.DateUtil;

@Component
public class MailUtil {
	@Autowired
    private JavaMailSender mailSender;
	@Autowired
    private VelocityEngine velocityEngine;
	
	private static final String FROM  = "info@dayalima.com";

	public boolean sendEmailInvitation(final Participant participantCredential, final Batch batch) {
		try
		{
	      MimeMessagePreparator preparator = new MimeMessagePreparator() {
		        @SuppressWarnings({ "rawtypes", "unchecked" })
				public void prepare(MimeMessage mimeMessage) throws Exception {
		             MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		             message.setTo(participantCredential.getEmail());
		             
		             message.setFrom(FROM);
		             message.setSubject("BNI Assessement Online");
		             message.setSentDate(new Date());
		             Map model = new HashMap();
		             model.put("participantCredential", participantCredential);
		             
		             
		             model.put("firstSessionDate", DateUtil.dateToStr(batch.getAssessementFirstHalfStartTime()));
		             model.put("firstSessionTime", DateUtil.dateToStr(batch.getAssessementFirstHalfStartTime(), DateUtil.DEFAULT_TIME_FMT));
		             model.put("secondSessionDate", DateUtil.dateToStr(batch.getAssessementSecondHalfStartTime()));
		             model.put("secondSessionTime", DateUtil.dateToStr(batch.getAssessementSecondHalfStartTime(), DateUtil.DEFAULT_TIME_FMT));
		             
		             model.put("now", DateUtil.dateToStr());
		             model.put("location", batch.getLocation());
		             model.put("locationSecond", batch.getLocationSecond());
		             model.put("additionalInformation", batch.getAdditionalInformation());
		             

		             String text = VelocityEngineUtils.mergeTemplateIntoString(
		                velocityEngine, "velocity/invitation.vm", "UTF-8", model);
		             message.setText(text, true);
		          }
		       };
		       mailSender.send(preparator);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean sendEmailForgotPassword(final UserAdmin userAdmin) {
		try
		{
	      MimeMessagePreparator preparator = new MimeMessagePreparator() {
		        @SuppressWarnings({ "rawtypes", "unchecked" })
				public void prepare(MimeMessage mimeMessage) throws Exception {
		             MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		             message.setTo(userAdmin.getEmail());
		             
		             message.setFrom(FROM);
		             message.setSubject("ACS - Forgot Password");
		             message.setSentDate(new Date());
		             Map model = new HashMap();
		             model.put("userAdmin", userAdmin);

		             String text = VelocityEngineUtils.mergeTemplateIntoString(
		                velocityEngine, "velocity/forgotpassword.vm", "UTF-8", model);
		             message.setText(text, true);
		          }
		       };
		       mailSender.send(preparator);
		}catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	
	public boolean sendEmailMoveBatch(final UserAdmin userAdmin, UserAdmin userAdminMover, Participant participant, final Batch batch) {
		try
		{
	      MimeMessagePreparator preparator = new MimeMessagePreparator() {
		        @SuppressWarnings({ "rawtypes", "unchecked" })
				public void prepare(MimeMessage mimeMessage) throws Exception {
		             MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		             message.setTo(userAdmin.getEmail());
		             
		             message.setFrom(FROM);
		             message.setSubject("ACS - Move Batch");
		             message.setSentDate(new Date());
		             Map model = new HashMap();
		             model.put("adminName", userAdmin.getFullname());
		             model.put("batchName", batch.getDescription());
		             model.put("session1desc", DateUtil.dateTimeToString(batch.getAssessementFirstHalfStartTime()));
		             model.put("session2desc", DateUtil.dateTimeToString(batch.getAssessementSecondHalfStartTime()));
		             

		             String text = VelocityEngineUtils.mergeTemplateIntoString(
		                velocityEngine, "velocity/movebatch.vm", "UTF-8", model);
		             message.setText(text, true);
		          }
		       };
		       mailSender.send(preparator);
		}catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	
	public boolean sendEmailReport(final String to, final ByteArrayOutputStream os, final String filename) {
		try
		{
	      MimeMessagePreparator preparator = new MimeMessagePreparator() {
		        @SuppressWarnings({ "rawtypes", "unchecked" })
				public void prepare(MimeMessage mimeMessage) throws Exception {
		             MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
		             message.setTo(to);
		             
		             message.addAttachment(filename,  new ByteArrayResource(IOUtils.toByteArray(new ByteArrayInputStream(os.toByteArray()))));
		             
		             message.setFrom(FROM);
		             message.setSubject("ACS - Report - " + filename);
		             message.setSentDate(new Date());
		             Map model = new HashMap();
		   
		             

		             String text = VelocityEngineUtils.mergeTemplateIntoString(
		                velocityEngine, "velocity/report.vm", "UTF-8", model);
		             message.setText(text, true);
		          }
		       };
		       mailSender.send(preparator);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}


}
