/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IGenericService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

/**
 * @function 功能 泛型业务通用接口
 * @author  创建人 李良林
 * @date  创建日期 2011-11-25
 */

public interface IGenericService<T,PK> {
	
	/**
	 * 新增记录
	 */
	public void insert(T t);
	
	/**
	 * 修改记录
	 */
	public void update(T t);
	
	/**
	 * 按主键删除
	 */
	public void delete(PK id);
	
	/**
	 * 查询记录
	 */
	public List<Map<String,String>> getList(Map<String, String> map);
	
	/**
	 * 查询记录数量
	 */
	public int getCount(Map<String, String> map);
	
	/**
     *按主键查询,取一条记录
     */
	public T get(PK id);
	/**
     *对刚插入的数据获得它的主键
     */
	public String insertGetPk(T t);

	/**
     *删除相应的模块同时删除相应的属性数据
     */
    public void deleteByModel(String id);

}
