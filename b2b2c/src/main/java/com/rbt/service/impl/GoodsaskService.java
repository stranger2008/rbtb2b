/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: GoodsaskService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IGoodsaskDao;
import com.rbt.model.Goodsask;
import com.rbt.service.IGoodsaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商品咨询信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Mar 23 11:24:44 CST 2012
 */
@Service
public class GoodsaskService extends GenericService<Goodsask,String> implements IGoodsaskService {
	
	IGoodsaskDao goodsaskDao;

	@Autowired
	public GoodsaskService(IGoodsaskDao goodsaskDao) {
		super(goodsaskDao);
		this.goodsaskDao = goodsaskDao;
	}
	
}

