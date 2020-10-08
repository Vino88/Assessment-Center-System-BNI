package com.dlabs.acs.util.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.dto.assessementresult.report.IndividuReportDto;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.entity.competency.enumeration.CompetencyCategory;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.util.DateUtil;
import com.dlabs.acs.util.properties.ConfigProperties;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.document.DocumentKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.itext.extension.IPdfWriterConfiguration;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.formatter.NullImageBehaviour;

@Component
public class ReportIndividuUtil {
	@Autowired
	private ConfigProperties configProperties;
	
	private Employee getEmployeeData(Employee employee)
	{
		if(employee == null)
		{
			employee = new Employee(); 
		}
		Employee employeeData = new Employee();
		if(employee.getNik() == null)
		{
			employeeData.setNik("");
		}
		else
		{
			employeeData.setNik(employee.getNik());
		}
		
		if(employee.getFullName() == null)
		{
			employeeData.setFullName("");
		}
		else
		{
			employeeData.setFullName(employee.getFullName());
		}
		
		if(employee.getPlaceOfBirth() == null)
		{
			employeeData.setPlaceOfBirth("");
		}
		else
		{
			employeeData.setPlaceOfBirth(employee.getPlaceOfBirth());
		}
		
		if(employee.getDateOfBirth() == null)
		{
			employeeData.setDateOfBirth(new Date());
		}
		else
		{
			employeeData.setDateOfBirth(employee.getDateOfBirth());
		}
		return employeeData;
	}
	
