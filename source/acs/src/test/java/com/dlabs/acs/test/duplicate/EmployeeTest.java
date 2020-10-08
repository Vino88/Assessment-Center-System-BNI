package com.dlabs.acs.test.duplicate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantAnalysys;
import com.dlabs.acs.entity.assessement.ParticipantCap;
import com.dlabs.acs.entity.assessement.ParticipantInbasketInboxInbasketQuestion;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.enumeration.LogDuringStatus;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.assessementresult.ParticipantCompetencyLibraryFinalResult;
import com.dlabs.acs.entity.factsheet.Profile;
import com.dlabs.acs.entity.masterdata.Employee;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.entity.participant.ParticipantLogPost;
import com.dlabs.acs.service.intf.assessement.IParticipantAnalysysService;
import com.dlabs.acs.service.intf.assessement.IParticipantCapService;
import com.dlabs.acs.service.intf.assessement.IParticipantInbasketInboxInbasketQuestionService;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.assessementresult.IParticipantCompetencyLibraryFinalResultService;
import com.dlabs.acs.service.intf.factsheet.IProfileService;
import com.dlabs.acs.service.intf.masterdata.IEmployeeService;
import com.dlabs.acs.service.intf.participant.IBatchService;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext.xml",  "classpath:spring/sql-view.xml", })
@WebAppConfiguration()
public class EmployeeTest {
	
	private int index = 1;
	private String npp = "";
	
	private static final String NPP_PREFIX = "PH0000";
	
	private static final String BATCH = "Batch PA 001";
	private static int loop = 2;
	
	
	private static final String NIK_TO_COPY = "PD00001";
	
	
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IBatchService batchService;
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	@Autowired
	private IParticipantLogPostService participantLogPostService;
	@Autowired
	private IProfileService profileService;
	
	@Autowired
	private IParticipantCapService participantCapService;
	@Autowired
	private IParticipantInbasketInboxInbasketQuestionService participantInbasketInboxInbasketQuestionServcie;
	@Autowired
	private IParticipantAnalysysService participantAnalysysService;
	
	@Autowired
	private IParticipantCompetencyLibraryFinalResultService participantCompetencyLibraryFinalResultService;
	
	private CommonFields commonFields;
	private Date now = new Date();
	
	public void setCommonFields()
	{
		
		commonFields = new CommonFields();
		commonFields.setCreatedBy("dummy");
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy("dummy");
		commonFields.setLastModifiedDate(now);
	}
	
	private List<ParticipantCap> listParticipantCap;
	private Participant participanToCopy;
	private List<ParticipantCompetencyLibraryFinalResult> listPclfr;
	List<ParticipantAnalysys> listAnalysys;
	List<ParticipantInbasketInboxInbasketQuestion> listInbasket;
	private void getData()
	{
		participanToCopy = participantService.getByNikAndActive(NIK_TO_COPY);
		listParticipantCap = participantCapService.getByParticipantId(participanToCopy.getId());
		listPclfr = participantCompetencyLibraryFinalResultService.getByParticipantId(participanToCopy.getId());
		listAnalysys = participantAnalysysService.getByParticipantId(participanToCopy.getId());
		listInbasket = participantInbasketInboxInbasketQuestionServcie.getByParticipantId(participanToCopy.getId());
	}
	

	@Test
	public void generate()
	{
		setCommonFields();
		getData();
		Batch batch = participanToCopy.getBatch();
		System.out.println("GEnERATE::BEGIN");
		for(int i=1;i<=loop;i++)
		{
			System.out.println("index-"+index);
			addParticipant(batch);
			index = index+1;
		}
		System.out.println("GEnERATE::END");
		
	}

	public void addParticipant(Batch batch)
	{
		Participant participant = new Participant();
		participant.setCommonFields(commonFields);
		
		
		Employee employee = addEmployee();
		
		participant.setBatch(batch);
		participant.setEmployee(employee);
		participant.setEmail(employee.getEmail());
		participant.setNik(employee.getNik());
		participant.setFullName(employee.getFullName());
		
		participant.setParticipantStatus(ParticipantStatus.PARTICIPANT_FINISH);
		
		participant.setPassword("admin123");
		participant.setProfile(addProfile());
		participantService.save(participant);
		
		participant.setParticipantLogDuring(addParticipantLogDuring(participant));
		
		participantService.saveOrUpdate(participant);
		
		addParticipantLogPost(participant);
		
		
		addCap(participant, participanToCopy);
		addInbasket(participant, participanToCopy);
		addAnalysis(participant, participanToCopy);
		addFinalResult(participant, participanToCopy);
	}
	
