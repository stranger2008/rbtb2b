/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: CreditdetailDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ICreditdetailDao;
import com.rbt.model.Creditdetail;

/**
 * @function 功能  会员信用指数明细dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Dec 08 20:44:27 CST 2011
 */
@Repository
public class CreditdetailDao extends GenericDao<Creditdetail,String> implements ICreditdetailDao {
	
	public CreditdetailDao() {
		super(Creditdetail.class);
	}

	public void delcredit(Map map) {
		this.getSqlMapClientTemplate().delete("creditdetail.delcredit", map);
	}
	
}

