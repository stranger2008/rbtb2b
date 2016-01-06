/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: GroupgoodsService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IGroupgoodsDao;
import com.rbt.model.Groupgoods;
import com.rbt.service.IGroupgoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 团购商品表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Mar 16 09:59:24 CST 2012
 */
@Service
public class GroupgoodsService extends GenericService<Groupgoods,String> implements IGroupgoodsService {
	
	IGroupgoodsDao groupgoodsDao;

	@Autowired
	public GroupgoodsService(IGroupgoodsDao groupgoodsDao) {
		super(groupgoodsDao);
		this.groupgoodsDao = groupgoodsDao;
	}
	
	public void updateShelves(final List list){
		
	}
}

