/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: OrderinfoService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;
import com.rbt.dao.IOrderinfoDao;
import com.rbt.model.Orderinfo;
import com.rbt.service.IOrderinfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录平台支付方式信息Service层业务接口实现
 * @author 创建人 订单管理
 * @date 创建日期 Tue Oct 25 17:06:38 CST 2011
 */
@Service
public class OrderinfoService extends GenericService<Orderinfo,String> implements IOrderinfoService {

	IOrderinfoDao orderinfoDao;

	@Autowired
	public OrderinfoService(IOrderinfoDao orderinfoDao) {
		super(orderinfoDao);
		this.orderinfoDao = orderinfoDao;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IOrderinfoService#getEndOrderList(java.util.Map)
	 */
	public List getEndOrderList(String endtime) {
		return this.orderinfoDao.getEndOrderList(endtime);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IOrderinfoService#getOrderinfoList(java.util.String)
	 */
	public List getpayOrderinfoList(String paypk) {
		return this.orderinfoDao.getpayOrderinfoList(paypk);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IOrderinfoService#UpdateOrderState(java.util.Map)
	 */
	public void UpdateOrderState(Map map){
		this.orderinfoDao.updateOrderState(map);
	}

}

