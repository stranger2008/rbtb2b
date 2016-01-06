/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: SmshistoryDao.java 
 */
package com.rbt.dao.impl;
import org.springframework.stereotype.Repository;
import com.rbt.dao.ISmshistoryDao;
import com.rbt.model.Smshistory;

/**
 * @function 功能  记录短信发送历史记录dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 19 16:23:30 CST 2011
 */
@Repository
public class SmshistoryDao extends GenericDao<Smshistory,String> implements ISmshistoryDao {


	public SmshistoryDao() {
		super(Smshistory.class);
	}
}

