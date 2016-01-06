/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: MembercreditFuc.java 
 */
package com.rbt.function;

import com.rbt.model.Membercredit;
import com.rbt.service.IMembercreditService;
/**
 * @function 功能 信用指数表的相关操作
 * @author  创建人 邱景岩  
 * @date  创建日期 Feb 22, 2012 5:33:25 PM
 */
public class MembercreditFuc extends CreateSpringContext{
	//根据公司cust_id找到其公司的信用指数
	public static Membercredit getMembercreditByPk(String cust_id){
		return  getMembercreditObj().get(cust_id);
	}
	//从Spring容器中获取招聘业务Bean
	public static IMembercreditService getMembercreditObj(){
		return (IMembercreditService)getContext().getBean("membercreditService");
	}
}
