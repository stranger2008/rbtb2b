/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberfundDao.java 
 */
package com.rbt.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rbt.dao.IFundhistoryDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.model.Fundhistory;
import com.rbt.model.Memberfund;
import com.rbt.service.IFundhistoryService;

/**
 * @function 功能  会员资金dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 12 09:26:58 CST 2011
 */
@Repository
public class MemberfundDao extends GenericDao<Memberfund,String> implements IMemberfundDao {

	public MemberfundDao() {
		super(Memberfund.class);
	}
	
	@Autowired
	public IFundhistoryDao fundhistoryDao;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberfundDao#updateMemberfund(java.util.Map)
	 */
	public void updateMemberfund(Map map) {
		this.getSqlMapClientTemplate().update("memberfund.updatenum", map);
	}
	//修改会员资金
	public void insertfundoption(String cust_id,String session_user_id,String fund_num){
		Memberfund memberfund=super.get(cust_id);
		memberfund.setFund_num(String.valueOf(Double.valueOf(memberfund.getFund_num())+Double.valueOf(fund_num)));
		String value_usefund=String.valueOf(Double.valueOf(memberfund.getUse_num())+Double.valueOf(fund_num));
		memberfund.setUse_num(value_usefund);
		// 实例化fundhistory
		Fundhistory fundhistory = new Fundhistory();
		fundhistory.setCust_id(memberfund.getCust_id());
		fundhistory.setFund_in(fund_num);
		fundhistory.setFund_out("0");
		fundhistory.setBalance(value_usefund);
		fundhistory.setUser_id(session_user_id);
		fundhistory.setReason("充值");
		this.fundhistoryDao.insert(fundhistory);
		super.update(memberfund);
	}

}

