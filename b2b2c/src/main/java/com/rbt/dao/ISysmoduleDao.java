/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ISysmoduleDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Sysmodule;

/**
 * @function 功能 系统模块表dao层业务接口
 * @author  创建人林俊钦
 * @date  创建日期 Fri Jan 13 12:48:48 CST 2012
 */

public interface ISysmoduleDao extends IGenericDao<Sysmodule,String>{
	/**
	 * 方法描述：模块排序
	 * @param interrule
	 */
	public void updateSort(final List list);
	
	public void updateisuse(final List list);
	
}

