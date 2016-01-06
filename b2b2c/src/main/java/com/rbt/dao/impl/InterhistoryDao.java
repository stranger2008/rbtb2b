/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: InterhistoryDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.rbt.dao.IFundhistoryDao;
import com.rbt.dao.IInterhistoryDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.dao.IMemberinterDao;
import com.rbt.model.Fundhistory;
import com.rbt.model.Interhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;


/**
 * @function 功能  记录会员积分异动历史dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 14 15:01:09 CST 2011
 */
@Repository
public class InterhistoryDao extends GenericDao<Interhistory,String> implements IInterhistoryDao {
	
	@Autowired
	private IMemberfundDao memberfundDao;
	@Autowired
	private IFundhistoryDao fundhistoryDao;
	@Autowired
	private IMemberinterDao memberinterDao;

	
	public InterhistoryDao() {
		super(Interhistory.class);
	}
	public int getInterhistorySumScore(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject("interhistory.getSumScore", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer.parseInt(infoMap.get("ct").toString()) : 0;
	}

	public List getReleaseCustId(Map map) {
		return this.getSqlMapClientTemplate().queryForList("interhistory.getReleaseCustId",
				map);
	}
   
	//操作积分充值
	public void insertoptioninter(Memberfund memberfund,String use_num,String session_cust_id,String session_user_id,Memberinter memberinter,String rech_fund,int rechange_value){
		String fund_num="";
		//修改资金表
		use_num=Double.toString(Double.parseDouble(use_num)-Double.parseDouble(rech_fund));
		fund_num=Double.toString(Double.parseDouble(memberfund.getFund_num())-Double.parseDouble(rech_fund));
		memberfund.setFund_num(fund_num);
		memberfund.setUse_num(use_num);
		this.memberfundDao.update(memberfund);
		//插入资金异动表
		Fundhistory fundhistory=new Fundhistory();
		fundhistory.setCust_id(session_cust_id);
		fundhistory.setFund_in("0");
		fundhistory.setFund_out(rech_fund);
		fundhistory.setBalance(use_num);
		fundhistory.setUser_id(session_user_id);
		fundhistory.setReason("兑换积分数："+Integer.parseInt(rech_fund)*rechange_value);
		fundhistory.setRemark("");
		this.fundhistoryDao.insert(fundhistory);
		//修改积分表
		if(memberinter!=null){
	    //兑换的积分数加上剩余积分数
	    String thisinter=Integer.toString(Integer.parseInt(rech_fund)*rechange_value+Integer.parseInt(memberinter.getFund_num()));
		memberinter.setFund_num(thisinter);
		this.memberinterDao.update(memberinter);
		//插入积分历史记录
		Interhistory interhistory=new Interhistory();
		interhistory.setCust_id(session_cust_id);
		interhistory.setInter_in(Integer.toString(Integer.parseInt(rech_fund)*rechange_value));
		interhistory.setInter_out("0");
		interhistory.setThisinter(thisinter);
		interhistory.setUser_id(session_user_id);
		interhistory.setReason("资金兑换："+rech_fund);
		interhistory.setRemark("");
		super.insert(interhistory);
		}
	}
	
}

