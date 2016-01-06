/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ICreditdetailService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Creditdetail;

/**
 * @function 功能 会员信用指数明细Service层业务接口实现类
 * @author  创建人 林俊钦
 * @date  创建日期 Thu Dec 08 20:44:27 CST 2011
 */

public interface ICreditdetailService extends IGenericService<Creditdetail,String>{

	/**
	 * @Method Description : 删除信用指数的表记录,特殊处理
	 * @author : 林俊钦
	 * @date : Dec 9, 2011 10:15:43 AM
	 */
	public void delcredit(Map map);
}

