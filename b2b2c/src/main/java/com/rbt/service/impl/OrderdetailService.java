/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: OrderdetailService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IOrderdetailDao;
import com.rbt.model.Orderdetail;
import com.rbt.service.IOrderdetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 订单商品详细Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Mar 28 17:27:56 CST 2012
 */
@Service
public class OrderdetailService extends GenericService<Orderdetail,String> implements IOrderdetailService {
	
	IOrderdetailDao orderdetailDao;

	@Autowired
	public OrderdetailService(IOrderdetailDao orderdetailDao) {
		super(orderdetailDao);
		this.orderdetailDao = orderdetailDao;
	}
	
	public List getdetailList(Map map){
		return this.orderdetailDao.getdetailList(map);
	}
	
	public int getdetailCount(Map map){
		return this.orderdetailDao.getdetailCount(map);
	}
}