	private Profile getProfileData(Profile profile)
	{
		Profile profileData = new Profile();
		if(profile == null)
		{
			profile = new Profile();
		}
		
		if(profile.getCurrentPositionName() == null)
		{
			profileData.setCurrentPositionName("");
		}
		else
		{
			profileData.setCurrentPositionName(profile.getCurrentPositionName());
		}
		
		
		if(profile.getStrong() == null)
		{
			profileData.setStrong("");
		}
		else
		{
			profileData.setStrong(profile.getStrong());
		}
		
		if(profile.getWeakness() == null)
		{
			profileData.setWeakness("");
		}
		else
		{
			profileData.setWeakness(profile.getWeakness());
		}
		
		if(profile.getPersonalCompetency() == null)
		{
			profileData.setPersonalCompetency("");
		}
		else
		{
			profileData.setPersonalCompetency(profile.getPersonalCompetency());
		}
		
		if(profile.getLeadershipCompetency() == null)
		{
			profileData.setLeadershipCompetency("");
		}
		else
		{
			profileData.setLeadershipCompetency(profile.getLeadershipCompetency());
		}
		
		if(profile.getFunctionalCompetency() == null)
		{
			profileData.setFunctionalCompetency("");
		}
		else
		{
			profileData.setFunctionalCompetency(profile.getFunctionalCompetency());
		}
		
		if(profile.getCareerAspiration() == null)
		{
			profileData.setCareerAspiration("");
		}
		else
		{
			profileData.setCareerAspiration(profile.getCareerAspiration());
		}
		
		return profileData;
	}
	
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
					individuReportDto.setCompetencyId(ir.getCompetencyId());
				}
			}
			
			
			if(individuReportDto.getCompetencyName() == null)
			{
				individuReportDto.setCompetencyName("-");
			}
			individuReportDto.setRl(4);
			individuReportDto.setIl(pcfr.getFinalRating());
			individuReportDto.setSelectedForReport(true);
			
			individuReportDto.setGap(individuReportDto.getIl() - individuReportDto.getRl());
			
			
			individureports.add(individuReportDto);
		}
		
		return individureports;
	}
	
	public void generateReport(final String password,String adminName, Employee employeeOriginal, Profile profileOriginal,Batch batch, List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult, List<IndividuReportDto> listIndividuReportDto, OutputStream responseOutputStream) {
		
		try {
			// 1) Load ODT file by filling Velocity template engine and cache
			// it to the registry
			
			InputStream in =getClass().getClassLoader().getResourceAsStream("report/individuPdf.docx");
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create context Java model
			IContext context = report.createContext();
			
			Employee employee = getEmployeeData(employeeOriginal);
			Profile profile = getProfileData(profileOriginal);
			
			DateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			
			context.put("adminName", adminName);
			context.put("now", sdf.format(new Date()));
			context.put("employee", employee);
			context.put("dateOfBirth", sdf.format(employee.getDateOfBirth()));
			context.put("profile", profile);
			context.put("ad", DateUtil.dateToStr(batch.getAssessementSecondHalfStartTime()));
			
			context.put("yyyy", Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
			
			context.put("fc", profile.getFunctionalCompetency());
			context.put("lc", profile.getLeadershipCompetency());
			context.put("pc", profile.getPersonalCompetency());
			context.put("ca", profile.getCareerAspiration());
			
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
			
			int i=1;
			for(IndividuReportDto ir : listIndividuReportDto)
			{
				if(ir.isSelectedForReport())
				{
					context.put("comflag"+i, 1);
				}
				else
				{
					context.put("comflag"+i, 0);
				}
				
				context.put("comval"+i, ir.getIl());
				i = i + 1;
			}
			
			for(IndividuReportDto ir : listIndividuReportDto)
			{
				if(ir.getCompetencyId() != null && ir.getCompetencyId().intValue() > 0)
				{
					if(ir.isSelectedForReport())
					{
						context.put("comflag"+ir.getCompetencyId().intValue(), 1);
					}
					else
					{
						context.put("comflag"+ir.getCompetencyId().intValue(), 0);
					}
				}
			}
			
			FieldsMetadata metadata = new FieldsMetadata();
			
			context.put("personal", personal);
			
			
			
			metadata.addFieldAsList("personal.no");
			metadata.addFieldAsList("personal.competencyName");
			metadata.addFieldAsList("personal.rl");
			metadata.addFieldAsList("personal.il");
			metadata.addFieldAsList("personal.gap");
			
			
			context.put("leadership", leadership);
			
			
			int persFlag1 = 0;
			for(IndividuReportDto ir :personal)
			{
				if(ir.isSelectedForReport())
				{
					persFlag1 = 1;
				}
				context.put("comval"+ir.getCompetencyId().intValue(), ir.getIl());
			}
			context.put("persFlag1", persFlag1);
			
			int persFlag2 = 0;
			for(IndividuReportDto ir :leadership)
			{
				if(ir.isSelectedForReport())
				{
					persFlag2 = 1;
				}
				context.put("comval"+ir.getCompetencyId().intValue(), ir.getIl());
			}
			context.put("persFlag2", persFlag2);
			
			
			int persFlag3 = 0;
			
			for(IndividuReportDto ir :functional)
			{
				if(ir.isSelectedForReport())
				{
					persFlag3 = 1;
				}
				context.put("comval"+ir.getCompetencyId().intValue(), ir.getIl());
			}
			context.put("persFlag3", persFlag3);
			
			
			metadata.addFieldAsList("leadership.no");
			metadata.addFieldAsList("leadership.competencyName");
			metadata.addFieldAsList("leadership.rl");
			metadata.addFieldAsList("leadership.il");
			metadata.addFieldAsList("leadership.gap");
			
			
			context.put("functional", functional);
			
			metadata.addFieldAsList("functional.no");
			metadata.addFieldAsList("functional.competencyName");
			metadata.addFieldAsList("functional.rl");
			metadata.addFieldAsList("functional.il");
			metadata.addFieldAsList("functional.gap");
			
			
			metadata.addFieldAsImage("profilephoto");
			metadata.setBehaviour( NullImageBehaviour.RemoveImageTemplate);
			
			report.setFieldsMetadata(metadata);
			
			
		    
			try
			{
				Path path = Paths.get(configProperties.getProfileFilePath() + File.separator + employee.getId() + File.separator + employee.getProfilePicture() );
			    byte[] data = Files.readAllBytes(path);
				IImageProvider profilephoto = new ByteArrayImageProvider(data);
				context.put("profilephoto", profilephoto);
			}catch(Exception e){
//				IImageProvider profilephoto = new ClassPathImageProvider(ReportIndividuUtil.class, "profile.png");
//				context.put("profilephoto", profilephoto);
			};
			// Export
			// 1) Create options ODT 2 PDF to select well converter form the registry
			PdfOptions pdfOptions = PdfOptions.create();
			pdfOptions.setConfiguration(new IPdfWriterConfiguration() {
			    // This is never called
			    public void configure(PdfWriter writer) {
			        try {
			            writer.setEncryption(password.getBytes(), password.getBytes(),
			                PdfWriter.ALLOW_COPY,
			                PdfWriter.STANDARD_ENCRYPTION_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
			            writer.createXmpMetadata();
			        } catch (DocumentException ex) {
			            throw new RuntimeException(ex);
			        }
			    }
			});
			
			Options options = Options.getTo(ConverterTypeTo.PDF).subOptions(pdfOptions);

			
			report.convert(context, options, responseOutputStream);  
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		}
	}
	
	public void convert() throws Exception{
		// 1) Create options ODT 2 PDF to select well converter form the registry
		Options options = Options.getFrom(DocumentKind.DOCX).to(ConverterTypeTo.PDF);

		// 2) Get the converter from the registry
		IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

		InputStream in= new FileInputStream(new File("E:\\individu.docx"));
		OutputStream out =  new FileOutputStream(new File("E:\\out.pdf"));
		converter.convert(in, out, options);     
		
/*		InputStream in =new FileInputStream("E:\\sample.docx");
		XWPFDocument document = new XWPFDocument(in);
		 
        // 2) Prepare Pdf options
        PdfOptions options = PdfOptions.create();

      
        PdfConverter.getInstance().convert(document, out, options);*/
	}
	
	
	public void generateReportOdt(String adminName, Employee employeeOriginal, Profile profileOriginal,Batch batch,List<ParticipantCompetencyLibraryFinalResult> listParticipantCompetencyLibraryFinalResult, List<IndividuReportDto> listIndividuReportDto, OutputStream responseOutputStream) {
		
		try {
			// 1) Load ODT file by filling Velocity template engine and cache
			// it to the registry
			
			InputStream in =getClass().getClassLoader().getResourceAsStream("report/individu.docx");
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create context Java model
			IContext context = report.createContext();
			
			Employee employee = getEmployeeData(employeeOriginal);
			Profile profile = getProfileData(profileOriginal);
			
			DateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			
			context.put("adminName", adminName);
			context.put("now", sdf.format(new Date()));
			context.put("employee", employee);
			context.put("dateOfBirth", sdf.format(employee.getDateOfBirth()));
			context.put("profile", profile);
			
			context.put("ad", DateUtil.dateToStr(batch.getAssessementSecondHalfStartTime()));
			context.put("yyyy", Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
			
			context.put("fc", profile.getFunctionalCompetency());
			context.put("lc", profile.getLeadershipCompetency());
			context.put("pc", profile.getPersonalCompetency());
			context.put("ca", profile.getCareerAspiration());
			
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
			
			int i=1;
			for(IndividuReportDto ir : listIndividuReportDto)
			{
				if(ir.isSelectedForReport())
				{
					context.put("comflag"+i, 1);
				}
				else
				{
					context.put("comflag"+i, 0);
				}
				context.put("comval"+i, ir.getIl());
				i = i + 1;
			}
			
			for(IndividuReportDto ir : listIndividuReportDto)
			{
				if(ir.getCompetencyId() != null && ir.getCompetencyId().intValue() > 0)
				{
					if(ir.isSelectedForReport())
					{
						context.put("comflag"+ir.getCompetencyId().intValue(), 1);
					}
					else
					{
						context.put("comflag"+ir.getCompetencyId().intValue(), 0);
					}					
				}
			}
			
			FieldsMetadata metadata = new FieldsMetadata();
			
			context.put("personal", personal);
			
			
			
			metadata.addFieldAsList("personal.no");
			metadata.addFieldAsList("personal.competencyName");
			metadata.addFieldAsList("personal.rl");
			metadata.addFieldAsList("personal.il");
			metadata.addFieldAsList("personal.gap");
			
			
			context.put("leadership", leadership);
			
			
			int persFlag1 = 0;
			for(IndividuReportDto ir :personal)
			{
				if(ir.isSelectedForReport())
				{
					persFlag1 = 1;
				}
				
				context.put("comval"+ir.getCompetencyId().intValue(), ir.getIl());
			}
			context.put("persFlag1", persFlag1);
			
			int persFlag2 = 0;
			for(IndividuReportDto ir :leadership)
			{
				if(ir.isSelectedForReport())
				{
					persFlag2 = 1;
				}
				context.put("comval"+ir.getCompetencyId().intValue(), ir.getIl());
			}
			context.put("persFlag2", persFlag2);
			
			
			int persFlag3 = 0;
			
			for(IndividuReportDto ir :functional)
			{
				if(ir.isSelectedForReport())
				{
					persFlag3 = 1;
				}
				context.put("comval"+ir.getCompetencyId().intValue(), ir.getIl());
			}
			context.put("persFlag3", persFlag3);
			
			
			metadata.addFieldAsList("leadership.no");
			metadata.addFieldAsList("leadership.competencyName");
			metadata.addFieldAsList("leadership.rl");
			metadata.addFieldAsList("leadership.il");
			metadata.addFieldAsList("leadership.gap");
			
			
			context.put("functional", functional);
			
			metadata.addFieldAsList("functional.no");
			metadata.addFieldAsList("functional.competencyName");
			metadata.addFieldAsList("functional.rl");
			metadata.addFieldAsList("functional.il");
			metadata.addFieldAsList("functional.gap");
			
			
			metadata.addFieldAsImage("profilephoto");
			metadata.setBehaviour( NullImageBehaviour.RemoveImageTemplate);
			
			report.setFieldsMetadata(metadata);
		    
			try
			{
				Path path = Paths.get(configProperties.getProfileFilePath() + File.separator + employee.getId() + File.separator + employee.getProfilePicture() );
			    byte[] data = Files.readAllBytes(path);
				IImageProvider profilephoto = new ByteArrayImageProvider(data);
				context.put("profilephoto", profilephoto);
			}catch(Exception e){
				e.printStackTrace();
			};
			
			report.process(context, responseOutputStream);
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Employee employee = new Employee();
		employee.setNik("123");
		employee.setFullName("abc");
		employee.setDateOfBirth(new Date());
		Profile profile = new Profile();
		profile.setLeadershipCompetency("abc");
		profile.setFunctionalCompetency("func");
		profile.setPersonalCompetency("pc");
		OutputStream os = new FileOutputStream("E:\\out.pdf");
		
		List<IndividuReportDto> listIndividuReportDto = new ArrayList<IndividuReportDto>();
		
		for(int i=1;i<=33;i++)
		{
			IndividuReportDto individuReportDto = new IndividuReportDto();
			individuReportDto.setCompetencyId(Long.valueOf(i));
			individuReportDto.setCompetencyName("a");
			individuReportDto.setSelectedForReport(true);
			listIndividuReportDto.add(individuReportDto);
		}
		listIndividuReportDto.get(listIndividuReportDto.size()-1).setSelectedForReport(false);
		
		List<ParticipantCompetencyLibraryFinalResult> listPcf = new ArrayList<ParticipantCompetencyLibraryFinalResult>();
		
		for(int i=1;i<=33;i++)
		{
			ParticipantCompetencyLibraryFinalResult pcf = new ParticipantCompetencyLibraryFinalResult();
			pcf.setCompetencyLibrary(new CompetencyLibrary());
			pcf.getCompetencyLibrary().setId(Long.valueOf(i));
			pcf.getCompetencyLibrary().setCompetencyName("bb");
			pcf.getCompetencyLibrary().setCategory(CompetencyCategory.PERSONAL.toString());
			listPcf.add(pcf);
		}
		
		Batch batch = new Batch();
//		new ReportIndividuUtil().convert();
//		new ReportIndividuUtil().convert("abc", "admin123", employee, null, null, os);
//		new ReportIndividuUtil().generateReportOdt("abc", employee, profile, listPcf, listIndividuReportDto, os);
		new ReportIndividuUtil().generateReport("aa", "abc", employee, profile,batch, listPcf, listIndividuReportDto, os);
	}
}
