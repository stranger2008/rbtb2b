/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: GoodsService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IGoodsDao;
import com.rbt.model.Goods;
import com.rbt.service.IGoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 商品表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Mon Feb 27 11:28:48 CST 2012
 */
@Service
public class GoodsService extends GenericService<Goods,String> implements IGoodsService {
	
	IGoodsDao goodsDao;

	@Autowired
	public GoodsService(IGoodsDao goodsDao) {
		super(goodsDao);
		this.goodsDao = goodsDao;
	}

	public List getRelateList(Map map) {
		return this.goodsDao.getRelateList(map);
	}

	public void updateShelves(final List list){
		this.goodsDao.updateShelves(list);
	}
	
	public void updatedownshelves(final List list){
		this.goodsDao.updatedownshelves(list);
	}

	public String insertGetPk(Goods t, List objList) {
		return this.goodsDao.insertGetPk(t,objList);
	}

	public void update(Goods t, List objList, String id) {
		this.goodsDao.update(t, objList, id);
	}
}

