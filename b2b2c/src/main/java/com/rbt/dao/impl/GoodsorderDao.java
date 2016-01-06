/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: GoodsorderDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IGoodsorderDao;
import com.rbt.model.Goodsorder;

/**
 * @function 功能  订单商品表dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Mon Mar 19 15:53:24 CST 2012
 */
@Repository
public class GoodsorderDao extends GenericDao<Goodsorder,String> implements IGoodsorderDao {
	
	public GoodsorderDao() {
		super(Goodsorder.class);
	}
	public int getCountBuyOut(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getCountBuyOut", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	public List getListBuyOut(Map map) {
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getListBuyOut",map);
	}
	
	public void update(Map<String,String> map){
		this.getSqlMapClientTemplate().update(getModelName()+".updateState", map);
	}
}

