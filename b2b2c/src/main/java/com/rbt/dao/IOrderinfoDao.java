/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IOrderinfoDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Orderinfo;

/**
 * @function 功能 记录平台支付方式信息dao层业务接口
 * @author  创建人 蔡毅存
 * @date  创建日期 Tue Oct 25 17:06:38 CST 2011
 */

public interface IOrderinfoDao extends IGenericDao<Orderinfo,String>{
	
	/**
	 * 方法描述：修改记录平台支付方式信息
	 * @param java.util.Map
	 */
	public void updateOrderState(Map map);
	
	/**
	 * 方法描述：按照endtime中的条件找出记录平台支付方式信息信息列表
	 * @param String
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getEndOrderList(String endtime);
	
	/**
	 * 方法描述：平台支付方式信息信息列表
	 * @param String
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getpayOrderinfoList(String paypk);

	
}

