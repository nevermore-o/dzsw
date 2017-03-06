package com.open.service.impl;

import java.util.List;

import com.open.dao.IDao;
import com.open.service.IService;

public class ServiceImpl implements IService {

	protected IDao dao;
	
	@Override
	public void create(Object entity) {
		// TODO Auto-generated method stub
		dao.create(entity);
	}
	
	public void update(Object entity){
		dao.update(entity);
	}

	@Override
	public void delete(Object entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object find(Class clazz, String id) {
		// TODO Auto-generated method stub
		dao.find(clazz, id);
		return dao.find(clazz, id);
	}

	@Override
	public int getTotalCount(String hql, Object params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List list(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List list(String hql, int page, int pageSize, Object params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Object entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

}
