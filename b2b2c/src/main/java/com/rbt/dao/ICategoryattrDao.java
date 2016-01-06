/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ICategoryattrDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Categoryattr;

/**
 * @function 功能 产品属性列表dao层业务接口
 * @author  创建人林俊钦
 * @date  创建日期 Tue Jul 19 08:48:08 CST 2011
 */

public interface ICategoryattrDao extends IGenericDao<Categoryattr,String>{
	
	public List getCatAttrList(Map map);
	
	public void deleteAttr_id(String id);
	
}