	public void addFinalResult(Participant participant, Participant participanToCopy)
	{
		
		ParticipantCompetencyLibraryFinalResult pf = null;
		
		List<ParticipantCompetencyLibraryFinalResult> list = new ArrayList<ParticipantCompetencyLibraryFinalResult>();
		
		for(ParticipantCompetencyLibraryFinalResult pfToCopy : listPclfr)
		{
			pf = new ParticipantCompetencyLibraryFinalResult();
			pf.setCommonFields(commonFields);
			pf.setParticipant(participant);
			
			pf.setCompetencyLibrary(pfToCopy.getCompetencyLibrary());
			pf.setLcbRating(pfToCopy.getLcbRating());
			
			list.add(pf);
			
			
		}
		
		participantCompetencyLibraryFinalResultService.save(list);
	}
	
	public void addAnalysis(Participant participant, Participant participanToCopy)
	{
		
		ParticipantAnalysys pa = null;
		List<ParticipantAnalysys> list = new ArrayList<ParticipantAnalysys>();
		for(ParticipantAnalysys paToCopy : listAnalysys)
		{
			pa = new ParticipantAnalysys();
			pa.setCommonFields(commonFields);
			pa.setAnalysys(paToCopy.getAnalysys());
			pa.setAnswer(paToCopy.getAnswer());
			pa.setParticipant(participant);
			list.add(pa);
			
		}
		participantAnalysysService.save(list);
		
	}
	public void addInbasket(Participant participant, Participant participanToCopy)
	{
		
		ParticipantInbasketInboxInbasketQuestion pii = null;
		List<ParticipantInbasketInboxInbasketQuestion> list = new ArrayList<ParticipantInbasketInboxInbasketQuestion>();
		for(ParticipantInbasketInboxInbasketQuestion piiToCopy : listInbasket)
		{
			pii = new ParticipantInbasketInboxInbasketQuestion();
			
			pii.setCommonFields(commonFields);
			pii.setAnswer(piiToCopy.getAnswer());
			pii.setInbasketInboxInbasketQuestion(piiToCopy.getInbasketInboxInbasketQuestion());
			pii.setParticipant(participant);
			list.add(pii);
			
		}
		participantInbasketInboxInbasketQuestionServcie.save(list);
	}
	public void addCap(Participant participant, Participant participanToCopy)
	{
		
		
		
		
		ParticipantCap participantCap = null;
		List<ParticipantCap> list = new ArrayList<ParticipantCap>();
		for(ParticipantCap participantCapToCopy : listParticipantCap)
		{
			participantCap = new ParticipantCap();
			
			participantCap.setParticipant(participant);
			participantCap.setCap(participantCapToCopy.getCap());
			
			participantCap.setCommonFields(commonFields);
			
			
			participantCap.setAnsAction(participantCapToCopy.getAnsAction());
			participantCap.setAnsResult(participantCapToCopy.getAnsResult());
			participantCap.setAnsSituation(participantCapToCopy.getAnsSituation());
			list.add(participantCap);
			
		}
		participantCapService.save(list);
	}
	
	
	
	
	public Profile addProfile()
	{
		Profile profile = new Profile();
		profile.setCommonFields(commonFields);
		
		profile.setCareerAspiration("career aspiration");
		
		profile.setCurrentDirectSupervisor("direct supervisor");
		profile.setCurrentPeriode("10");
		profile.setCurrentPositionName("AVP");
		profile.setWorkingArea("workingarea");
		profileService.save(profile);
		
		return profile;
	}
	public void addParticipantLogPost(Participant participant)
	{
		ParticipantLogPost participantLogPost = new ParticipantLogPost();
		participantLogPost.setCommonFields(commonFields);
		
		participantLogPost.setParticipant(participant);
		participantLogPostService.save(participantLogPost);
	}
	public ParticipantLogDuring addParticipantLogDuring(Participant participant)
	{
		ParticipantLogDuring participantLogDuring = new ParticipantLogDuring();
		
		participantLogDuring.setCommonFields(commonFields);
		participantLogDuring.setLogDuringStatus(LogDuringStatus.FINISH);
		participantLogDuring.setParticipant(participant);
		participantLogDuringService.save(participantLogDuring);
		return participantLogDuring;
	}
	
	
	public Employee addEmployee()
	{
		npp = NPP_PREFIX + index;
		
		Employee employee = new Employee();
		employee.setCommonFields(commonFields);
		
		employee.setEmail(npp+"@dayalima.com");
		employee.setDateOfBirth(now);
		employee.setNik(npp);
		employee.setFullName("Peserta " + index);
		employee.setCurrentDirectSupervisor("direct supervisor");
		employee.setCurrentPeriode("10");
		employee.setCurrentPositionName("AVP");
		employee.setPhone("123456789");
		employee.setPlaceOfBirth("Jakarta");
		employee.setWorkingArea("workingarea");
		employeeService.save(employee);
		
		return employee;
	}
	
	public Batch addBatch()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2018);
		
		Batch batch = new Batch();
		batch.setDescription(BATCH);
		batch.setAssessementFirstHalfStartTime(new Date());
		batch.setAssessementFirstHalfEndTime(new Date());
		batch.setAssessementSecondHalfStartTime(new Date());
		batch.setAssessementFinishTime(calendar.getTime());
		batch.setCommonFields(commonFields);
		batchService.save(batch);
		
		return batch;
	}
}
