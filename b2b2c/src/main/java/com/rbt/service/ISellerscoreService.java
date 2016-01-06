/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ISellerscoreService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Sellerscore;

/**
 * @function 功能 记录商家打分信息Service层业务接口实现类
 * @author  创建人 胡惜坤
 * @date  创建日期 Fri Mar 30 11:03:31 CST 2012
 */

public interface ISellerscoreService extends IGenericService<Sellerscore,String>{
	/**
	 * 查询记录打分统计记录
	 */
	public List<Map<String,String>> getCountList(Map<String, String> map);
	
	/**
	 * 查询打分统计记录数量
	 */
	public int getIndexCount(Map<String, String> map);
}

