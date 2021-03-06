/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IOrderdetailDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Orderdetail;

/**
 * @function 功能 订单商品详细dao层业务接口
 * @author  创建人邱景岩
 * @date  创建日期 Wed Mar 28 17:27:56 CST 2012
 */

public interface IOrderdetailDao extends IGenericDao<Orderdetail,String>{
	public List getdetailList(Map map);
	public int getdetailCount(Map map);
}

