/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: LogsService.java
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.service.ILogsService;
import com.rbt.model.Logs;
import com.rbt.dao.IAboutusDao;
import com.rbt.dao.ILogsDao;

/**
 * @function 功能 添加角色业务层类
 * @author 创建人 林俊钦
 * @date 创建日期 Jun 28, 2011 3:25:23 PM
 */
@Service
public class LogsService extends GenericService<Logs,String>implements ILogsService {

	/*
	 * 系统日志实现层接口
	 */
	@Autowired
	private ILogsDao logsDao;
	
	IAboutusDao aboutusDao;

	@Autowired
	public LogsService(ILogsDao logsDao) {
		super(logsDao);
		this.logsDao = logsDao;
	}

	public void deleteAlllogs() {
		logsDao.deleteAlllogs();
	}


}
