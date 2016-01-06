/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MembernewsDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMembernewsDao;
import com.rbt.model.Membernews;

/**
 * @function 功能 企业新闻信息dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:14:34 CST 2011
 */
@Repository
public class MembernewsDao extends GenericDao<Membernews, String> implements
		IMembernewsDao {

	public MembernewsDao() {
		super(Membernews.class);
	}

	public int getWebMembernewsCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"membernews.getWebCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	public List getWebMembernewsList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"membernews.getWebList", map);
	}

	public void auditMembernews(Map map) {
		this.getSqlMapClientTemplate()
				.update("membernews.auditMembernews", map);
	}

}
