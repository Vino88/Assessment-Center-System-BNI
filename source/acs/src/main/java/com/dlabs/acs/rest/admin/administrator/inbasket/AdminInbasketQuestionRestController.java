package com.dlabs.acs.rest.admin.administrator.inbasket;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlabs.acs.dto.DataTables;
import com.dlabs.acs.entity.inbasket.InbasketQuestion;
import com.dlabs.acs.rest.AbstractRest;
import com.dlabs.acs.service.intf.inbasket.IInbasketQuestionService;

@RestController()
@RequestMapping("rest/admin/administrator/inbasket/inbasketquestion")
public class AdminInbasketQuestionRestController extends AbstractRest{
	private Logger logger = Logger.getLogger(AdminInbasketQuestionRestController.class);
	@Autowired
	private IInbasketQuestionService inbasketquestionService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public DataTables<InbasketQuestion> pageListInstallment(@RequestParam("start") int start,@RequestParam("length")  int length, @RequestParam("search[value]") String sSearch)
			throws Exception {
		logger.debug("BEGIN");

		DataTables<InbasketQuestion> dataTables = new DataTables<InbasketQuestion>();
		
		Long recordCount = null;
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    recordCount = (long) inbasketquestionService.count();
		}
		else {
		    recordCount = (long) inbasketquestionService.countBySearch(sSearch);
		}
		
		if (length == 0)
			length = DataTables.PAGING_MAX_RECORD;
		dataTables.setRecordsFiltered(recordCount);
		dataTables.setRecordsTotal(recordCount);
		
		if (sSearch == null || sSearch.trim().length() == 0){
		    List<InbasketQuestion> entities = inbasketquestionService.getAllActive(start, length);
		    
		     dataTables.setData(entities);

		    return dataTables;
		}
		else {
			List<InbasketQuestion> entities = inbasketquestionService.getBySearch(sSearch, start, length);
		    
		    dataTables.setData(entities);
		    return dataTables;
		}
		
	}
}
