/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IPaymentDao.java 
 */
package com.rbt.dao;

import java.util.List;
import com.rbt.model.Payment;

/**
 * @function 功能 记录平台支付方式信息dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Mon Oct 24 10:57:44 CST 2011
 */

public interface IPaymentDao extends IGenericDao<Payment,String>{
	
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateEnabled(List lists);

}

