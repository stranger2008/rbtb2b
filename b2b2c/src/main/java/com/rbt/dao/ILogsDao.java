/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ILogsDao.java
 */
package com.rbt.dao;

import com.rbt.model.Logs;

/**
 * @function 功能 系统日志功能实现接口层
 * @author 创建人 林俊钦
 * @date 创建日期 Jul 5, 2011 9:35:51 AM
 */
public interface ILogsDao extends IGenericDao<Logs,String>{

	/**
	 * @Method Description :清空logs记录表
	 * @author : 林俊钦
	 * @date : Nov 9, 2011 1:30:03 PM
	 */
	public void deleteAlllogs();
}
