package com.dlabs.acs.test.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dlabs.acs.dto.assessementresult.report.IndividuReportDto;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.enumeration.CompetencyCategory;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.util.report.JasperReportIndividuUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext.xml",  "classpath:spring/sql-view.xml", })
@WebAppConfiguration()
public class JasperTest {
	@Autowired
	private JasperReportIndividuUtil jasperReportIndividuUtil;
	
	@Test
	public void generatePdf() throws Exception
	{
		Profile profile = new Profile();
        profile.setCareerAspiration("aspiration There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc");
        profile.setFunctionalCompetency("func There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc");
        profile.setLeadershipCompetency("lead There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc");
        profile.setPersonalCompetency("personal There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc");
        
        profile.setStrong("strong There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc");
        profile.setWeakness("weak There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc");
        
        Employee employee = new  Employee();
        employee.setDateOfBirth(new Date());
        employee.setPlaceOfBirth("pob");
        employee.setNik("nik1");
        employee.setFullName("fullname");
        employee.setCurrentPositionName("curpos");
        
        List<IndividuReportDto> listIndividuReportDto = new ArrayList<IndividuReportDto>();
		
		for(int i=1;i<=33;i++)
		{
			IndividuReportDto individuReportDto = new IndividuReportDto();
			individuReportDto.setCompetencyId(Long.valueOf(i));
			individuReportDto.setCompetencyName("a");
			individuReportDto.setSelectedForReport(true);
			listIndividuReportDto.add(individuReportDto);
		}
		
		
		List<ParticipantCompetencyLibraryFinalResult> listPcf = new ArrayList<ParticipantCompetencyLibraryFinalResult>();
		
		for(int i=1;i<=5;i++)
		{
			ParticipantCompetencyLibraryFinalResult pcf = new ParticipantCompetencyLibraryFinalResult();
			pcf.setCompetencyLibrary(new CompetencyLibrary());
			pcf.getCompetencyLibrary().setId(Long.valueOf(i));
			pcf.getCompetencyLibrary().setCompetencyName("bb");
			pcf.getCompetencyLibrary().setCategory(CompetencyCategory.PERSONAL.toString());
			listPcf.add(pcf);
		}
		
		for(int i=6;i<=17;i++)
		{
			ParticipantCompetencyLibraryFinalResult pcf = new ParticipantCompetencyLibraryFinalResult();
			pcf.setCompetencyLibrary(new CompetencyLibrary());
			pcf.getCompetencyLibrary().setId(Long.valueOf(i));
			pcf.getCompetencyLibrary().setCompetencyName("bb");
			pcf.getCompetencyLibrary().setCategory(CompetencyCategory.FUNCTIONAL.toString());
			listPcf.add(pcf);
		}
		
		for(int i=18;i<=49;i++)
		{
			ParticipantCompetencyLibraryFinalResult pcf = new ParticipantCompetencyLibraryFinalResult();
			pcf.setCompetencyLibrary(new CompetencyLibrary());
			pcf.getCompetencyLibrary().setId(Long.valueOf(i));
			pcf.getCompetencyLibrary().setCompetencyName("bb");
			pcf.getCompetencyLibrary().setCategory(CompetencyCategory.LEADERSHIP.toString());
			listPcf.add(pcf);
		}
		
        OutputStream os = new FileOutputStream("E:\\jasper.pdf");
//        jasperReportIndividuUtil.generatePdf("asq", employee, profile,listPcf,listIndividuReportDto, os);
        
/*        OutputStream os = new FileOutputStream("E:\\jasper.docx");
        jasperReportIndividuUtil.generateDocx( employee, profile, os);*/
	}
	
	
}
