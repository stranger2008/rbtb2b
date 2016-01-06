/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IInfoattrDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Infoattr;

/**
 * @function 功能 信息属性dao层业务接口
 * @author  创建人邱景岩
 * @date  创建日期 Sat Mar 17 10:32:08 CST 2012
 */

public interface IInfoattrDao extends IGenericDao<Infoattr,String>{
	public List getDetailList(Map map);
}

