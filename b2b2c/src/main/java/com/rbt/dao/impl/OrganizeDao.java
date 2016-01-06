/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: OrganizeDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IOrganizeDao;
import com.rbt.model.Organize;

/**
 * @function 功能  记录组织部门dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Mon Nov 07 13:37:36 CST 2011
 */
@Repository
public class OrganizeDao extends GenericDao<Organize,String> implements IOrganizeDao {

	public OrganizeDao() {
		super(Organize.class);
	}
}

