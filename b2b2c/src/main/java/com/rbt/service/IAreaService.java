/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: IAreaService.java 
 */

package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Area;
/**
 * @function 功能  地区管理业务层接口
 * @author  创建人 胡惜坤
 * @date  创建日期  Jun 28, 2011
 */
public interface IAreaService extends IGenericService<Area,String> {
	
	/**
	 * 方法描述：批量地区排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateAreaSortNo(List list);
	/**
	 * 方法描述：单个地区排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateOneAreaSortNo(Map map);
	
	/**
	 * @MethodDescribe 方法描述   获取建产索引的数据
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 12, 2011 1:05:46 PM
	 */
	@SuppressWarnings("unchecked")
	public List getAreaIndexList(Map map);
	/**
	 * @MethodDescribe 方法描述    前台的地区列表
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 29, 2011 4:37:51 PM
	 */
    public List getWebAreaList(Map map);
    /**
	 * @MethodDescribe 方法描述 取出所有地区
	 * @author  创建人  李良林
	 * @date  创建日期  2011-08-31
	 */
    public List getAll();
    /**
	 * @Method Description : 根据地区ID获取
	 * @author : 林俊钦
	 * @date : Nov 1, 2011 10:51:08 AM
	 */
    public List getWebAreaIndexList(Map map);
    /**
	 * @Method Description : 根据地区ID获取
	 * @author : 蔡毅存
	 * @date : Nov 1, 2012 10:51:08 AM
	 */
    public List getAreaCityList(Map map);
    /**
	 * @Method Description : 根据地区ID获取
	 * @author : 蔡毅存
	 * @date : Nov 1, 2012 10:51:08 AM
	 */
    public List getCharacterList(Map map);
    /**
	 * @Method Description : 根据国家获取
	 * @author : 蔡毅存
	 * @date : Nov 1, 2012 10:51:08 AM
	 */
    public List getCountryList(Map map);
}
