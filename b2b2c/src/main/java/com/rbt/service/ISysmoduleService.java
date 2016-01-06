/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ISysmoduleService.java 
 */
package com.rbt.service;

import java.util.List;
import com.rbt.model.Sysmodule;

/**
 * @function 功能 系统模块表Service层业务接口实现类
 * @author  创建人 林俊钦
 * @date  创建日期 Fri Jan 13 12:48:48 CST 2012
 */

public interface ISysmoduleService extends IGenericService<Sysmodule,String>{
	/**
	 * 方法描述：模块排序
	 * @param interrule
	 */
	public void updateSort(final List list);
	
	/**
	 * 方法描述：批量修改启用
	 * @param pk
	 * @return interrule
	 */
	public void updateisuse(final List list);
}

