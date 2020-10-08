package com.dlabs.acs.service.impl.cap;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.cap.ICompetencyLibraryCapDao;
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.entity.cap.CompetencyLibraryCap;
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.cap.ICompetencyLibraryCapService;

@Service
@Transactional(readOnly = true)
public class CompetencyLibraryCapServiceImpl extends AbstractServiceImpl<CompetencyLibraryCap, Long> implements ICompetencyLibraryCapService{
	private Logger log = Logger.getLogger(CompetencyLibraryCapServiceImpl.class);
	
	@Autowired
	private ICompetencyLibraryCapDao competencylibrarycapDao;

	@Override
	public IAbstractDao<CompetencyLibraryCap, Long> getDao() {
		// TODO Auto-generated method stub
		return competencylibrarycapDao;
	}
	
	public Long countBySearch(String search){
		return competencylibrarycapDao.countBySearch(search);
	}
	
	public List<CompetencyLibraryCap> getBySearch(String search, int start, int num){
		return competencylibrarycapDao.getBySearch(search, start, num);
	}
	
	public List<CompetencyLibrary> getDistinctCompetencyLibrary(){
		return competencylibrarycapDao.getDistinctCompetencyLibrary();
	}
	
	public Cap getCapByCompetencyId(Long competencyLibraryId)
	{
		return competencylibrarycapDao.getCapByCompetencyId(competencyLibraryId);
	}
	
	public List<CompetencyLibrary> getCompetenciesByCapId(Long capId)
	{
		return competencylibrarycapDao.getCompetenciesByCapId(capId);
	}
}
