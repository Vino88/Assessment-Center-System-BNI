package com.dlabs.acs.init.lcb;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.entity.lcb.Lcb;
import com.dlabs.acs.service.intf.lcb.ILcbService;
import com.dlabs.acs.util.excel.lcb.ExcelLcb;

@Component
public class InitLcb {
	@Autowired
	private ILcbService lcbService;
	
	public void generate()
	{
		if(lcbService.count().intValue() == 0)
		{
			InputStream is =getClass().getClassLoader().getResourceAsStream("init/Lcb.xlsx");
//			InputStream is =getClass().getClassLoader().getResourceAsStream("init/Scb.xlsx");
			
			List<Lcb> list = ExcelLcb.parse(is);
			
			lcbService.save(list);
		}
	}
}
