/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: CouponService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ICouponDao;
import com.rbt.model.Coupon;
import com.rbt.service.ICouponService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录优惠卷表信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Mar 22 13:38:28 CST 2012
 */
@Service
public class CouponService extends GenericService<Coupon,String> implements ICouponService {
	
	ICouponDao couponDao;

	@Autowired
	public CouponService(ICouponDao couponDao) {
		super(couponDao);
		this.couponDao = couponDao;
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.service.ISupplyService#updateSupplyState(java.util.List)
	 */
	public void updateCouponState(List lists) {
		this.couponDao.updateCouponState(lists);
	}
	
}

