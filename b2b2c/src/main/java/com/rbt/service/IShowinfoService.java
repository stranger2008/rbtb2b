/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IExhibitionService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Resume;
import com.rbt.model.Showinfo;

/**
 * @function 功能 展会表Service层业务接口实现类
 * @author  创建人 胡惜坤
 * @date  创建日期 Thu Jul 28 09:17:39 CST 2011
 */

public interface IShowinfoService  extends IGenericService<Showinfo,String>{

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
	 * @MethodDescribe 方法描述     根据前台的Map找出对应的列表
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 5, 2011 11:31:44 AM
	 */
	@SuppressWarnings("unchecked")
	public List getWebShowinfoList(Map map);

	/**
	 * @MethodDescribe 方法描述    根据前台的Map找出对应的列表条数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 5, 2011 11:30:52 AM
	 */
	@SuppressWarnings("unchecked")
	public int getWebShowinfoCount(Map map);
	 /**
     * @MethodDescribe 方法描述   修改推荐与不推荐状态
 	 * @author  创建人  胡惜坤
 	 * @date  创建日期  sep 26, 2011 3:32:52 PM
     */
     public void updateRecommendState(List list);
     public String insertGetPk(Showinfo t, List objList);
     public void update(Showinfo t, List objList, String id);
}

