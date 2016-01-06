/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IProductService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Product;
import com.rbt.model.Supply;

/**
 * @function 功能 产品表Service层业务接口实现类
 * @author  创建人 胡惜坤
 * @date  创建日期 Mon Jul 25 17:02:42 CST 2011
 */

public interface IProductService extends IGenericService<Product,String> {
	/**
	 * 方法描述：更新审核状态
	 * @param map
	 */
	public void updateState(Map map);
	/**
	 * 方法描述：按照map中的条件找出用户自定义分类的信息
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getMemberCatList(String custid);
	public void updateClickNum(String pk);
	
	/**
	 * @MethodDescribe 方法描述    前台产品数据列表的绑定
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 2, 2011 4:42:46 PM
	 */
	@SuppressWarnings("unchecked")
	public List getWebProductList(Map map);
    /**
	 * @MethodDescribe 方法描述    前台产品数据列表绑定的条数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 2, 2011 4:43:29 PM
	 */
	@SuppressWarnings("unchecked")
	public int getWebProductCount(Map map);
	/**
	 * @MethodDescribe 方法描述    前台找出产品表数据
	 * @author  创建人  蔡毅存
	 * @date  创建日期  Aug 29, 2011 3:31:13 PM
	 */
    public List getCatProductList(Map map);
    /**
     * @MethodDescribe 方法描述   修改推荐与不推荐状态
 	 * @author  创建人  胡惜坤
 	 * @date  创建日期  sep 26, 2011 3:32:52 PM
     */
     public void updateRecommendState(List list);
     public String insertGetPk(Product t, List objList);
     public void update(Product t, List objList, String id);
}

