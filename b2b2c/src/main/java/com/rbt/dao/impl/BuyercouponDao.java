/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: BuyercouponDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IBuyercouponDao;
import com.rbt.model.Buyercoupon;

/**
 * @function 功能  买家优惠卷dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Thu Mar 29 10:41:16 CST 2012
 */
@Repository
public class BuyercouponDao extends GenericDao<Buyercoupon,String> implements IBuyercouponDao {
	
	public BuyercouponDao() {
		super(Buyercoupon.class);
	}
	
}

