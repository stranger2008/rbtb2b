/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: SendmodeService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISendmodeDao;
import com.rbt.model.Sendmode;
import com.rbt.service.ISendmodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 配送方式表Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Fri Mar 23 09:55:49 CST 2012
 */
@Service
public class SendmodeService extends GenericService<Sendmode,String> implements ISendmodeService {
	
	ISendmodeDao sendmodeDao;

	@Autowired
	public SendmodeService(ISendmodeDao sendmodeDao) {
		super(sendmodeDao);
		this.sendmodeDao = sendmodeDao;
	}
	/**
	 * 批量更新sort_no排序字段
	 */
	public void updatesort_no(List list) {
		this.sendmodeDao.updateSendmode_sort(list);
	}
	
}

