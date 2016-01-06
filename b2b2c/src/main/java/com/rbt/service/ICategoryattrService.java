/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ICategoryattrService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Categoryattr;

/**
 * @function 功能 产品属性列表Service层业务接口实现类
 * @author  创建人 林俊钦
 * @date  创建日期 Tue Jul 19 08:48:08 CST 2011
 */

public interface ICategoryattrService extends IGenericService<Categoryattr,String>{
	
	public List getCatAttrList(Map map);
	
	public void deleteAttr_id(String id);
}

