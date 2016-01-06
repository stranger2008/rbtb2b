/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: CredithistoryDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ICredithistoryDao;
import com.rbt.model.Credithistory;

/**
 * @function 功能  记录会员信用指数历史dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Nov 30 13:35:49 CST 2011
 */
@Repository
public class CredithistoryDao extends GenericDao<Credithistory,String> implements ICredithistoryDao {
	
	public CredithistoryDao() {
		super(Credithistory.class);
	}
	
}

