/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IShiptemplateService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Shiptemplate;
import com.rbt.model.Supply;

/**
 * @function 功能 记录运费模板信息Service层业务接口实现类
 * @author  创建人 林俊钦
 * @date  创建日期 Thu May 24 15:00:05 CST 2012
 */

public interface IShiptemplateService extends IGenericService<Shiptemplate,String>{
	
	/**
	 * @author : 林俊钦
	 * @date : May 30, 2012 10:40:21 AM
	 * @Method Description :插入运费模板表和区域设置表
	 */
	public void insertShipMode(Shiptemplate t, List objList);
	
	/**
	 * @author : 林俊钦
	 * @date : Jun 6, 2012 1:46:22 PM
	 * @Method Description :更新运费模板表和区域设置表
	 */
	public void updateShipMode(Shiptemplate t, List objList);
}

