/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IPaymentService.java 
 */
package com.rbt.service;

import java.util.List;
import com.rbt.model.Payment;

/**
 * @function 功能 记录平台支付方式信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Mon Oct 24 10:57:44 CST 2011
 */

public interface IPaymentService extends IGenericService<Payment,String>{

	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateEnabled(List list);
}

