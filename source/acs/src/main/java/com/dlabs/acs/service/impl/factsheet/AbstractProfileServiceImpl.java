package com.dlabs.acs.service.impl.factsheet;

import java.io.Serializable;

import com.dlabs.acs.dao.intf.factsheet.IAbstractProfileDao;
import com.dlabs.acs.service.impl.AbstractServiceImpl;
import com.dlabs.acs.service.intf.factsheet.IAbstractProfileService;

public abstract class AbstractProfileServiceImpl <E, I extends Serializable> extends AbstractServiceImpl<E,I> implements IAbstractProfileService<E, I>{
	public abstract IAbstractProfileDao<E, I> getDao();
	
	public E getByProfileIdFirst(Long profileId)
	{
		return getDao().getByProfileIdFirst(profileId);
	}

}
