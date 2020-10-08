package com.dlabs.acs.init.analysys;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.service.intf.analysys.IAnalysysService;
import com.dlabs.acs.util.excel.analysys.ExcelAnalysys;

@Component
public class InitAnalysys {
	@Autowired
	private IAnalysysService analysysService;
	
	public void generate()
	{
		if(analysysService.count().intValue() == 0)
		{
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/Analysys.xlsx");
			
			List<Analysys> list = ExcelAnalysys.parse(is);
			
			analysysService.save(list);
		}
	}
}
