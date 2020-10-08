package com.dlabs.acs.init.inbasket;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.entity.inbasket.InbasketInbox;
import com.dlabs.acs.service.intf.inbasket.IInbasketInboxService;
import com.dlabs.acs.util.excel.inbasket.ExcelInbasketInbox;

@Component
public class InitInbasketInbox {
	@Autowired
	private IInbasketInboxService inbasketInboxService;
	
	public void generate()
	{
		if(inbasketInboxService.count().intValue() == 0)
		{
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/InbasketInbox.xlsx");
			
			List<InbasketInbox> list = ExcelInbasketInbox.parse(is);
			
			inbasketInboxService.save(list);
		}
	}
}
