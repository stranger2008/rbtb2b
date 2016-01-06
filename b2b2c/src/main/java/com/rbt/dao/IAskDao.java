/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IAskDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Ask;

/**
 * @function 功能 问题dao层业务接口
 * @author 创建人邱景岩
 * @date 创建日期 Mon Jul 25 13:25:38 CST 2011
 */

public interface IAskDao extends IGenericDao<Ask,String>{

	/**
	 * 方法描述：审核知道
	 * 
	 * @param map
	 */
	public void updateAskState(Map map);

	/**
	 * 方法描述：批量修改推荐
	 * 
	 * @param map
	 * @return java.util.List
	 */
	public void updateIsrecom(List list);

	/**
	 * @MethodDescribe 方法描述 获取前台的数据列表绑定
	 * @author 创建人 林俊钦
	 * @date 创建日期 Sep 1, 2011 10:16:49 AM
	 */
	@SuppressWarnings("unchecked")
	public List getWebAskList(Map map);

	/**
	 * @MethodDescribe 方法描述 获取前台的数据列表绑定的条数
	 * @author 创建人 林俊钦
	 * @date 创建日期 Sep 1, 2011 10:17:41 AM
	 */
	@SuppressWarnings("unchecked")
	public int getWebAskCount(Map map);

	/**
	 * @MethodDescribe 方法描述 更新浏览次数
	 * @author 创建人 胡惜坤
	 * @param pk
	 */
	public void updateClickNum(String pk);
	
    public String insertGetPk(Ask t, List objList);
    
    public void update(Ask t, List objList, String id);
}
