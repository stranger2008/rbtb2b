/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMembernewsDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Membernews;

/**
 * @function 功能 企业新闻信息dao层业务接口
 * @author 创建人邱景岩
 * @date 创建日期 Wed Jul 20 13:14:34 CST 2011
 */

public interface IMembernewsDao extends IGenericDao<Membernews, String> {

	/**
	 * 方法描述：审核企业新闻信息
	 * 
	 * @param member
	 */
	public void auditMembernews(Map map);

	/**
	 * @Method Description : 找出前台企业新闻的列表
	 * @author : 林俊钦
	 * @date : Nov 4, 2011 10:45:13 AM
	 */
	@SuppressWarnings("unchecked")
	public List getWebMembernewsList(Map map);

	/**
	 * @Method Description : 找出前台企业新闻的列表的条数
	 * @author : 林俊钦
	 * @date : Nov 4, 2011 10:45:13 AM
	 */
	@SuppressWarnings("unchecked")
	public int getWebMembernewsCount(Map map);
}
