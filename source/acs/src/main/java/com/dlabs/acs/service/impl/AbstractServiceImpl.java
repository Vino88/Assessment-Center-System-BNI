package com.dlabs.acs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dlabs.acs.dao.IAbstractDao;
import com.dlabs.acs.service.IService;


@Transactional(readOnly = true)
public abstract class AbstractServiceImpl<E, I extends Serializable> implements IService<E, I>
{
	public abstract IAbstractDao<E, I> getDao();

	@Override
	public E findById(I id)
	{
		// TODO Auto-generated method stub
		return getDao().findById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdate(E e)
	{
		getDao().saveOrUpdate(e);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(E e)
	{
		getDao().save(e);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(List<E> listE)
	{
		getDao().save(listE);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(E e)
	{
		getDao().update(e);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(I id)
	{
		getDao().delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(E e)
	{
		getDao().delete(e);
	}

	@Override
	public Long count()
	{
		// TODO Auto-generated method stub
		return getDao().count();
	}

	@Override
	public List<E> getAll()
	{
		// TODO Auto-generated method stub
		return getDao().getAll();
	}

	@Override
	public List<E> getAll(int start, int num)
	{
		return getDao().getAll(start, num);
	}

	@Override
	public List<E> getAllActive()
	{
		return getDao().getAllActive();
	}

	@Override
	public List<E> getAllActive(int start, int num)
	{
		return getDao().getAllActive(start, num);
	}

}
