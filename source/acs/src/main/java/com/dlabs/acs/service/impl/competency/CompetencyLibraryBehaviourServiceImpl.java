package com.dlabs.acs.service.impl.competency;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.competency.ICompetencyLibraryBehaviourDao;
import com.dlabs.acs.entity.competency.CompetencyLibraryBehaviour;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryBehaviourService;

@Service
@Transactional(readOnly = true)
public class CompetencyLibraryBehaviourServiceImpl extends AbstractServiceImpl<CompetencyLibraryBehaviour, Long> implements ICompetencyLibraryBehaviourService{
	private Logger log = Logger.getLogger(CompetencyLibraryBehaviourServiceImpl.class);
	
	@Autowired
	private ICompetencyLibraryBehaviourDao competencylibrarybehaviourDao;

	@Override
	public IAbstractDao<CompetencyLibraryBehaviour, Long> getDao() {
		// TODO Auto-generated method stub
		return competencylibrarybehaviourDao;
	}
	
	public Long countBySearch(String search){
		return competencylibrarybehaviourDao.countBySearch(search);
	}
	
	public List<CompetencyLibraryBehaviour> getBySearch(String search, int start, int num){
		return competencylibrarybehaviourDao.getBySearch(search, start, num);
	}
	
	public List<CompetencyLibraryBehaviour> getByCompetencyLibraryId(Long competencyLibraryId)
	{
		return competencylibrarybehaviourDao.getByCompetencyLibraryId(competencyLibraryId);
	}
}
