package com.dlabs.acs.dao;


import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface IAbstractDao<E, I extends Serializable>
{
	E findById(I id);

	void saveOrUpdate(E e);

	void save(E e);

	void save(List<E> listE);

	void update(E e);

	void delete(I id);

	void remove(E e);

	void remove(I id);

	public Long count();

	public List<E> getAll();

	public List<E> getAll(int start, int num);

	public List<E> getAllActive();

	public List<E> getAllActive(int start, int num);


	void delete(E e);
}

