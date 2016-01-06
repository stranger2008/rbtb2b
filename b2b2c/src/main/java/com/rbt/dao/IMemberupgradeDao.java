/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMemberupgradeDao.java 
 */
package com.rbt.dao;

import java.util.Map;

import com.rbt.model.Memberupgrade;

/**
 * @function 功能 会员升级记录信息dao层业务接口
 * @author 创建人邱景岩
 * @date 创建日期 Fri Jul 29 16:37:21 CST 2011
 */

public interface IMemberupgradeDao extends IGenericDao<Memberupgrade, String> {

	/**
	 * 方法描述：审核会员升级
	 * 
	 * @param member
	 */
	public void auditMemberupgrade(Map map);

	/**
	 * 方法描述：根据会员升级记录信息申请用户名和会员标识找出会员升级记录信息
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public Memberupgrade getMemberupgradeByName(Map map);
}
