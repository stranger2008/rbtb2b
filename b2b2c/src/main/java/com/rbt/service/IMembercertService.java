/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMembercertService.java 
 */
package com.rbt.service;

import java.util.Map;

import com.rbt.model.Membercert;

/**
 * @function 功能 会员荣誉资质信息Service层业务接口实现类
 * @author  创建人 邱景岩
 * @date  创建日期 Wed Jul 20 13:11:13 CST 2011
 */

public interface IMembercertService extends IGenericService<Membercert,String>{

	/**
	 * 方法描述：审核会员荣誉资质信息
	 * 
	 * @param member
	 */
	public void auditMembercert(Map map);

}
