/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: SellerscoreDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISellerscoreDao;
import com.rbt.model.Sellerscore;

/**
 * @function 功能  记录商家打分信息dao层业务接口实现类
 * @author 创建人 胡惜坤
 * @date 创建日期 Fri Mar 30 11:03:31 CST 2012
 */
@Repository
public class SellerscoreDao extends GenericDao<Sellerscore,String> implements ISellerscoreDao {
	
	public SellerscoreDao() {
		super(Sellerscore.class);
	}
	public int getIndexCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getindexCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	public List getCountList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getcountList",map);
	}

}

