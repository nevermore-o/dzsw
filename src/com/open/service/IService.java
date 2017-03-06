package com.open.service;

import java.util.List;

public interface IService <T>{
	
	public T find(Class<T> clazz, String id);

	public void create(T entity);
	
	public void save(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public List<T> list(String hql);
	
	public List<T> list(String hql, int page, int pageSize, Object params);
	
	public int getTotalCount(String hql, Object params);
	
}


