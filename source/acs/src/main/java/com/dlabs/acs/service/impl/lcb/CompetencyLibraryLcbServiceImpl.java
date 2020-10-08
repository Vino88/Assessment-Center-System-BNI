package com.dlabs.acs.service.impl.lcb;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.lcb.ICompetencyLibraryLcbDao;
import com.dlabs.acs.entity.lcb.CompetencyLibraryLcb;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.lcb.ICompetencyLibraryLcbService;

@Service
@Transactional(readOnly = true)
public class CompetencyLibraryLcbServiceImpl extends AbstractServiceImpl<CompetencyLibraryLcb, Long> implements ICompetencyLibraryLcbService{
	private Logger log = Logger.getLogger(CompetencyLibraryLcbServiceImpl.class);
	
	@Autowired
	private ICompetencyLibraryLcbDao competencylibrarylcbDao;

	@Override
	public IAbstractDao<CompetencyLibraryLcb, Long> getDao() {
		// TODO Auto-generated method stub
		return competencylibrarylcbDao;
	}
	
	public Long countBySearch(String search){
		return competencylibrarylcbDao.countBySearch(search);
	}
	
	public List<CompetencyLibraryLcb> getBySearch(String search, int start, int num){
		return competencylibrarylcbDao.getBySearch(search, start, num);
	}
	
	public CompetencyLibraryLcb getByCompetencyIdAndLcbId(Long competencyId, Long lcbId){
		return competencylibrarylcbDao.getByCompetencyIdAndLcbId(competencyId, lcbId);
	}
}
