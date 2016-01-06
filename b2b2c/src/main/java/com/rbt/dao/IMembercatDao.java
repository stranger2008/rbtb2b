/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMembercatDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Membercat;

/**
 * @function 功能 会员自定义分类表dao层业务接口
 * @author  创建人胡惜坤
 * @date  创建日期 Mon Jul 25 11:13:52 CST 2011
 */

public interface IMembercatDao extends IGenericDao<Membercat,String> {
	/**
	 * 方法描述：批量自定义分类
	 * @param pk
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public void updateAllMemberCat(List lists);
	/**
	 * 方法描述：批量排序
	 * @param pk
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public void updateSortNo(List lists);
}

