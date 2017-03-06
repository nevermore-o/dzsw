package com.open.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.open.dao.IDao;

public class DaoImpl<T> extends HibernateDaoSupport implements IDao<T> {

	@Override
	public void create(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().persist(entity);
	}

	@Override
	public Query createQuery(String hql) {
		// TODO Auto-generated method stub
		return this.getSession().createQuery(hql);
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public T find(Class<T> clazz, String id) {
		// TODO Auto-generated method stub
		return (T) this.getHibernateTemplate().load(clazz, id);
	}

	@Override
	public int getTotalCount(String hql, Object[] params) {
		// TODO Auto-generated method stub
		Query query = this.createQuery(hql);
		//设置参数
		for(int i=0; params!=null&&i<params.length;i++){
			query.setParameter(i+1, params[i]);
		}
		Object obj = this.createQuery(hql).uniqueResult();
		return ((Long)obj).intValue();
	}

	@Override
	public List<T> list(String hql) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<T> list(String hql, int page, int pageSize, Object[] params) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Query query = this.createQuery(hql);
		//设置参数
		for(int i=0; params!=null&&i<params.length;i++){
			query.setParameter(i+1, params[i]);
		}
		List<T> list = this.createQuery(hql).setFirstResult(page)
			.setMaxResults(pageSize).list();
		return list;
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(entity);
	}

	public void update(T entity){
		this.getHibernateTemplate().update(entity);
	}
}
