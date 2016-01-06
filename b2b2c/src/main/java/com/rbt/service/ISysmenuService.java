/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: ISysuserService.java 
 */

package com.rbt.service;

import java.util.List;

import com.rbt.model.Sysmenu;

/**
 * @function 功能  系统菜单业务层接口
 * @author  创建人 李良林
 * @date  创建日期  Jun 25, 2011
 */
public interface ISysmenuService extends IGenericService<Sysmenu,String>{
	
	/**
	 * 方法描述：菜单是否可用批量修改
	 * @param interrule
	 */
	public void updateEnable(final List list);
}
