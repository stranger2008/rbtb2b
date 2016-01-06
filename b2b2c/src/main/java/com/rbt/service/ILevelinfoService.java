/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ILevelinfoService.java 
 */
package com.rbt.service;

import java.util.List;

import com.rbt.model.Levelinfo;

/**
 * @function 功能 会员级别信息Service层业务接口实现类
 * @author  创建人 邱景岩
 * @date  创建日期 Tue Jul 19 14:31:17 CST 2011
 */

public interface ILevelinfoService extends IGenericService<Levelinfo,String>{
	
	/**
	 * 方法描述：根据会员级别信息主键找出会员级别信息
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public List getLevelinfoByTime(String pk);
}

