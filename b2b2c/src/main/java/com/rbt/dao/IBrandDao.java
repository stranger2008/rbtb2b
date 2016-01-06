/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IBrandDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Brand;

/**
 * @function 功能 品牌信息dao层业务接口
 * @author 创建人邱景岩
 * @date 创建日期 Tue Nov 08 09:15:10 CST 2011
 */

public interface IBrandDao extends IGenericDao<Brand,String>{

	/**
	 * 方法描述：批量修改推荐
	 * 
	 * @param map
	 * @return java.util.List
	 */
	public void updateIsrecom(List list);

	/**
	 * 方法描述：审核品牌
	 * 
	 * @param brand
	 */
	public void updateBrandState(Map map);

	/**
	 * 方法描述：前台产品数据列表的绑定
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getWebBrandList(Map map);

	/**
	 * 方法描述：前台产品数据列表绑定的条数
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getWebBrandCount(Map map);
	
    public String insertGetPk(Brand t, List objList);
    
    public void update(Brand t, List objList, String id);
}
