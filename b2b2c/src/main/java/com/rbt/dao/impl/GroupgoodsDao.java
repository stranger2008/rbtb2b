/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: GroupgoodsDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IGroupgoodsDao;
import com.rbt.model.Groupgoods;

/**
 * @function 功能  团购商品表dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Mar 16 09:59:24 CST 2012
 */
@Repository
public class GroupgoodsDao extends GenericDao<Groupgoods,String> implements IGroupgoodsDao {
	
	public GroupgoodsDao() {
		super(Groupgoods.class);
	}
	
}

