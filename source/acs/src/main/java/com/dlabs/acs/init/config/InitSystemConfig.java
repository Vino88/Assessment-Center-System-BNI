package com.dlabs.acs.init.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dlabs.acs.constants.SystemConfigConstants;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.config.SystemConfig;
import com.dlabs.acs.service.intf.config.ISystemConfigService;
import com.dlabs.acs.util.StringPool;

@Component
public class InitSystemConfig {
	@Autowired
	private ISystemConfigService systemConfigService;
	
	public void generate(){
		if( systemConfigService.count() == 0)
		{
			Date now = new Date();
			
			CommonFields commonFields = new CommonFields();
			commonFields.setCreatedBy(StringPool.SCHEDULER);
			commonFields.setCreatedDate(now);
			commonFields.setLastModifiedBy(StringPool.SCHEDULER);
			commonFields.setLastModifiedDate(now);
			
			SystemConfig systemConfig = new SystemConfig();
			systemConfig.setCommonFields(commonFields);
			systemConfig.setModule(SystemConfigConstants.TIMER.MODULE_NAME);
			
			systemConfig.setSystemCode(SystemConfigConstants.TIMER.TIMER_CAP_MINUTES);
			systemConfig.setSystemValue(""+SystemConfigConstants.TIMER.DEFAULT_CAP_MINUTES);
			systemConfig.setDescription("Timer in CAP");
			
			systemConfigService.save(systemConfig);
			
			
			systemConfig = new SystemConfig();
			systemConfig.setCommonFields(commonFields);
			systemConfig.setModule(SystemConfigConstants.TIMER.MODULE_NAME);
			
			systemConfig.setSystemCode(SystemConfigConstants.TIMER.TIMER_LI_MINUTES);
			systemConfig.setSystemValue(""+SystemConfigConstants.TIMER.DEFAULT_LI_MINUTES);
			systemConfig.setDescription("Timer in Leadership Inventory");
			
			systemConfigService.save(systemConfig);
			
			
			systemConfig = new SystemConfig();
			systemConfig.setCommonFields(commonFields);
			systemConfig.setModule(SystemConfigConstants.TIMER.MODULE_NAME);
			
			systemConfig.setSystemCode(SystemConfigConstants.TIMER.TIMER_INBASKET);
			systemConfig.setSystemValue(""+SystemConfigConstants.TIMER.DEFAULT_INBASKET_MINUTES);
			
			systemConfig.setDescription("Timer in Inbasket");
			
			systemConfigService.save(systemConfig);
			
			
			
			systemConfig = new SystemConfig();
			systemConfig.setCommonFields(commonFields);
			systemConfig.setModule(SystemConfigConstants.TIMER.MODULE_NAME);
			
			systemConfig.setSystemCode(SystemConfigConstants.TIMER.TIMER_READ_MEMO);
			systemConfig.setSystemValue(""+SystemConfigConstants.TIMER.DEFAULT_READ_MEMO);
			systemConfig.setDescription("Timer Read Memo in Simulation");
			
			systemConfigService.save(systemConfig);
			
			
			
			systemConfig = new SystemConfig();
			systemConfig.setCommonFields(commonFields);
			systemConfig.setModule(SystemConfigConstants.TIMER.MODULE_NAME);
			
			systemConfig.setSystemCode(SystemConfigConstants.TIMER.TIMER_ANALYSYS_MINUTES);
			systemConfig.setSystemValue(""+SystemConfigConstants.TIMER.DEFAULT_ANALYSYS_MINUTES);
			systemConfig.setDescription("Timer in Analysis");
			
			systemConfigService.save(systemConfig);
			
			
			
			
			systemConfig = new SystemConfig();
			systemConfig.setCommonFields(commonFields);
			systemConfig.setModule(SystemConfigConstants.TIMER.MODULE_NAME);
			
			systemConfig.setSystemCode(SystemConfigConstants.TIMER.TIMER_VISION_SPEECH_ALL);
			systemConfig.setSystemValue(""+SystemConfigConstants.TIMER.DEFAULT_VISION_SPEECH_ALL);
			systemConfig.setDescription("Timer in Vision Speech Overall");
			
			systemConfigService.save(systemConfig);
			
			
			
			systemConfig = new SystemConfig();
			systemConfig.setCommonFields(commonFields);
			systemConfig.setModule(SystemConfigConstants.TIMER.MODULE_NAME);
			
			systemConfig.setSystemCode(SystemConfigConstants.TIMER.TIMER_VISION_SPEECH_RECORD);
			systemConfig.setSystemValue(""+SystemConfigConstants.TIMER.DEFAULT_VISION_SPEECH_RECORD);
			systemConfig.setDescription("Timer in Vision Speech Record Video");
			
			systemConfigService.save(systemConfig);
			
			
			
			systemConfig = new SystemConfig();
			systemConfig.setCommonFields(commonFields);
			systemConfig.setModule(SystemConfigConstants.TIMER.MODULE_NAME);
			
			systemConfig.setSystemCode(SystemConfigConstants.TIMER.TIMER_SIMULATION_II);
			systemConfig.setSystemValue(""+SystemConfigConstants.TIMER.DEFAULT_TIMER_SIMULATION_II);
			systemConfig.setDescription("Timer in Session 2. (LI, Inbasket, Analysis)");
			
			systemConfigService.save(systemConfig);
			
		}
	}
}
