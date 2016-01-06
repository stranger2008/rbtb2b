/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: CredithistoryService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ICredithistoryDao;
import com.rbt.model.Credithistory;
import com.rbt.service.ICredithistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录会员信用指数历史Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Nov 30 13:35:49 CST 2011
 */
@Service
public class CredithistoryService extends GenericService<Credithistory,String> implements ICredithistoryService {
	
	ICredithistoryDao credithistoryDao;

	@Autowired
	public CredithistoryService(ICredithistoryDao credithistoryDao) {
		super(credithistoryDao);
		this.credithistoryDao = credithistoryDao;
	}
	
}

