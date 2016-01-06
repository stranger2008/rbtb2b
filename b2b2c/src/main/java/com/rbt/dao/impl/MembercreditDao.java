/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MembercreditDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMembercreditDao;
import com.rbt.model.Membercredit;

/**
 * @function 功能  记录会员信用指数dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Nov 30 13:37:20 CST 2011
 */
@Repository
public class MembercreditDao extends GenericDao<Membercredit,String> implements IMembercreditDao {
	
	public MembercreditDao() {
		super(Membercredit.class);
	}
	
}

