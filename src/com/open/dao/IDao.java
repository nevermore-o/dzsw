package com.open.dao;

import java.util.List;

import org.hibernate.Query;

public interface IDao<T> {
	
	public T find(Class <T>  clazz, String id);
	
	public void create(T entity);
	
	public void save(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public List<T> list(String hql);
	
	public Query createQuery(String hql);
	
	public int getTotalCount(String hql, Object[] param);
	
	public List<T> list(String hql, int page, int pageSize, Object[] params);
	
}
