package com.dlabs.acs.test.lcb;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dlabs.acs.dto.assessement.ParticipantLcbDto;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.service.business.lcb.LcbBusinessService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.test.util.excel.lcb.TestExcelLcbAnswer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext.xml", "classpath:spring/sql-view.xml"})
@WebAppConfiguration()
public class TestLcbAnswer {
	@Autowired
	private LcbBusinessService lcbBusinessService;
	@Autowired
	private IParticipantService participantService;
	
	@Test
	public void addAdministratorAccount(){
		System.out.println("begin");
		InputStream is =getClass().getClassLoader().getResourceAsStream("init/Upload_answer_v2_1_1.xlsx");
		System.out.println("1");
		List<ParticipantLcbDto> listDto = TestExcelLcbAnswer.parse(is);
		System.out.println("2");
		Participant participant = participantService.findById(2L);
		
		System.out.println("a");
		
		lcbBusinessService.evaluate(listDto, participant);
		
		System.out.println("end");
	}
}
