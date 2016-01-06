/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IGoodsorderService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsorder;

/**
 * @function 功能 订单商品表Service层业务接口实现类
 * @author  创建人 林俊钦
 * @date  创建日期 Mon Mar 19 15:53:24 CST 2012
 */

public interface IGoodsorderService extends IGenericService<Goodsorder,String>{

	/**
	 * 查询记录
	 */
	public List<Map<String,String>> getListBuyOut(Map<String, String> map);
	
	/**
	 * 查询记录数量
	 */
	public int getCountBuyOut(Map<String, String> map);
	
	public void update(Map<String, String> map);
		
}

