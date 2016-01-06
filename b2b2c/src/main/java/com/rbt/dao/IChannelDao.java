/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IChannelDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Channel;

/**
 * @function 功能 记录网站栏目信息dao层业务接口
 * @author  创建人胡惜坤
 * @date  创建日期 Mon Aug 15 10:57:10 CST 2011
 */

public interface IChannelDao  extends IGenericDao<Channel,String> {

	/**
	 * 方法描述：批量更新网站栏目排序
	 * @param pk
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public void updateChannelSortNo(List lists);
	/**
	 * @MethodDescribe 方法描述   根据模块类型返回相应的对象
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 17, 2011 3:49:12 PM
	 */
	@SuppressWarnings("unchecked")
	public Channel getChannelByType(String type);
}

