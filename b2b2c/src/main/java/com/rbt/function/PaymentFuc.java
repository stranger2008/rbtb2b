/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;

import com.rbt.model.Payment;
import com.rbt.service.IPaymentService;

/**
 * @function 功能  获取支付宝账户信息
 * @author  创建人 蔡毅存
 * @date  创建日期  2011-11-10
 */
public class PaymentFuc extends CreateSpringContext{
	@SuppressWarnings("unchecked")
	public static List paymentlist;
	public static Payment getPayment(String pay_code){
		//修改用户账户信息
		IPaymentService paymentService = (IPaymentService)getContext().getBean("paymentService");
		String pay_id="";
		paymentlist=null;
		Payment payment=new Payment();
		HashMap map=new HashMap();
		map.put("pay_code", pay_code);
		paymentlist=paymentService.getList(map);
		if(paymentlist != null && paymentlist.size()>0){
			map=(HashMap)paymentlist.get(0);
			payment.setSeller_name(map.get("seller_name").toString());
			payment.setPay_account(map.get("pay_account").toString());
			payment.setPasswd(map.get("passwd").toString());
		}
		return payment;
		//修改资金修改记录
	}
}
