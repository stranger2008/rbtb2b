/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMemberuserService.java 
 */
package com.rbt.service;

import java.util.List;

import com.rbt.model.Memberuser;

/**
 * @function 功能 用户账号Service层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Jul 19 09:37:16 CST 2011
 */

public interface IMemberuserService extends IGenericService<Memberuser, String> {
	
	/**
	 * 方法描述：插入用户并且返回用户ID
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
	public Memberuser getMemberuserByusername(String username);
}
