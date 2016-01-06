/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IOrderinfoService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;
import com.rbt.model.Orderinfo;

/**
 * @function 功能 记录平台支付方式信息Service层业务接口实现类
 * @author  创建人 订单管理
 * @date  创建日期 Tue Oct 25 17:06:38 CST 2011
 */

public interface IOrderinfoService extends IGenericService<Orderinfo,String>{
	
	/**
	 * 方法描述：修改记录平台支付方式信息
	 * @param java.util.Map
	 */
	public void UpdateOrderState(Map map);
	
	/**
	 * 方法描述：按照String中的条件找出订单的信息
	 * @param map
	 * @return java.lang.String
	 */
	@SuppressWarnings("unchecked")
	public List getEndOrderList(String endtime);
	
	/**
	 * 方法描述：按照map中的条件找出订单的信息
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getpayOrderinfoList(String paypk);

}

