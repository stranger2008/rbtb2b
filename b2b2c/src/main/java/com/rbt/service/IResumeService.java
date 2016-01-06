/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IResumeService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Resume;

/**
 * @function 功能 简历信息Service层业务接口实现类
 * @author  创建人 胡惜坤
 * @date  创建日期 Wed Jul 13 16:14:17 CST 2011
 */

public interface IResumeService extends IGenericService<Resume,String> {
	/**
	 * 方法描述：更新审核状态
	 * @param map
	 */
	public void updateState(Map map);
    /**
	 * @MethodDescribe 方法描述    获取前台绑定简历的列表
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 6, 2011 10:03:36 AM
	 */
	@SuppressWarnings("unchecked")
	public List getWebResumeList(Map map);
    /**
	 * @MethodDescribe 方法描述   获取前台绑定简历的条数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 6, 2011 10:03:07 AM
	 */
	@SuppressWarnings("unchecked")
	public int getWebResumeCount(Map map);
	/**
	 * @MethodDescribe 方法描述    更新浏览次数
	 * @author  创建人  胡惜坤
	 */
	public void updateClickNum(String pk);
	 /**
     * @MethodDescribe 方法描述   修改推荐与不推荐状态
 	 * @author  创建人  胡惜坤
 	 * @date  创建日期  sep 26, 2011 3:32:52 PM
     */
     public void updateRecommendState(List list);
     public String insertGetPk(Resume t, List objList);
     public void update(Resume t, List objList, String id);
}

