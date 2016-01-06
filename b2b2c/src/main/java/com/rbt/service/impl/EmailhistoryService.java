/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: EmailhistoryService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IEmailhistoryDao;
import com.rbt.model.Emailhistory;
import com.rbt.service.IEmailhistoryService;

/**
 * @function 功能 记录邮件发送历史记录Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Jul 15 09:47:57 CST 2011
 */
@Service
public class EmailhistoryService extends GenericService<Emailhistory,String> implements IEmailhistoryService {

	
	IEmailhistoryDao emailhistoryDao;

	@Autowired
	public EmailhistoryService(IEmailhistoryDao emailhistoryDao) {
		super(emailhistoryDao);
		this.emailhistoryDao = emailhistoryDao;
	}
}

