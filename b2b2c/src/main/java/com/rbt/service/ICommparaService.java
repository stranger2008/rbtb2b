/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: ICommparaService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Commpara;

/**
 * @function 功能  系统参数业务层接口
 * @author  创建人 胡惜坤
 * @date  创建日期  July 6, 2011
 */
public interface ICommparaService extends IGenericService<Commpara,String> {

    /**
	 * @MethodDescribe 方法描述    对para_code类型进行分组用来创建动态的系统参数MAP
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 1, 2011 2:05:42 PM
	 */
     @SuppressWarnings("unchecked")
	public List getCommparaGroupList(Map map);
     /**
	 * @MethodDescribe 方法描述    建立参数表索引数据
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 26, 2011 2:53:13 PM
	 */
 	public List getCommparaIndexList(Map map);
 	/**
	 * @MethodDescribe 方法描述    前台相应的参数值
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 29, 2011 4:56:14 PM
	 */
 	public List getWebCommparaList(Map map);
 	/**
	 * 方法描述：批量修改是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateenabled(List list);
	
	/**
	 * 方法描述：批量修改排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updatesort_no(List list);
}
