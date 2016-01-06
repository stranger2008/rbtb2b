/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MembercertDao.java 
 */
package com.rbt.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMembercertDao;
import com.rbt.model.Membercert;

/**
 * @function 功能 会员荣誉资质信息dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:11:13 CST 2011
 */
@Repository
public class MembercertDao extends GenericDao<Membercert, String> implements
		IMembercertDao {

	public MembercertDao() {
		super(Membercert.class);
	}

	public void auditMembercert(Map map) {
		this.getSqlMapClientTemplate()
				.update("membercert.auditMembercert", map);
	}

}
