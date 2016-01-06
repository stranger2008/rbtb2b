/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ICouponService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Coupon;

/**
 * @function 功能 记录优惠卷表信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Thu Mar 22 13:38:28 CST 2012
 */

public interface ICouponService extends IGenericService<Coupon,String>{
	/**
	 * 方法描述：批量修改是否通用
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateCouponState(List lists);
	
}

