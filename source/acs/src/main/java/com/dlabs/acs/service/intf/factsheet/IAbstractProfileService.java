package com.dlabs.acs.service.intf.factsheet;

import java.io.Serializable;

import com.dlabs.acs.service.IService;

public interface IAbstractProfileService <E, I extends Serializable> extends IService<E, I>{
	public E getByProfileIdFirst(Long profileId);
}
