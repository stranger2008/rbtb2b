/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IBuyDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;
import com.rbt.model.Buy;

/**
 * @function 功能 求购表dao层业务接口
 * @author  创建人林俊钦
 * @date  创建日期 Fri Jul 29 14:52:50 CST 2011
 */

public interface IBuyDao extends IGenericDao<Buy,String>{
	
	/**
	 * @MethodDescribe 方法描述    前台求购数据绑定列表
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 29, 2011 3:32:52 PM
	 */
    public List getWebBuyList(Map map);
    /**
	 * @MethodDescribe 方法描述    前台找出求购表的记录数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 29, 2011 3:44:40 PM
	 */
	public int getWebBuyCount(Map map);
	
	/**
	 * @Method Description : 更新点击量
	 * @author : 林俊钦
	 * @date : Nov 28, 2011 9:37:56 AM
	 */
	public void updateClickNum(String pk);
	
	/**
	 * @MethodDescribe 方法描述    前台求购数据绑定列表
	 * @author  创建人  蔡毅存
	 * @date  创建日期  Aug 29, 2011 3:32:52 PM
	 */	
    public List getCatBuyList(Map map);
	
    /**
	 * @Method Description :批量修改推荐
	 * @author : 林俊钦
	 * @date : Nov 28, 2011 9:38:29 AM
	 */
	@SuppressWarnings("unchecked")
	public void updateBuyState(List lists);
	
	public String insertGetPk(Buy t, List objList);
	
	public void update(Buy t, List objList, String id);
}

