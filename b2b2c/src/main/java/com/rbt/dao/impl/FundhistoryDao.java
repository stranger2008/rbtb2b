/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: FundhistoryDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IFundhistoryDao;
import com.rbt.model.Fundhistory;

/**
 * @function 功能  会员资金流dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 13 10:06:11 CST 2011
 */
@Repository
public class FundhistoryDao extends GenericDao<Fundhistory,String> implements IFundhistoryDao {

	public FundhistoryDao() {
		super(Fundhistory.class);
	}
}

