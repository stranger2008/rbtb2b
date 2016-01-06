/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ICreditdetailDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Creditdetail;

/**
 * @function 功能 会员信用指数明细dao层业务接口
 * @author  创建人林俊钦
 * @date  创建日期 Thu Dec 08 20:44:27 CST 2011
 */

public interface ICreditdetailDao extends IGenericDao<Creditdetail,String>{
	/**
	 * @Method Description : 删除信用指数的表记录,特殊处理
	 * @author : 林俊钦
	 * @date : Dec 9, 2011 10:15:43 AM
	 */
	public void delcredit(Map map);
}

