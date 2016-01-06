/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: BuyercouponService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IBuyercouponDao;
import com.rbt.model.Buyercoupon;
import com.rbt.service.IBuyercouponService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 买家优惠卷Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Thu Mar 29 10:41:16 CST 2012
 */
@Service
public class BuyercouponService extends GenericService<Buyercoupon,String> implements IBuyercouponService {
	
	IBuyercouponDao buyercouponDao;

	@Autowired
	public BuyercouponService(IBuyercouponDao buyercouponDao) {
		super(buyercouponDao);
		this.buyercouponDao = buyercouponDao;
	}
	
}

