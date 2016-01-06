/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: IndexrecordDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IIndexrecordDao;
import com.rbt.model.Indexrecord;

/**
 * @function 功能  记录更新的索引记录dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Jul 18 15:42:50 CST 2012
 */
@Repository
public class IndexrecordDao extends GenericDao<Indexrecord,String> implements IIndexrecordDao {
	
	public IndexrecordDao() {
		super(Indexrecord.class);
	}
	
}

