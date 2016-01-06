/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IEmailtemplateDao.java 
 */
package com.rbt.dao;

import com.rbt.model.Emailtemplate;

/**
 * @function 功能 记录会员邮件发送模板信息dao层业务接口
 * @author 创建人蔡毅存
 * @date 创建日期 Fri Jul 15 09:13:20 CST 2011
 */

public interface IEmailtemplateDao extends IGenericDao<Emailtemplate,String>{
	/**
	 * 方法描述：根据记录会员邮件发送模板的编号找出记录会员邮件发送模板信息
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public Emailtemplate getEmailtemplateByTempcode(String temp_code);
}
