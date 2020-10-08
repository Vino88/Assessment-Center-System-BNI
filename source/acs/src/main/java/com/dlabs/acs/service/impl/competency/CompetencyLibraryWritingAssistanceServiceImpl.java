package com.dlabs.acs.service.impl.competency;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.dao.intf.competency.ICompetencyLibraryWritingAssistanceDao;
import com.dlabs.acs.entity.competency.CompetencyLibraryWritingAssistance;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.competency.ICompetencyLibraryWritingAssistanceService;

@Service
@Transactional(readOnly = true)
public class CompetencyLibraryWritingAssistanceServiceImpl extends AbstractServiceImpl<CompetencyLibraryWritingAssistance, Long> implements ICompetencyLibraryWritingAssistanceService{
	private Logger log = Logger.getLogger(CompetencyLibraryWritingAssistanceServiceImpl.class);
	
	@Autowired
	private ICompetencyLibraryWritingAssistanceDao competencylibrarywritingassistanceDao;

	@Override
	public IAbstractDao<CompetencyLibraryWritingAssistance, Long> getDao() {
		// TODO Auto-generated method stub
		return competencylibrarywritingassistanceDao;
	}
	
	public Long countBySearch(String search){
		return competencylibrarywritingassistanceDao.countBySearch(search);
	}
	
	public List<CompetencyLibraryWritingAssistance> getBySearch(String search, int start, int num){
		return competencylibrarywritingassistanceDao.getBySearch(search, start, num);
	}
	
	public List<CompetencyLibraryWritingAssistance> getByCompetencyLibraryId(Long competencyLibraryId, int start, int num){
		return competencylibrarywritingassistanceDao.getByCompetencyLibraryId(competencyLibraryId, start, num);
	}

	@Override
	public List<CompetencyLibraryWritingAssistance> getByLevel(int level, int start, int num) {
		// TODO Auto-generated method stub
		return competencylibrarywritingassistanceDao.getByLevel(level, start, num);
	}

	@Override
	public List<CompetencyLibraryWritingAssistance> getByCompetencyLibraryIdAndLevel(Long competencyLibraryId,
			int level, int start, int num) {
		// TODO Auto-generated method stub
		return competencylibrarywritingassistanceDao.getByCompetencyLibraryIdAndLevel(competencyLibraryId, level, start, num);
	}

	@Override
	public Long countByCompetencyLibraryId(Long competencyLibraryId) {
		// TODO Auto-generated method stub
		return competencylibrarywritingassistanceDao.countByCompetencyLibraryId(competencyLibraryId);
	}

	@Override
	public Long countByLevel(int level) {
		// TODO Auto-generated method stub
		return competencylibrarywritingassistanceDao.countByLevel(level);
	}

	@Override
	public Long countByCompetencyLibraryIdAndLevel(Long competencyLibraryId, int level) {
		// TODO Auto-generated method stub
		return competencylibrarywritingassistanceDao.countByCompetencyLibraryIdAndLevel(competencyLibraryId, level);
	}
}
