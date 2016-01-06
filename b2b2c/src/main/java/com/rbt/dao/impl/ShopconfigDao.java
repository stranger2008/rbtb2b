/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: ShopconfigDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IShopconfigDao;
import com.rbt.model.Shopconfig;

/**
 * @function 功能  商店设置dao层业务接口实现类
 * @author 创建人 hxk
 * @date 创建日期 Tue Feb 28 10:24:54 CST 2012
 */
@Repository
public class ShopconfigDao extends GenericDao<Shopconfig,String> implements IShopconfigDao {
	
	public ShopconfigDao() {
		super(Shopconfig.class);
	}
	public Shopconfig GetByCustId(String pk) {
		return (Shopconfig)this.getSqlMapClientTemplate().queryForObject("shopconfig.getByCustIdPk", pk);
	}
	//删除会员店铺信息
	public void delShopMes(String pk) {
		  this.getSqlMapClientTemplate().delete("shopconfig.delShopconfig", pk);
	}
}

