package com.dlabs.acs.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.dlabs.acs.embeddable.CommonFields;

@MappedSuperclass()
public abstract class AbstractEntity implements Serializable
{
	public CommonFields getCommonFields()
	{
		return null;
	}

	public void setCommonFields(CommonFields commonFields)
	{

	}
}
