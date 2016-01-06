/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: EmailhistoryDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IEmailhistoryDao;
import com.rbt.model.Emailhistory;

/**
 * @function 功能  记录邮件发送历史记录dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Jul 15 09:47:57 CST 2011
 */
@Repository
public class EmailhistoryDao extends GenericDao<Emailhistory,String> implements IEmailhistoryDao {

	public EmailhistoryDao() {
		super(Emailhistory.class);
	}
}

