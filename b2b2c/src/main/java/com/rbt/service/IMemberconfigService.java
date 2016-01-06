/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMemberconfigService.java 
 */
package com.rbt.service;

import com.rbt.model.Memberconfig;

/**
 * @function 功能 记录会员企业站设置信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Mon Aug 29 16:12:02 CST 2011
 */

public interface IMemberconfigService extends IGenericService<Memberconfig,String>{
	
	/**
	 * 方法描述：修改记录会员企业站设置信息
	 * @param memberconfig
	 */
	public void updatetempcode(Memberconfig memberconfig);
	
	/**
	 * 方法描述：修改记录会员企业站点击数
	 * @param java.lang.String
	 */
	public void updatelognum(String cust_id);

}

