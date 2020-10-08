package com.dlabs.acs.service.impl.analysys;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.analysys.ICompetencyLibraryAnalysysDao;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.CompetencyLibraryAnalysys;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.analysys.ICompetencyLibraryAnalysysService;

@Service
@Transactional(readOnly = true)
public class CompetencyLibraryAnalysysServiceImpl extends AbstractServiceImpl<CompetencyLibraryAnalysys, Long> implements ICompetencyLibraryAnalysysService{
	private Logger log = Logger.getLogger(CompetencyLibraryAnalysysServiceImpl.class);
	
	@Autowired
	private ICompetencyLibraryAnalysysDao competencylibraryanalysysDao;

	@Override
	public IAbstractDao<CompetencyLibraryAnalysys, Long> getDao() {
		// TODO Auto-generated method stub
		return competencylibraryanalysysDao;
	}
	
	public Long countBySearch(String search){
		return competencylibraryanalysysDao.countBySearch(search);
	}
	
	public List<CompetencyLibraryAnalysys> getBySearch(String search, int start, int num){
		return competencylibraryanalysysDao.getBySearch(search, start, num);
	}
	
	public List<CompetencyLibrary> getDistinctCompetencyLibrary(){
		return competencylibraryanalysysDao.getDistinctCompetencyLibrary();
	}
	public List<Analysys> getAnalysisByCompetencyId(Long competencyLibraryId){
		return competencylibraryanalysysDao.getAnalysisByCompetencyId(competencyLibraryId);
	}
}
