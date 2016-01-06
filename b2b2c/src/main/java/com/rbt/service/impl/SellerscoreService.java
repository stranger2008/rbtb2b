/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: SellerscoreService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISellerscoreDao;
import com.rbt.model.Sellerscore;
import com.rbt.service.ISellerscoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商家打分信息Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Fri Mar 30 11:03:31 CST 2012
 */
@Service
public class SellerscoreService extends GenericService<Sellerscore,String> implements ISellerscoreService {
	
	ISellerscoreDao sellerscoreDao;

	@Autowired
	public SellerscoreService(ISellerscoreDao sellerscoreDao) {
		super(sellerscoreDao);
		this.sellerscoreDao = sellerscoreDao;
	}
	public int getIndexCount(Map map) {
		return sellerscoreDao.getIndexCount(map);
	}
   
	public List getCountList(Map map) {
		return sellerscoreDao.getCountList(map);
	}

	
}

