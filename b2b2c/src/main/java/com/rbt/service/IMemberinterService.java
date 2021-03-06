/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMemberinterService.java 
 */
package com.rbt.service;

import com.rbt.model.Interhistory;
import com.rbt.model.Memberinter;

/**
 * @function 功能 记录会员积分数Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Thu Jul 14 14:30:38 CST 2011
 */

public interface IMemberinterService extends IGenericService<Memberinter,String>{
	//跟新积分
	public void updateinter(Interhistory interhistory, Memberinter memberinter);
}

