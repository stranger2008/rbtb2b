/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IGenericDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

/**
 * @function 功能 泛型dao层通用接口
 * @author  创建人 李良林
 * @date  创建日期 2011-11-25
 */

public interface IGenericDao<T,PK> {
	
	public void insert(T t);
	
	public void update(T t);
	
	public void delete(PK id);
	
	public List<Map<String,String>> getList(Map<String, String> map);
	
	public int getCount(Map<String, String> map);
	
	public T get(PK id);
	
	/**
	 * @Method Description : 对刚插入的数据获得它的主键
	 * @author : 林俊钦
	 * @date : Dec 16, 2011 4:37:14 PM
	 */
	public String insertGetPk(T t);
}
