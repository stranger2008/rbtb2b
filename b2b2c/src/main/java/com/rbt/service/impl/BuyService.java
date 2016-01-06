/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: BuyService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.IBuyDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Buy;
import com.rbt.service.IBuyService;

/**
 * @function 功能 求购表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Jul 29 14:52:50 CST 2011
 */
@Service
public class BuyService extends GenericService<Buy,String> implements IBuyService {

	/*
	 * 求购表Dao层接口
	 */
	IBuyDao buyDao;

	@Autowired
	public BuyService(IBuyDao buyDao) {
		super(buyDao);
		this.buyDao = buyDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IBuyService#getWebBuyCount(java.util.Map)
	 */
	public int getWebBuyCount(Map map) {
		return this.buyDao.getWebBuyCount(map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IBuyService#getWebBuyList(java.util.Map)
	 */
	public List getWebBuyList(Map map) {
		return this.buyDao.getWebBuyList(map);
	}

	public void updateClickNum(String pk) {
		this.buyDao.updateClickNum(pk);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.service.ISupplyService#getCatBuyList(java.util.Map)
	 */
	public List getCatBuyList(Map map) {
		return this.buyDao.getCatBuyList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IBuyService#updateBuyState(java.util.List)
	 */
	public void updateBuyState(List list) {
		 this.buyDao.updateBuyState(list);
	}

	public String insertGetPk(Buy t, List objList) {
		return this.buyDao.insertGetPk(t, objList);
	}

	public void update(Buy t, List objList, String id) {
	   this.buyDao.update(t, objList, id);
	}
}
