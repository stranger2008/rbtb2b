/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: FilterwordDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IFilterwordDao;
import com.rbt.model.Filterword;

/**
 * @function 功能 敏感字dao层接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Jul 5, 2011 9:24:56 AM
 */
@Repository
public class FilterwordDao extends GenericDao<Filterword,String> implements IFilterwordDao {
	
	public FilterwordDao() {
		super(Filterword.class);
	}
}
