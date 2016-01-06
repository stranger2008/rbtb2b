/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IAskService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Ask;

/**
 * @function 功能 问题Service层业务接口实现类
 * @author  创建人 邱景岩
 * @date  创建日期 Mon Jul 25 13:25:38 CST 2011
 */

public interface IAskService extends IGenericService<Ask,String>{
	
	/**
	 * 方法描述：审核知道
	 * @param map
	 */
	public void updateAskState(Map map);
	
    /**
	 * @MethodDescribe 方法描述    获取前台的数据列表绑定
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 1, 2011 10:16:49 AM
	 */
	@SuppressWarnings("unchecked")
	public List getWebAskList(Map map);
    /**
	 * @MethodDescribe 方法描述    获取前台的数据列表绑定的条数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 1, 2011 10:17:41 AM
	 */
	@SuppressWarnings("unchecked")
	public int getWebAskCount(Map map);
	/**
	 * @MethodDescribe 方法描述    更新浏览次数
	 * @author  创建人  胡惜坤
	 */
	public void updateClickNum(String pk);
	/**
	 * 方法描述：批量修改推荐
	 * 
	 * @param map
	 * @return java.util.List
	 */
	public void updateIsrecom(List list);
	
    public String insertGetPk(Ask t, List objList);
    
    public void update(Ask t, List objList, String id);
}

