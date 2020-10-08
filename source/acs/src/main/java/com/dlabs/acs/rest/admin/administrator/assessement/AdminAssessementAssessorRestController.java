package com.dlabs.acs.rest.admin.administrator.assessement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.service.intf.participant.IParticipantLogPostService;
import com.dlabs.acs.util.DateUtil;

@RestController()
@RequestMapping("rest/admin/administrator/assessement/assessor")
public class AdminAssessementAssessorRestController {
	private Logger logger = Logger.getLogger(AdminParticipantRestController.class);
	
	@Autowired
	private IParticipantLogPostService participantLogPostService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables pageListInstallment(@RequestParam("assessDone") int assessDone, @RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("assessorIds") String assessorIds,@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");
		
		DataTables dataTables = new DataTables();
		Long recordCount = null;
		List entities = Collections.EMPTY_LIST;
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		
		Date startTimeInDate = DateUtil.stringToDateByFmt(startTime, "ddMMyyyyHHmm");;
		Date endTimeInDate = DateUtil.stringToDateByFmt(endTime, "ddMMyyyyHHmm");;
		List<Long> assessorIdsInList = null;
		
		if(StringUtils.isNotEmpty(assessorIds))
		{
			assessorIdsInList = new ArrayList<Long>();
			try
			{
				for(String id : assessorIds.split(","))
				{
					assessorIdsInList.add(Long.valueOf(id));
				}
			}catch(Exception e){
				assessorIdsInList=new ArrayList<Long>();
			}
		}
		
		boolean withDateParam = false;
		boolean withAssessorParam = false;
		boolean withDone = false;
		
		if(startTimeInDate != null && endTimeInDate != null)
		{
			withDateParam = true;
		}
		
		if(assessorIdsInList != null && assessorIdsInList.size() > 0)
		{
			withAssessorParam =true;
		}
		
		if(assessDone == 0 || assessDone == 1)
		{
			withDone =true;
		}
		
		if(!withAssessorParam && !withDateParam && !withDone)
		{
			recordCount = (long) participantLogPostService.countAssessorDetail();
			entities = participantLogPostService.getAssessorDetail(start, length);
		}
		else if(withAssessorParam && !withDateParam && !withDone)
		{
			recordCount = (long) participantLogPostService.countAssessorDetailByAssessorId(assessorIdsInList);
			entities = participantLogPostService.getAssessorDetailByAssessorId(assessorIdsInList, start, length);
		}
		else if(!withAssessorParam && withDateParam && !withDone)
		{
			recordCount = (long) participantLogPostService.countAssessorDetailByBatchTime(startTimeInDate, endTimeInDate);
			entities = participantLogPostService.getAssessorDetailByBatchTime(startTimeInDate, endTimeInDate, start, length);
		}
		
		else if(withAssessorParam && withDateParam && !withDone)
		{
			recordCount = (long) participantLogPostService.countAssessorDetailByAssessorIdAndBatchTime(assessorIdsInList, startTimeInDate, endTimeInDate);
			entities = participantLogPostService.getAssessorDetailByAssessorIdAndBatchTime(assessorIdsInList, startTimeInDate, endTimeInDate, start, length);
		}
		
		
		
		else if(!withAssessorParam && !withDateParam && withDone)
		{
			recordCount = (long) participantLogPostService.countAssessorDetailByDone(assessDone);
			entities = participantLogPostService.getAssessorDetailByDone(assessDone, start, length);
		}
		
		else if(withAssessorParam && !withDateParam && withDone)
		{
			recordCount = (long) participantLogPostService.countAssessorDetailByAssessorIdByDone(assessorIdsInList, assessDone);
			entities = participantLogPostService.getAssessorDetailByAssessorIdByDone(assessorIdsInList, assessDone, start, length);
		}
		
		else if(!withAssessorParam && withDateParam && withDone)
		{
			recordCount = (long) participantLogPostService.countAssessorDetailByBatchTimeByDone(startTimeInDate, endTimeInDate, assessDone);
			entities = participantLogPostService.getAssessorDetailByBatchTimeByDone(startTimeInDate, endTimeInDate, assessDone, start, length);
		}
		
		else if(withAssessorParam && withDateParam && withDone)
		{
			recordCount = (long) participantLogPostService.countAssessorDetailByAssessorIdAndBatchTimeByDone(assessorIdsInList, startTimeInDate, endTimeInDate, assessDone);
			entities = participantLogPostService.getAssessorDetailByAssessorIdAndBatchTimeByDone(assessorIdsInList, startTimeInDate, endTimeInDate, assessDone, start, length);
		}

		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		
	    dataTables.setData(entities);
	    
	    return dataTables;
	}
}
