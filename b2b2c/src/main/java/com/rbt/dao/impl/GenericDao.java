/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: GenericDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IGenericDao;
import com.rbt.model.Member;

/**
 * @function 功能 泛型dao层通用接口实现类
 * @author  创建人 李良林
 * @date  创建日期 2011-11-25
 */

@Repository
public class GenericDao<T,PK> extends BaseDao implements IGenericDao<T,PK> {

	private Class<T> persistentClass;

	/**
	 * Constructor that takes in a class to see which type of entity to persist.
	 * Use this constructor when subclassing.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDao(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	public String getModelName(){
		return persistentClass.getSimpleName().toLowerCase();
	}
	
	public void delete(PK id) {
		this.getSqlMapClientTemplate().delete(getModelName()+".delete", id);
	}

	public T get(PK id) {
		return (T)this.getSqlMapClientTemplate().queryForObject(getModelName()+".getByPk", id);
	}

	public int getCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	public List getList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getList",map);
	}

	public void insert(T t) {
		this.getSqlMapClientTemplate().insert(getModelName()+".insert", t);
	}

	public void update(T t) {
		this.getSqlMapClientTemplate().update(getModelName()+".update", t);
	}
	/**
	 * @Method Description : 对刚插入的数据获得它的主键
	 * @author : 林俊钦
	 * @date : Dec 16, 2011 4:37:14 PM
	 */
	public String insertGetPk(T t){
		  return (String) this.getSqlMapClientTemplate().insert(getModelName()+".insertGetPk",t);
	}	
	
	
}
