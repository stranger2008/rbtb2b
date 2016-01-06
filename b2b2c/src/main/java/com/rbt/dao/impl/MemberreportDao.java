/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberreportDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMemberreportDao;
import com.rbt.model.Memberreport;

/**
 * @function 功能  会员举报信息表dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Nov 30 14:19:40 CST 2011
 */
@Repository
public class MemberreportDao extends GenericDao<Memberreport,String> implements IMemberreportDao {
	
	public MemberreportDao() {
		super(Memberreport.class);
	}
	
}

