/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: ILogsService.java
 */

package com.rbt.service;
import com.rbt.model.Logs;

/**
 * @function 功能 添加系统日志业务层类的接口
 * @author 创建人 林俊钦
 * @date 创建日期 Jul 5, 2011 9:38:38 AM
 */
public interface ILogsService extends IGenericService<Logs,String>{

	/**
	 * @Method Description :清空logs记录表
	 * @author : 林俊钦
	 * @date : Nov 9, 2011 1:30:03 PM
	 */
	public void deleteAlllogs();

}
