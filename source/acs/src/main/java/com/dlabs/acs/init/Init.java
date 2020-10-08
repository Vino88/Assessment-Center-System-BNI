package com.dlabs.acs.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.dlabs.acs.init.analysys.InitAnalysys;
import com.dlabs.acs.init.analysys.InitCompetencyLibraryAnalysys;
import com.dlabs.acs.init.cap.InitCap;
import com.dlabs.acs.init.cap.InitCompetencyLibraryCap;
import com.dlabs.acs.init.competency.InitCompetencyLibrary;
import com.dlabs.acs.init.competency.InitCompetencyLibraryBehaviour;
import com.dlabs.acs.init.competency.InitCompetencyLibraryWritingAssistance;
import com.dlabs.acs.init.config.InitSystemConfig;
import com.dlabs.acs.init.inbasket.InitCompetencyLibraryInbasketInboxInbasketQuestion;
import com.dlabs.acs.init.inbasket.InitInbasketInbox;
import com.dlabs.acs.init.inbasket.InitInbasketInboxInbasketQuestion;
import com.dlabs.acs.init.inbasket.InitInbasketQuestion;
import com.dlabs.acs.init.lcb.InitCompetencyLibraryLcb;
import com.dlabs.acs.init.lcb.InitLcb;
import com.dlabs.acs.util.properties.ConfigProperties;

@Configuration
public class Init {
	@Autowired
	private InitCompetencyLibrary initCompetencyLibrary;
	@Autowired
	private InitLcb initLcb;
	@Autowired
	private InitCompetencyLibraryLcb initCompetencyLibraryLcb;
	@Autowired
	private InitCap initCap;
	@Autowired
	private InitCompetencyLibraryCap initCompetencyLibraryCap;
	
	@Autowired
	private InitInbasketInbox initInbasketInbox;
	@Autowired
	private InitInbasketQuestion initInbasketQuestion;
	@Autowired
	private InitInbasketInboxInbasketQuestion initInbasketInboxInbasketQuestion;
	@Autowired
	private InitCompetencyLibraryInbasketInboxInbasketQuestion initCompetencyLibraryInbasketInboxInbasketQuestion;
	
	@Autowired
	private InitAnalysys initAnalysys;
	@Autowired
	private InitCompetencyLibraryAnalysys initCompetencyLibraryAnalysys;
	
	@Autowired
	private InitUserAdmin InitUserAdmin;
	
	@Autowired
	private InitCompetencyLibraryWritingAssistance initCompetencyLibraryWritingAssistance;
	
	@Autowired
	private InitCompetencyLibraryBehaviour initCompetencyLibraryBehaviour;
	
	@Autowired
	private InitSystemConfig initSystemConfig;
	
	@Autowired
	private ConfigProperties configProperties;
	
	@PostConstruct
	public void generateCompetencyLibrary()
	{
		if(configProperties.isInitGenerate())
		{
			initCompetencyLibrary.generte();
			initLcb.generate();
			initCompetencyLibraryLcb.generate();
			
			initCap.generate();
			initCompetencyLibraryCap.generate();
			
			
			initInbasketInbox.generate();
			initInbasketQuestion.generate();
			initInbasketInboxInbasketQuestion.generate();
			initCompetencyLibraryInbasketInboxInbasketQuestion.generate();
			
			initAnalysys.generate();
			initCompetencyLibraryAnalysys.generate();
			
			InitUserAdmin.generate();
			
			initCompetencyLibraryWritingAssistance.generate();
			initCompetencyLibraryBehaviour.generate();
			
			initSystemConfig.generate();
		}
		
		
	}
}
