/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMembercommentService.java 
 */
package com.rbt.service;

import com.rbt.model.Membercomment;

/**
 * @function 功能 记录会员信息评论信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Fri Jul 22 14:21:40 CST 2011
 */

public interface IMembercommentService extends IGenericService<Membercomment,String>{
	
	/**
	 * 方法描述:更新支持数
	 * @param pk
	 */
	public void updateUpNum(String pk);
	/**
	 * 方法描述:更新反对数
	 * @param pk
	 */
	public void updateDownNum(String pk);
}

