/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: ComplaintDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IComplaintDao;
import com.rbt.model.Complaint;

/**
 * @function 功能  会员投诉信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Dec 02 11:20:21 CST 2011
 */
@Repository
public class ComplaintDao extends GenericDao<Complaint,String> implements IComplaintDao {
	
	public ComplaintDao() {
		super(Complaint.class);
	}
	
}

