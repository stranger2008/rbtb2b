/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IOrderdetailService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Orderdetail;

/**
 * @function 功能 订单商品详细Service层业务接口实现类
 * @author  创建人 邱景岩
 * @date  创建日期 Wed Mar 28 17:27:56 CST 2012
 */

public interface IOrderdetailService extends IGenericService<Orderdetail,String>{
	public List getdetailList(Map map);
	
	public int getdetailCount(Map map);
}

