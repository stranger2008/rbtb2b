/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: INewsService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.News;
import com.rbt.model.Product;

/**
 * @function 功能  资讯管理业务层接口
 * @author  创建人 胡惜坤
 * @date  创建日期  July 8, 2011
 */
public interface INewsService extends IGenericService<News,String> {
	
	
	/**
	 * 方法描述：按照map中的条件找出会员资讯的信息
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getMemberNewsList(Map map);
	/**
	 * 方法描述：将资讯信息删除放入回收站里面
	 * @param java.util.Map
	 */
	public void deleteNewsInRecycle(String pk);
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
	 * @MethodDescribe 方法描述    获取前台绑定数据
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 2, 2011 9:06:31 AM
	 */
	@SuppressWarnings("unchecked")
	public List getWebNewsList(Map map);
	/**
	 * @MethodDescribe 方法描述    获取前台绑定数据条数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 2, 2011 9:06:31 AM
	 */
	@SuppressWarnings("unchecked")
	public int getWebNewsCount(Map map);
	/**
	 * @MethodDescribe 方法描述   获取标题数
	 * @author  创建人  胡惜坤
	 * @date  创建日期  Sep 2, 2011 9:06:31 AM
	 */
	@SuppressWarnings("unchecked")
	public int getInfoCount(Map map);
	 public String insertGetPk(News t, List objList);
     public void update(News t, List objList, String id);
     /**
   	 * @MethodDescribe 方法描述   各地校友按字母排序
   	 * @author  创建人  蔡毅存
   	 * @date  创建日期  Sep 2, 2011 9:06:31 AM
   	 */
   	@SuppressWarnings("unchecked")
   	public List getNewsalumniList(Map map); 
   	
   	@SuppressWarnings("unchecked")
   	public List getalumnicharList(Map map);  
	
}
