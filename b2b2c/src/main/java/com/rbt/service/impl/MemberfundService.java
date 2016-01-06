/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberfundService.java 
 */
package com.rbt.service.impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMemberfundDao;
import com.rbt.model.Memberfund;
import com.rbt.service.IMemberfundService;

/**
 * @function 功能 会员资金Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 12 09:26:58 CST 2011
 */
@Service
public class MemberfundService extends GenericService<Memberfund,String> implements IMemberfundService {

	IMemberfundDao memberfundDao;

	@Autowired
	public MemberfundService(IMemberfundDao memberfundDao) {
		super(memberfundDao);
		this.memberfundDao = memberfundDao;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberfundService#updateMemberfund(java.util.Map)
	 */
	public void updateMemberfund(Map map) {
		this.memberfundDao.updateMemberfund(map);
	}
	//修改会员资金
	public void insertfundoption(String cust_id,String session_user_id,String fund_num){
		this.memberfundDao.insertfundoption(cust_id, session_user_id, fund_num);
	}

}

