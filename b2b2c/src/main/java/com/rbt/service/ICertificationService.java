/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ICertificationService.java 
 */
package com.rbt.service;

import com.rbt.model.Certification;

/**
 * @function 功能 记录企业身份认证信息Service层业务接口实现类
 * @author  创建人 林俊钦
 * @date  创建日期 Wed Nov 30 13:34:38 CST 2011
 */

public interface ICertificationService extends IGenericService<Certification,String>{
	/**
	 * @Method Description :审核会员实名认证
	 * @author : 林俊钦
	 * @date : Dec 2, 2011 4:17:25 PM
	 */
	public void auditState(Certification t);
	/**
	 * @Method Description : 插入会员信用指数表
	 * @author : 林俊钦
	 * @date : Dec 2, 2011 3:15:20 PM
	 */
	//第一个参数需要操作的CUST_ID,第二个参数传正负一，第三个参数指数值,第四个参数理由类型，第五个参数为理由内容,第六个文件名称
	public void creditChangeNum(String cust_id, int symbol, String fund_value, String reason_type, String reason, String title);
}

