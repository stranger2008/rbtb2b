/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberlinkDao.java 
 */
package com.rbt.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMemberlinkDao;
import com.rbt.model.Memberlink;

/**
 * @function 功能 企业友情链接信息dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:16:25 CST 2011
 */
@Repository
public class MemberlinkDao extends GenericDao<Memberlink, String> implements
		IMemberlinkDao {

	public MemberlinkDao() {
		super(Memberlink.class);
	}

	public void auditMemberlink(Map map) {
		this.getSqlMapClientTemplate()
				.update("memberlink.auditMemberlink", map);
	}

}
