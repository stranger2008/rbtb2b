/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMemberfundService.java 
 */
package com.rbt.service;

import java.util.Map;
import com.rbt.model.Memberfund;

/**
 * @function 功能 会员资金Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Tue Jul 12 09:26:58 CST 2011
 */

public interface IMemberfundService extends IGenericService<Memberfund,String>{

	/**
	 * 方法描述：修改会员资金
	 * @param java.util.Map
	 */
	public void updateMemberfund(Map map);
	//修改会员资金
	public void insertfundoption(String cust_id, String session_user_id, String fund_num);
}

