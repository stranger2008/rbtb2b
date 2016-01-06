/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMemberchannelService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memberchannel;
import com.rbt.model.Memberconfig;
/**
 * @function 功能 记录会员企业站栏目信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Fri Aug 26 16:21:41 CST 2011
 */

public interface IMemberchannelService extends IGenericService<Memberchannel,String>{
	
	
	/**
	 * 方法描述：插入记录会员企业站栏目信息
	 * @param sysuser
	 */
	public void insertintoMemberchannel(Map configchannel);
	
	/**
	 * 方法描述：插入会员企业站设置信息
	 * @param sysuser
	 */
	public void insertMemberconfig(Memberconfig memberconfig);

	

	
	/**
	 * 方法描述：批量修改是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateisdis(List list);
	
	/**
	 * 方法描述：批量修改排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updatechannel(List list);
}

