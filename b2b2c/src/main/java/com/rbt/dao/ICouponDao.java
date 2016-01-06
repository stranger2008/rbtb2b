/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ICouponDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Coupon;

/**
 * @function 功能 记录优惠卷表信息dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Thu Mar 22 13:38:28 CST 2012
 */

public interface ICouponDao extends IGenericDao<Coupon,String>{
	
	/**
	 * @Method Description :批量修改推荐
	 * @author : 蔡毅存
	 * @date : Nov 28, 2012 9:55:16 AM
	 */
	@SuppressWarnings("unchecked")
	public void updateCouponState(List lists);
	
}

