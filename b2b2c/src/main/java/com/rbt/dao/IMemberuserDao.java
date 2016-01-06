/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMemberuserDao.java 
 */
package com.rbt.dao;

import java.util.List;

import com.rbt.model.Memberuser;

/**
 * @function 功能 用户账号dao层业务接口
 * @author 创建人邱景岩
 * @date 创建日期 Tue Jul 19 09:37:16 CST 2011
 */

public interface IMemberuserDao extends IGenericDao<Memberuser, String> {
	/**
	 * 方法描述：插入用户账号
	 * 
	 * @param memberuser
	 */
	public String insertMemberuser(Memberuser memberuser);

	/**
	 * 方法描述：修改用户密码
	 * 
	 * @param memberuser
	 */
	public void updatePassword(Memberuser memberuser);

	/**
	 * 方法描述：批量修改密码
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updatePasswdBatch(List list);

	/**
	 * 方法描述：根据用户账号username找出用户账号
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public Memberuser getMemberuserByname(String username);
	
	/**
	 * 
	 * 根据会员标识cust_id删除用户信息
	 */
	public void deleteByCustId(String cust_id);
	
}
