/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;

import com.rbt.model.Fundrecharge;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IFundrechargeService;

/**
 * @function 功能 用户支付后修改账户
 * @author 创建人 蔡毅存
 * @date 创建日期 2011-11-09
 */
public class MemberfundFuc extends CreateSpringContext {

	public static void updateMemberfund(String cust_id, String money,
			String payplat) {
		
		IMemberfundService memberfundService = (IMemberfundService) getContext()
		.getBean("memberfundService");
		// 修改用户账户信息
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("cust_id", cust_id);
		map.put("addfund", money);
		memberfundService.updateMemberfund(map);
		// 插入资金修改记录
		IFundrechargeService fundrechargeService = (IFundrechargeService) getContext()
				.getBean("fundrechargeService");
		Fundrecharge fundrecharge = new Fundrecharge();
		fundrecharge.setCust_id(cust_id);
		fundrecharge.setPayplat(payplat);
		fundrecharge.setFund_num(money);
		fundrecharge.setUser_id("0");
		fundrecharge.setOrder_state("0");
		fundrecharge.setRemark("账户充值");
		fundrechargeService.insert(fundrecharge);

	}
}
