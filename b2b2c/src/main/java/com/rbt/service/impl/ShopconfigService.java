/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: ShopconfigService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IShopconfigDao;
import com.rbt.model.Shopconfig;
import com.rbt.service.IShopconfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 商店设置Service层业务接口实现
 * @author 创建人 hxk
 * @date 创建日期 Tue Feb 28 10:24:54 CST 2012
 */
@Service
public class ShopconfigService extends GenericService<Shopconfig,String> implements IShopconfigService {
	
	IShopconfigDao shopconfigDao;

	@Autowired
	public ShopconfigService(IShopconfigDao shopconfigDao) {
		super(shopconfigDao);
		this.shopconfigDao = shopconfigDao;
	}
	public Shopconfig GetByCustId(String id) {
		return (Shopconfig)shopconfigDao.GetByCustId(id);
	}

}

