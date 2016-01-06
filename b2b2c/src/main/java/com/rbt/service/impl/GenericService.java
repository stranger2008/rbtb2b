/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: GenericService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rbt.dao.IGenericDao;
import com.rbt.service.IGenericService;

/**
 * @function 功能 泛型业务通用接口实现类
 * @author  创建人 李良林
 * @date  创建日期 2011-11-25
 */

@Service
public class GenericService<T,PK> implements IGenericService<T,PK>{
	
	IGenericDao<T,PK> genericDao;
	
	public GenericService() {}

    public GenericService(IGenericDao<T, PK> genericDao) {
        this.genericDao = genericDao;
    }
	
	public void delete(PK id) {
		genericDao.delete(id);
	}

	public T get(PK id) {
		return (T)genericDao.get(id);
	}

	public int getCount(Map map) {
		return genericDao.getCount(map);
	}

	public List getList(Map map) {
		return genericDao.getList(map);
	}

	public void insert(T t) {
		genericDao.insert(t);
	}

	public void update(T t) {
		genericDao.update(t);
	}
	
	public String insertGetPk(T t){		
		return genericDao.insertGetPk(t);
	}
    public void deleteByModel(String id){
    	//genericDao.deleteByModel(id);
    }
}
