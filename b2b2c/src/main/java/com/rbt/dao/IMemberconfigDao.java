/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMemberconfigDao.java 
 */
package com.rbt.dao;

import com.rbt.model.Memberconfig;

/**
 * @function 功能 记录会员企业站设置信息dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Mon Aug 29 16:12:02 CST 2011
 */

public interface IMemberconfigDao extends IGenericDao<Memberconfig,String>{
	
	/**
	 * 方法描述：修改记录会员企业站设置信息
	 * @param memberconfig
	 */
	public void updatetempcode(Memberconfig memberconfig);
	
	/**
	 * 方法描述：修改记录会员企业站点击数
	 * @param java.lang.String
	 */
	public void updateLognum(String cust_id);

}

