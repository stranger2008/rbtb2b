/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMemberchannelDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memberchannel;
import com.rbt.model.Memberconfig;

/**
 * @function 功能 记录会员企业站栏目信息dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Fri Aug 26 16:21:41 CST 2011
 */

public interface IMemberchannelDao extends IGenericDao<Memberchannel,String>{

	/**
	 * 方法描述：插入记录会员企业站栏目信息
	 * @param memberchannel
	 */
	public void insertintoMemberchannel(Map configchannel);
	/**
	 * 方法描述：插入记录会员企业站设置信息
	 * @param memberchannel
	 */
	public void insertMemberconfig(Memberconfig memberconfig);

	
	/**
	 * 方法描述：批量修改是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateState(List lists);
	
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updatechannel(List lists);
}

