package com.dlabs.acs.service;

import java.io.Serializable;
import java.util.List;


public interface IService<E, I extends Serializable>
{
	E findById(I id);

	void saveOrUpdate(E e);

	void save(E e);

	public void save(List<E> listE);

	void update(E e);

	void delete(I id);

	public Long count();

	public List<E> getAll();

	public List<E> getAll(int start, int num);

	public List<E> getAllActive();

	public List<E> getAllActive(int start, int num);

	void delete(E e);
}
