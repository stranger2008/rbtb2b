/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: CreditdetailService.java 
 */
package com.rbt.service.impl;

import java.util.Map;

import com.rbt.dao.ICreditdetailDao;
import com.rbt.model.Creditdetail;
import com.rbt.service.ICreditdetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 会员信用指数明细Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Dec 08 20:44:27 CST 2011
 */
@Service
public class CreditdetailService extends GenericService<Creditdetail,String> implements ICreditdetailService {
	
	ICreditdetailDao creditdetailDao;

	@Autowired
	public CreditdetailService(ICreditdetailDao creditdetailDao) {
		super(creditdetailDao);
		this.creditdetailDao = creditdetailDao;
	}

	public void delcredit(Map map) {
		this.creditdetailDao.delcredit(map);
	}
	
}

