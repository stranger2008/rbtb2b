/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IAreasetService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Areaset;

/**
 * @function 功能 记录区域设置信息Service层业务接口实现类
 * @author  创建人 胡惜坤
 * @date  创建日期 Wed Mar 28 13:22:27 CST 2012
 */

public interface IAreasetService extends IGenericService<Areaset,String>{
	public void deleteByShopid(String id);
}

