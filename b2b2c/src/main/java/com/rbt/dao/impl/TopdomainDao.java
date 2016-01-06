/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: TopdomainDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.ITopdomainDao;
import com.rbt.model.Topdomain;

/**
 * @function 功能  记录会员申请的顶级域名申请信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Aug 01 14:24:27 CST 2011
 */
@Repository
public class TopdomainDao extends GenericDao<Topdomain,String> implements ITopdomainDao {

	
	public TopdomainDao() {
		super(Topdomain.class);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.ITopdomainDao#updateTopdomain(com.rbt.model.Topdomain)
	 */
	public void updateenabled(Topdomain topdomain) {
		this.getSqlMapClientTemplate().update("topdomain.updateenabled", topdomain);
	}


}

