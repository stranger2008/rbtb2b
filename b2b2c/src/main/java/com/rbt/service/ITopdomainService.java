/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ITopdomainService.java 
 */
package com.rbt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.Topdomain;

/**
 * @function 功能 记录会员申请的顶级域名申请信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Mon Aug 01 14:24:27 CST 2011
 */

public interface ITopdomainService extends IGenericService<Topdomain,String>{

	/**
	 * 方法描述：修改记录会员申请的顶级域名状态
	 * @param topdomain
	 */
	public void updateenabled(Topdomain topdomain);
	
	
	public String domainRetUrl(String subdomain);

}

