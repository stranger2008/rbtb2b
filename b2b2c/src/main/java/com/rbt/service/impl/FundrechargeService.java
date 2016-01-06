/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: FundrechargeService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IFundrechargeDao;
import com.rbt.model.Fundrecharge;
import com.rbt.service.IFundrechargeService;

/**
 * @function 功能 会员资金充值记录Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 12 13:10:48 CST 2011
 */
@Service
public class FundrechargeService extends GenericService<Fundrecharge,String> implements IFundrechargeService {

	
	IFundrechargeDao fundrechargeDao;
	@Autowired
	public FundrechargeService(IFundrechargeDao fundrechargeDao) {
		super(fundrechargeDao);
		this.fundrechargeDao = fundrechargeDao;
	}
	
}

