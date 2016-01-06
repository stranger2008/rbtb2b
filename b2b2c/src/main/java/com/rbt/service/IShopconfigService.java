/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IShopconfigService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Shopconfig;

/**
 * @function 功能 商店设置Service层业务接口实现类
 * @author  创建人 hxk
 * @date  创建日期 Tue Feb 28 10:24:54 CST 2012
 */

public interface IShopconfigService extends IGenericService<Shopconfig,String>{
	
	public Shopconfig GetByCustId(String id);
}

