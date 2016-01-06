/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MalllevelsetDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMalllevelsetDao;
import com.rbt.model.Malllevelset;

/**
 * @function 功能  会员等级设置表dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Feb 29 10:28:35 CST 2012
 */
@Repository
public class MalllevelsetDao extends GenericDao<Malllevelset,String> implements IMalllevelsetDao {
	
	public MalllevelsetDao() {
		super(Malllevelset.class);
	}
	
}

