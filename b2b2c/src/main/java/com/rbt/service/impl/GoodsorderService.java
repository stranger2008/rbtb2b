/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: GoodsorderService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IGoodsorderDao;
import com.rbt.model.Goodsorder;
import com.rbt.service.IGoodsorderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 订单商品表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Mon Mar 19 15:53:24 CST 2012
 */
@Service
public class GoodsorderService extends GenericService<Goodsorder,String> implements IGoodsorderService {
	
	IGoodsorderDao goodsorderDao;

	@Autowired
	public GoodsorderService(IGoodsorderDao goodsorderDao) {
		super(goodsorderDao);
		this.goodsorderDao = goodsorderDao;
	}
	public int getCountBuyOut(Map map) {
		return goodsorderDao.getCountBuyOut(map);
	}

	public List getListBuyOut(Map map) {
		return goodsorderDao.getListBuyOut(map);
	}
	
	public void update(Map<String,String> map){
		this.goodsorderDao.update(map);
	}
	
}

