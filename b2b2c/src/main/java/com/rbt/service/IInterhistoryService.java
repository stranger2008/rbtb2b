/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IInterhistoryService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Interhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;

/**
 * @function 功能 记录会员积分异动历史Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Thu Jul 14 15:01:09 CST 2011
 */

public interface IInterhistoryService extends IGenericService<Interhistory,String>{
	
	/**
	 * @Method Description : 获取每天会员所得的积分数
	 * @author : 林俊钦
	 * @date : Nov 11, 2011 2:18:22 PM
	 */
	@SuppressWarnings("unchecked")
	public int getInterhistorySumScore(Map map);
	
	/**
	 * @Method Description :运营商找出需要删除的ID值并减掉积分
	 * @author : 林俊钦
	 * @date : Nov 16, 2011 1:48:15 PM
	 */
	public List getReleaseCustId(Map map);

	
	// 资金兑换积分
	public void optioninter(Memberfund memberfund, String use_num, String session_cust_id, String session_user_id, Memberinter memberinter, String rech_fund, int rechange_value);
}

