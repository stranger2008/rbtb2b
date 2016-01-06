/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: JobtalentDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IJobtalentDao;
import com.rbt.model.Jobtalent;

/**
 * @function 功能  人才库表dao层业务接口实现类
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 01 10:39:25 CST 2011
 */
@Repository
public class JobtalentDao extends GenericDao<Jobtalent,String> implements IJobtalentDao {
	public JobtalentDao() {
		super(Jobtalent.class);
	}
}

