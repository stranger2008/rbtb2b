/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IInfoattrService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Infoattr;

/**
 * @function 功能 信息属性Service层业务接口实现类
 * @author  创建人 邱景岩
 * @date  创建日期 Sat Mar 17 10:32:08 CST 2012
 */

public interface IInfoattrService extends IGenericService<Infoattr,String>{
	public List getDetailList(Map map);
}

