/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: InterhistoryService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IInterhistoryDao;
import com.rbt.model.Interhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.service.IInterhistoryService;

/**
 * @function 功能 记录会员积分异动历史Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 14 15:01:09 CST 2011
 */
@Service
public class InterhistoryService extends GenericService<Interhistory,String> implements IInterhistoryService {

	IInterhistoryDao interhistoryDao;

	@Autowired
	public InterhistoryService(IInterhistoryDao interhistoryDao) {
		super(interhistoryDao);
		this.interhistoryDao = interhistoryDao;
	}

	public int getInterhistorySumScore(Map map) {
		return this.interhistoryDao.getInterhistorySumScore(map);
	}

	public List getReleaseCustId(Map map) {
		return this.interhistoryDao.getReleaseCustId(map);
	}
    //资金兑换积分
	public void optioninter(Memberfund memberfund,String use_num,String session_cust_id,String session_user_id,Memberinter memberinter,String rech_fund,int rechange_value){
		this.interhistoryDao.insertoptioninter(memberfund, use_num, session_cust_id, session_user_id, memberinter, rech_fund, rechange_value);
	}
}

