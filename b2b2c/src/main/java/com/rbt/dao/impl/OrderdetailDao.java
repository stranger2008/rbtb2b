/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: OrderdetailDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IOrderdetailDao;
import com.rbt.model.Orderdetail;

/**
 * @function 功能  订单商品详细dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Mar 28 17:27:56 CST 2012
 */
@Repository
public class OrderdetailDao extends GenericDao<Orderdetail,String> implements IOrderdetailDao {
	
	public OrderdetailDao() {
		super(Orderdetail.class);
	}
	
	public List getdetailList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("orderdetail.getdetailList",map);
	}
	
	public int getdetailCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getdetailCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
}

