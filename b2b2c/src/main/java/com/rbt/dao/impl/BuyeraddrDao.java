/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: BuyeraddrDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IBuyeraddrDao;
import com.rbt.model.Buy;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Categoryattr;

/**
 * @function 功能  买家收货地址表dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Oct 21 10:48:38 CST 2011
 */
@Repository
public class BuyeraddrDao extends GenericDao<Buyeraddr,String> implements IBuyeraddrDao {

	public BuyeraddrDao() {
		super(Buyeraddr.class);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBuyeraddrDao#deleteBuyeraddr(java.lang.String)
	 */
	public void deleteBuyeraddr(String pk) {
		this.getSqlMapClientTemplate().delete("buyeraddr.delete", pk);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBuyeraddrDao#getBuyeraddrByPk(java.lang.String)
	 */
	public Buyeraddr getBuyeraddrByPk(String pk) {
		return (Buyeraddr) this.getSqlMapClientTemplate().queryForObject(
				"buyeraddr.getByPk", pk);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBuyeraddrDao#getBuyeraddrCount(java.util.Map)
	 */
	public int getBuyeraddrCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"buyeraddr.getCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBuyeraddrDao#getBuyeraddrList(java.util.Map)
	 */
	public List getBuyeraddrList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("buyeraddr.getList",
				map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBuyeraddrDao#insertBuyeraddr(com.rbt.model.Buyeraddr)
	 */
	public void insertBuyeraddr(Buyeraddr buyeraddr) {
		this.getSqlMapClientTemplate().insert("buyeraddr.insert", buyeraddr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IBuyeraddrDao#updateBuyeraddr(com.rbt.model.Buyeraddr)
	 */
	public void updateBuyeraddr(Buyeraddr buyeraddr) {
		this.getSqlMapClientTemplate().update("buyeraddr.update", buyeraddr);
	}

}

