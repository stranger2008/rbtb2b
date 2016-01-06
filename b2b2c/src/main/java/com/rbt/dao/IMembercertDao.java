/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMembercertDao.java 
 */
package com.rbt.dao;

import java.util.Map;

import com.rbt.model.Membercert;

/**
 * @function 功能 会员荣誉资质信息dao层业务接口
 * @author  创建人邱景岩
 * @date  创建日期 Wed Jul 20 13:11:13 CST 2011
 */

public interface IMembercertDao extends IGenericDao<Membercert,String>{

	/**
	 * 方法描述：审核会员荣誉资质信息
	 * 
	 * @param member
	 */
	public void auditMembercert(Map map);
	
	/**
	 * @Method Description : 添加成功后马上返回ID
	 * @author : 林俊钦
	 * @date : Dec 8, 2011 8:49:10 PM
	 */
	
}

