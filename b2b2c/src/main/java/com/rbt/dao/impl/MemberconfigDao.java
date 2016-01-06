/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberconfigDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IMemberconfigDao;
import com.rbt.model.Memberconfig;

/**
 * @function 功能  记录会员企业站设置信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Aug 29 16:12:02 CST 2011
 */
@Repository
public class MemberconfigDao extends GenericDao<Memberconfig,String> implements IMemberconfigDao {

	public MemberconfigDao() {
		super(Memberconfig.class);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberconfigDao#updateMemberconfig(com.rbt.model.Memberconfig)
	 */
	public void updateLognum(String cust_id) {
		this.getSqlMapClientTemplate().update("memberconfig.update_loc_num", cust_id);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberconfigDao#updateMemberconfig(com.rbt.model.Memberconfig)
	 */
	public void updatetempcode(Memberconfig memberconfig) {
		this.getSqlMapClientTemplate().update("memberconfig.update_tempcode", memberconfig);
	}

}

