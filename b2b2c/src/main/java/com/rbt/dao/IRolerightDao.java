/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IRolerightDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Roleright;

/**
 * @function 功能 角色权限dao层业务接口
 * @author 创建人 邱景岩
 * @date 创建日期 Jun 28, 2011
 */

public interface IRolerightDao extends IGenericDao<Roleright, String> {

	/**
	 * 方法描述：按照map中的条件找出角色权限的信息
	 * 
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getRolerightMenuList(Map map);

	/**
	 * @MethodDescribe 方法描述 根据url地址返回操作权限名和提示字符串prompt
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 5, 2011 3:12:35 PM
	 */

	public List getRolerightLogsList(Map map);

}
