/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: BuyeraddrService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IBuyeraddrDao;
import com.rbt.model.Buyeraddr;
import com.rbt.service.IBuyeraddrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 买家收货地址表Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Oct 21 10:48:38 CST 2011
 */
@Service
public class BuyeraddrService implements IBuyeraddrService {

	/*
	 * 买家收货地址表Dao层接口
	 */
	@Autowired
	private IBuyeraddrDao buyeraddrDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBuyeraddrService#deleteBuyeraddr(java.lang.String)
	 */
	public void deleteBuyeraddr(String pk) {
		this.buyeraddrDao.deleteBuyeraddr(pk);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBuyeraddrService#getBuyeraddrByPk(java.lang.String)
	 */
	public Buyeraddr getBuyeraddrByPk(String pk) {
		return this.buyeraddrDao.getBuyeraddrByPk(pk);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBuyeraddrService#getBuyeraddrCount(java.util.Map)
	 */
	public int getBuyeraddrCount(Map map) {
		return this.buyeraddrDao.getBuyeraddrCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBuyeraddrService#getBuyeraddrList(java.util.Map)
	 */
	public List getBuyeraddrList(Map map) {
		return this.buyeraddrDao.getBuyeraddrList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBuyeraddrService#insertBuyeraddr(com.rbt.model.Buyeraddr)
	 */
	public void insertBuyeraddr(Buyeraddr buyeraddr) {
		this.buyeraddrDao.insertBuyeraddr(buyeraddr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBuyeraddrService#updateBuyeraddr(com.rbt.model.Buyeraddr)
	 */
	public void updateBuyeraddr(Buyeraddr buyeraddr) {
		this.buyeraddrDao.updateBuyeraddr(buyeraddr);
	}

}

