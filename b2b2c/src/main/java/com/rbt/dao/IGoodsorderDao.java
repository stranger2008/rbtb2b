/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IGoodsorderDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsorder;

/**
 * @function 功能 订单商品表dao层业务接口
 * @author  创建人林俊钦
 * @date  创建日期 Mon Mar 19 15:53:24 CST 2012
 */

public interface IGoodsorderDao extends IGenericDao<Goodsorder,String>{
	
	public List<Map<String,String>> getListBuyOut(Map<String, String> map);
	
	public int getCountBuyOut(Map<String, String> map);
	
	public void update(Map<String, String> map);
}

