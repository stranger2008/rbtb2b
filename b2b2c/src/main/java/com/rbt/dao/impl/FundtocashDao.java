/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: FundtocashDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IFundtocashDao;
import com.rbt.model.Fundtocash;

/**
 * @function 功能  会员资金提现记录dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 13 09:52:27 CST 2011
 */
@Repository
public class FundtocashDao extends GenericDao<Fundtocash,String> implements IFundtocashDao {

	public FundtocashDao() {
		super(Fundtocash.class);
	}
}

