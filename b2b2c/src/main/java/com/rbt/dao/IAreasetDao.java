/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IAreasetDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Areaset;

/**
 * @function 功能 记录区域设置信息dao层业务接口
 * @author  创建人胡惜坤
 * @date  创建日期 Wed Mar 28 13:22:27 CST 2012
 */

public interface IAreasetDao extends IGenericDao<Areaset,String>{
	
	public void deleteByShopid(String id);
	
}

