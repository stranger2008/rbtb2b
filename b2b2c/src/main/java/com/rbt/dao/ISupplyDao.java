/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ISupplyDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;
import com.rbt.model.Supply;

/**
 * @function 功能 供应表dao层业务接口
 * @author  创建人林俊钦
 * @date  创建日期 Thu Jul 21 17:15:43 CST 2011
 */

public interface ISupplyDao  extends IGenericDao<Supply,String>{
	/**
	 * @MethodDescribe 方法描述    找出供应表中所有的记录
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 15, 2011 3:15:16 PM
	 */
	public List getSupplyIndexList(Map map);
	
	/**
	 * @MethodDescribe 方法描述    前台供应数据绑定列表
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 29, 2011 3:32:52 PM
	 */
    public List getWebSupplyList(Map map);
    
    /**
	 * @MethodDescribe 方法描述    前台找出供应表的记录数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 29, 2011 3:44:40 PM
	 */
	public int getWebSupplyCount(Map map);
	
	/**
	 * @MethodDescribe 方法描述    更新浏览次数
	 * @author  创建人  胡惜坤
	 * @param pk
	 */
	public void updateClickNum(String pk);
	
	/**
	 * @MethodDescribe 方法描述    前台供应数据绑定列表
	 * @author  创建人  蔡毅存
	 * @date  创建日期  Aug 29, 2011 3:32:52 PM
	 */
    public List getCatSupplyList(Map map);
    
	/**
	 * @Method Description :批量修改推荐
	 * @author : 林俊钦
	 * @date : Nov 28, 2011 9:55:16 AM
	 */
	@SuppressWarnings("unchecked")
	public void updateSupplyState(List lists);
	
	
	/**
	 * @MethodDescribe 方法描述    前台供应关键字，赞助商广告列表数据
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 29, 2011 3:31:13 PM
	 */
    public List getWebSupplyAdsList(Map map);
    
    public String insertGetPk(Supply t, List objList);
    
    public void update(Supply t, List objList, String id);
}

