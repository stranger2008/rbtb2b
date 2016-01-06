/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMemberlinkService.java 
 */
package com.rbt.service;

import java.util.Map;

import com.rbt.model.Memberlink;

/**
 * @function 功能 企业友情链接信息Service层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:16:25 CST 2011
 */

public interface IMemberlinkService extends IGenericService<Memberlink, String> {

	/**
	 * 方法描述：审核企业友情链接信息
	 * 
	 * @param member
	 */
	public void auditMemberlink(Map map);

}
