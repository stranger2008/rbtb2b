/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ITopdomainDao.java 
 */
package com.rbt.dao;

import com.rbt.model.Topdomain;

/**
 * @function 功能 记录会员申请的顶级域名申请信息dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Mon Aug 01 14:24:27 CST 2011
 */

public interface ITopdomainDao extends IGenericDao<Topdomain,String>{
	
	/**
	 * 方法描述：修改记录会员申请的顶级域名状态
	 * @param topdomain
	 */
	public void updateenabled(Topdomain topdomain);

}

