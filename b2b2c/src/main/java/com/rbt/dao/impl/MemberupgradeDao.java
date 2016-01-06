/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberupgradeDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMemberupgradeDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Memberupgrade;

/**
 * @function 功能  会员升级记录信息dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Fri Jul 29 16:37:21 CST 2011
 */
@Repository
public class MemberupgradeDao extends GenericDao<Memberupgrade,String> implements IMemberupgradeDao {

	public MemberupgradeDao() {
		super(Memberupgrade.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.IMemberupgradeDao#auditMemberupgrade(java.util.Map)
	 */
	public void auditMemberupgrade(Map map) {
		this.getSqlMapClientTemplate().update("memberupgrade.auditMemberupgrade", map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.IMemberupgradeDao#getMemberupgradeByName(java.util.Map)
	 */
	public Memberupgrade getMemberupgradeByName(Map map) {
		return (Memberupgrade) this.getSqlMapClientTemplate().queryForObject("memberupgrade.getByName", map);
	}

}

