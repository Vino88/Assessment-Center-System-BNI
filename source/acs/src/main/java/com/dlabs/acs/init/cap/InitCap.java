package com.dlabs.acs.init.cap;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.service.intf.cap.ICapService;
import com.dlabs.acs.util.excel.cap.ExcelCap;

@Component
public class InitCap {
	@Autowired
	private ICapService capService;
	
	public void generate()
	{
		if(capService.count().intValue() == 0)
		{
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/Cap.xlsx");
			
			List<Cap> list = ExcelCap.parse(is);
			
			capService.save(list);
		}
	}
}
