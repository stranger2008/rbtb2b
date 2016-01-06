/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ISellerscoreDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Sellerscore;

/**
 * @function 功能 记录商家打分信息dao层业务接口
 * @author  创建人胡惜坤
 * @date  创建日期 Fri Mar 30 11:03:31 CST 2012
 */

public interface ISellerscoreDao extends IGenericDao<Sellerscore,String>{
	
	public List<Map<String,String>> getCountList(Map<String, String> map);
	
	public int getIndexCount(Map<String, String> map);
}

