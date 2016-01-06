/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: SmshistoryService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.ISmshistoryDao;
import com.rbt.model.Smshistory;
import com.rbt.service.ISmshistoryService;

/**
 * @function 功能 记录短信发送历史记录Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 19 16:23:30 CST 2011
 */
@Service
public class SmshistoryService extends GenericService<Smshistory,String> implements ISmshistoryService {

	
	ISmshistoryDao smshistoryDao;

	@Autowired
	public SmshistoryService(ISmshistoryDao smshistoryDao) {
		super(smshistoryDao);
		this.smshistoryDao = smshistoryDao;
	}
}

