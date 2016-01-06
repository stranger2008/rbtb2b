/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MembertrashDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IMembertrashDao;
import com.rbt.model.Membertrash;

/**
 * @function 功能  记录回收站中信息dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Sep 28 21:36:10 CST 2011
 */
@Repository
public class MembertrashDao extends GenericDao<Membertrash,String> implements IMembertrashDao {

	public MembertrashDao() {
		super(Membertrash.class);
	}
}

