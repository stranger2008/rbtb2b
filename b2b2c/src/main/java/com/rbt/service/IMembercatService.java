/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMembercatService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Membercat;

/**
 * @function 功能 会员自定义分类表Service层业务接口实现类
 * @author  创建人 胡惜坤
 * @date  创建日期 Mon Jul 25 11:13:52 CST 2011
 */

public interface IMembercatService extends IGenericService<Membercat,String> {

	/**
	 * 方法描述：批量会员自定义分类排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateAllMemberCat(List list);
	/**
	 * 方法描述：批量排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateSortNo(List list);
}

