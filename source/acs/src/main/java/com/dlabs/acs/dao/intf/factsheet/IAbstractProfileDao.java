package com.dlabs.acs.dao.intf.factsheet;

import java.io.Serializable;

import com.dlabs.acs.dao.IAbstractDao;

public interface IAbstractProfileDao <E, I extends Serializable> extends IAbstractDao<E,I>  {
	public E getByProfileIdFirst(Long profileId);
}
