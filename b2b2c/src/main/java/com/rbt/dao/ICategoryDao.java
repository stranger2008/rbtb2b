/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ICategoryDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;
import com.rbt.model.Category;

/**
 * @function 功能 分类信息表dao层业务接口
 * @author  创建人林俊钦
 * @date  创建日期 Tue Jul 12 13:04:58 CST 2011
 */

public interface ICategoryDao extends IGenericDao<Category,String>{

	 /**
	 * @MethodDescribe 方法描述    根据地区查找表中的数据
	 * @author  创建人  蔡毅存
	 * @date  创建日期  Aug 26, 2011 2:34:18 PM
	 */
	
	public List getAreaCategoryList(Map map);
	 /**
	 * @MethodDescribe 方法描述    根据地区查找表中的数据
	 * @author  创建人  蔡毅存
	 * @date  创建日期  Aug 26, 2011 2:34:18 PM
	 */
	
	public List getTwoAreaCategoryList(Map map);
   /**
	 * @MethodDescribe 方法描述    找出分类表中不在索引记录表中的数据
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 26, 2011 2:34:18 PM
	 */
	
	public List getCategoryIndexList(Map map);
	
	/**
	 * @MethodDescribe 方法描述   前台查找相应的分类
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 29, 2011 5:46:42 PM
	 */
    public List getWebCategroyList(Map map);
    
    /**
	 * @MethodDescribe 方法描述  获取所有地区信息
	 * @author  创建人  李良林
	 * @date  创建日期  2011-08-31
	 */
    public List getAll();

    /**
	 * @Method Description : 分类排序
	 * @author : 林俊钦
	 * @date : Nov 28, 2011 9:41:15 AM
	 */
	public void updateSort(final List list);
	
	/**
	 * 方法描述：分类是否显示批量修改
	 * @param interrule
	 */
	public void updateDisplay(final List list);
}

