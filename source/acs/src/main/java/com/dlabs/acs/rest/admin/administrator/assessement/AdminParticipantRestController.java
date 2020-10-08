package com.dlabs.acs.rest.admin.administrator.assessement;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.dto.assessementresult.report.ListIndividuReportDto;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.enumeration.LogDuringStatus;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.assessement.IParticipantLogDuringService;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;
import com.dlabs.acs.util.DateUtil;

@RestController()
@RequestMapping("rest/admin/administrator/assessement/participant")
public class AdminParticipantRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminParticipantRestController.class);
	@Autowired
	private IParticipantService participantService;
	@Autowired
	private IParticipantLogPostService participantLogPostService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<Map<String,Object>> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Map<String,Object>> dataTables = new DataTables<Map<String,Object>>();
		
		Long recordCount = null;
		
		recordCount = (long) participantService.countAllBySearch(sSearch);
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		List<Map<String,Object>> entities = participantService.getAllBySearch(sSearch, start, length);
	    dataTables.setData(entities);
	    
	    return dataTables;
	}
	
	
	@RequestMapping(value = "/listbybatch/{id}", method = RequestMethod.GET)
	public DataTables<Map<String,Object>> listbybatch(@PathVariable("id") Long batchid, @RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Map<String,Object>> dataTables = new DataTables<Map<String,Object>>();
		
		Long recordCount = null;
		
		recordCount = (long) participantService.countByBatchBySearch(batchid, sSearch);
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		List<Map<String,Object>> entities = participantService.getByBatchBySearch(batchid, sSearch, start, length);
	    dataTables.setData(entities);

	    return dataTables;
		
	}
	
	
	@RequestMapping(value = "/participantfinish", method = RequestMethod.GET)
	public DataTables<Participant> participantfinish(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Participant> dataTables = new DataTables<Participant>();
		
		Long recordCount = null;
		
		recordCount = (long) participantService.countParticipantFinish(sSearch);
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		List<Participant> entities = participantService.getParticipantFinish(sSearch, start, length);
	    dataTables.setData(entities);

	    return dataTables;
	}
	
	@RequestMapping(value = "/assingtome/{id}", method = RequestMethod.GET)
	public DataTables<Participant> assingtome(@PathVariable("id") Long userid,@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Participant> dataTables = new DataTables<Participant>();
		
		Long recordCount = null;
		
		recordCount = (long) participantLogPostService.countParticipantAssignToMe(userid, sSearch);
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		List<Participant> entities = participantLogPostService.getParticipantAssignToMe(userid, sSearch, start, length);
	    dataTables.setData(entities);

	    return dataTables;
	}
	
	
	@RequestMapping(value = "/participanthistory", method = RequestMethod.GET)
	public DataTables<Participant> participantHistory(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<Participant> dataTables = new DataTables<Participant>();
		
		Long recordCount = null;
		
		recordCount = (long) participantLogPostService.countParticipantHistory(sSearch);
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		List<Participant> entities = participantLogPostService.getParticipantHistory(sSearch, start, length);
	    dataTables.setData(entities);

	    return dataTables;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/individualreport/downloaddata/{id}")
	public String downloaddata(@PathVariable("id") Long id,@Valid @ModelAttribute("dataForm") ListIndividuReportDto dataForm, HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().setAttribute(SessionConstants.ADMIN_REPORT_COMPETENCY_SELECT, dataForm);
		
		return "success";
	}
	
	@Autowired
	private IParticipantLogDuringService participantLogDuringService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/modifylog/{id}")
	public boolean modifyLog(@PathVariable("id") Long id, String pkEndTime, String lcbEndTime, String inbasketEndTime, String analysysEndTime, String logDuringStatus
			, String inbasketReadMemoEndTime, String visionEndTime, String participantStatus, String aspirationEndTime)
	{
		try
		{
			Date pk = DateUtil.stringToDateTime(pkEndTime);
			Date lcb = DateUtil.stringToDateTime(lcbEndTime);
			Date inbasket = DateUtil.stringToDateTime(inbasketEndTime);
			Date analysys = DateUtil.stringToDateTime(analysysEndTime);
			Date aspiration = DateUtil.stringToDateTime(aspirationEndTime);
			
			
			Date inbasketReadMemo= DateUtil.stringToDateTime(inbasketReadMemoEndTime);
			Date vision = DateUtil.stringToDateTime(visionEndTime);
			
			
			ParticipantLogDuring participantLogDuring = participantLogDuringService.getByParticipantIdFirst(id);
			participantLogDuring.setCapEndTime(pk);
			participantLogDuring.setLcbEndTime(lcb);
			participantLogDuring.setInbasketEndTime(inbasket);
			participantLogDuring.setAnalysysEndTime(analysys);
			participantLogDuring.setInbasketReadMemoEndTime(inbasketReadMemo);
			participantLogDuring.setVisionEndTime(vision);
			participantLogDuring.setAspirationEndTime(aspiration);
			LogDuringStatus lds = LogDuringStatus.valueOf(logDuringStatus);
			participantLogDuring.setLogDuringStatus(lds);
			
			if(LogDuringStatus.CAP.equals(lds))
			{
				participantLogDuring.setCapFinishTime(null);
			}
			if(LogDuringStatus.LCB.equals(lds))
			{
				participantLogDuring.setLcbFinishTime(null);
			}
			
			if(LogDuringStatus.INBASKET.equals(lds))
			{
				participantLogDuring.setInbasketFinishTime(null);
			}
			
			if(LogDuringStatus.ANALYSYS.equals(lds))
			{
				participantLogDuring.setAnalysysFinishTime(null);
			}
			if(LogDuringStatus.VISION_SPEECH.equals(lds))
			{
				participantLogDuring.setVisionFinishTime(null);
			}
			
			participantLogDuringService.saveOrUpdate(participantLogDuring);
			
			Participant participant = participantService.findById(id);
			participant.setParticipantStatus(ParticipantStatus.valueOf(participantStatus));
			participantService.saveOrUpdate(participant);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		System.out.println("logDuringStatus"+logDuringStatus);
		return true;
	}
}
