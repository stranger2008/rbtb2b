/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IRolerightService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Roleright;

/**
 * @function 功能 角色权限业务层接口
 * @author 创建人 邱景岩
 * @date 创建日期 Jun 28, 2011
 */
public interface IRolerightService extends IGenericService<Roleright, String> {

	/**
	 * 方法描述：按照map中的条件找出角色权限的信息
	 * 
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getRolerightMenuList(Map map);

	/**
	 * @MethodDescribe 方法描述 根据url返回提示字符串和操作权限名
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 5, 2011 3:15:22 PM
	 */
	public List getRolerightLogsList(Map map);

}
