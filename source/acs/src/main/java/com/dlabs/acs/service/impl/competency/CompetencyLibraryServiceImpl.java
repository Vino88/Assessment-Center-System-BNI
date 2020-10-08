package com.dlabs.acs.service.impl.competency;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.competency.ICompetencyLibraryDao;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryService;

@Service
@Transactional(readOnly = true)
public class CompetencyLibraryServiceImpl extends AbstractServiceImpl<CompetencyLibrary, Long> implements ICompetencyLibraryService{
	private Logger log = Logger.getLogger(CompetencyLibraryServiceImpl.class);
	
	@Autowired
	private ICompetencyLibraryDao competencylibraryDao;

	@Override
	public IAbstractDao<CompetencyLibrary, Long> getDao() {
		// TODO Auto-generated method stub
		return competencylibraryDao;
	}
	
	public Long countBySearch(String search){
		return competencylibraryDao.countBySearch(search);
	}
	
	public List<CompetencyLibrary> getBySearch(String search, int start, int num){
		return competencylibraryDao.getBySearch(search, start, num);
	}
	public CompetencyLibrary getByCompetencyName(String competencyName)
	{
		return competencylibraryDao.getByCompetencyName(competencyName);
	}
}
