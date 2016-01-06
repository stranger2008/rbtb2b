/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IJobService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Job;
import com.rbt.model.Product;

/**
 * @function 功能 招聘信息Service层业务接口实现类
 * @author  创建人 胡惜坤
 * @date  创建日期 Tue Jul 12 15:29:27 CST 2011
 */

public interface IJobService extends IGenericService<Job,String> {
	/**
	 * 方法描述：按照map中的条件找出会员招聘的信息
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getJobMemberList(Map map);

	/**
	 * 方法描述：按照map中的条件找出会员招聘信息的数量
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int getJobMemberCount(Map map);
	/**
	 * 方法描述：更新审核状态
	 * @param map
	 */
	public void updateState(Map map);
	/**
	 * @MethodDescribe 方法描述    更新浏览次数
	 * @author  创建人  胡惜坤
	 */
	public void updateClickNum(String pk);
	
	/**
	 * @MethodDescribe 方法描述    获取前台绑定列表
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 6, 2011 2:05:01 PM
	 */
	@SuppressWarnings("unchecked")
	public List getWebJobList(Map map);
	/**
	 * @MethodDescribe 方法描述    获取前台绑定列表的条数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 6, 2011 2:04:34 PM
	 */
	@SuppressWarnings("unchecked")
	public int getWebJobCount(Map map);
	 /**
     * @MethodDescribe 方法描述   修改推荐与不推荐状态
 	 * @author  创建人  胡惜坤
 	 * @date  创建日期  sep 26, 2011 3:32:52 PM
     */
     public void updateRecommendState(List list);
     public String insertGetPk(Job t, List objList);
     public void update(Job t, List objList, String id);
}

