/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: FundhistoryService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IFundhistoryDao;
import com.rbt.model.Fundhistory;
import com.rbt.service.IFundhistoryService;

/**
 * @function 功能 会员资金流Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 13 10:06:11 CST 2011
 */
@Service
public class FundhistoryService extends GenericService<Fundhistory,String> implements IFundhistoryService {

	IFundhistoryDao fundhistoryDao;

	@Autowired
	public FundhistoryService(IFundhistoryDao fundhistoryDao) {
		super(fundhistoryDao);
		this.fundhistoryDao = fundhistoryDao;
	}

}

