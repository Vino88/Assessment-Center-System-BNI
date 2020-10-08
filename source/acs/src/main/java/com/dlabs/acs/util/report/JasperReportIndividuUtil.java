package com.dlabs.acs.util.report;
/*
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.dlabs.acs.dto.assessementresult.report.IndividuReportDto;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.competency.enumeration.CompetencyCategory;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.util.properties.ConfigProperties;
import com.lowagie.text.pdf.PdfWriter;

import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Component*/
public class JasperReportIndividuUtil {
	/*@Autowired
    private VelocityEngine velocityEngine;
	
	@Autowired
	private ConfigProperties configProperties;
	
	private List<IndividuReportDto> getCompetencies(List<ParticipantCompetencyLibraryFinalResult> pcfrlist,List<IndividuReportDto> listIndividuReportDto, CompetencyCategory category)
	{
		List<IndividuReportDto> individureports =  new ArrayList<IndividuReportDto>();
		
		if(pcfrlist == null)
		{
			return individureports;
		}
		
		IndividuReportDto individuReportDto = null;
		int index = 0;
		for(ParticipantCompetencyLibraryFinalResult pcfr : pcfrlist)
		{
			if(!category.toString().equals(pcfr.getCompetencyLibrary().getCategory()))
			{
				continue;
			}
			index = index + 1;
			
			individuReportDto = new IndividuReportDto();
			
			individuReportDto.setNo(index);
			for(IndividuReportDto ir : listIndividuReportDto)
			{
				if(ir.getCompetencyId().equals(pcfr.getCompetencyLibrary().getId()))
				{
					individuReportDto.setCompetencyName(ir.getCompetencyName());
				}
			}
			
			
			if(individuReportDto.getCompetencyName() == null)
			{
				individuReportDto.setCompetencyName("-");
			}
			individuReportDto.setRl(4);
			individuReportDto.setIl(pcfr.getFinalRating());
			
			individuReportDto.setGap(individuReportDto.getIl() - individuReportDto.getRl());
			
			
			individureports.add(individuReportDto);
		}
		
		return individureports;
	}
	
	private JasperPrint getJasperPrint(Employee employee, Profile profile, List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult, List<IndividuReportDto> listIndividuReportDto) throws Exception
	{
	 	JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        JasperDesign jasperDesign = null;
        
        Map parameters = new HashMap();
        parameters.put("profile", profile);
        parameters.put("employee",employee);
        
        
        
        List<ParticipantCompetencyLibraryFinalResult> listNew = new ArrayList<ParticipantCompetencyLibraryFinalResult>();
		
		for(IndividuReportDto ir : listIndividuReportDto)
		{
			if(ir.isSelectedForReport())
			{
				for(ParticipantCompetencyLibraryFinalResult pcf :listParticipantCompetencyLibraryFinalResult)
				{
					if(pcf.getCompetencyLibrary().getId().equals(ir.getCompetencyId()))
					{
						listNew.add(pcf);
					}
				}
			}
		}
		
		listParticipantCompetencyLibraryFinalResult = listNew;
		
		List<IndividuReportDto> personal = getCompetencies(listParticipantCompetencyLibraryFinalResult,listIndividuReportDto, CompetencyCategory.PERSONAL);
		List<IndividuReportDto> leadership = getCompetencies(listParticipantCompetencyLibraryFinalResult,listIndividuReportDto, CompetencyCategory.LEADERSHIP);
		List<IndividuReportDto> functional = getCompetencies(listParticipantCompetencyLibraryFinalResult,listIndividuReportDto, CompetencyCategory.FUNCTIONAL);
		
        
        Map model = new HashMap();
        model.put("competencyCategory", "PERSONAL");
        model.put("listIndividu", personal);
        model.put("listFunctional", functional);
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "report/individu.vm", "UTF-8", model);
        
        parameters.put("assessementResult1", text);
        
        
        BufferedImage image = ImageIO.read(getClass().getResource("/report/logocover.jpg"));
        parameters.put("logo", image );
        
         try
		{
			Path path = Paths.get(configProperties.getProfileFilePath() + File.separator + employee.getId() + File.separator + employee.getProfilePicture() );
		    byte[] data = Files.readAllBytes(path);
			IImageProvider profilePicture = new ByteArrayImageProvider(data);
			parameters.put("profilePicture", profilePicture );
		}catch(Exception e){
		};
        
        List a = new ArrayList();
        a.add("");
        
        
        InputStream in =new FileInputStream("E:\\svn\\bniacs\\trunk\\report\\individu.jrxml");
        InputStream in =getClass().getClassLoader().getResourceAsStream("report/individu.jrxml");
        
        jasperDesign = JRXmlLoader.load(in);
        jasperReport = JasperCompileManager.compileReport(jasperDesign);
        jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(a));
       
        return jasperPrint;
	}
	
	public void generatePdf(String password, Employee employee, Profile profile,List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult, List<IndividuReportDto> listIndividuReportDto, OutputStream os)
	{
		try
	    {
	       
			JasperPrint jasperPrint  = getJasperPrint(employee, profile, listParticipantCompetencyLibraryFinalResult, listIndividuReportDto);
	        JRPdfExporter exporter = new JRPdfExporter();
	        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            
            
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setEncrypted(true);
            configuration.set128BitKey(true);
            configuration.setUserPassword(password);
            configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
            exporter.setConfiguration(configuration);
            
            exporter.exportReport();
            
	    }
	    catch(Exception ex)
	    {
	        System.out.println("EXCEPTION: "+ex);
	    }
	}
	
	
	public void generateDocx(Employee employee, Profile profile,List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult, List<IndividuReportDto> listIndividuReportDto, OutputStream os)
	{
		try
	    {
	       
			JasperPrint jasperPrint  = getJasperPrint(employee, profile, listParticipantCompetencyLibraryFinalResult, listIndividuReportDto);
			Exporter exporter = new JRDocxExporter();
			
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
			
			exporter.exportReport();
	    }
	    catch(Exception ex)
	    {
	        System.out.println("EXCEPTION: "+ex);
	    }
	}*/
}
