/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: SubscribeinfoDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.ISubscribeinfoDao;
import com.rbt.model.Subscribeinfo;

/**
 * @function 功能  记录会员商机订阅信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 21 14:04:42 CST 2011
 */
@Repository
public class SubscribeinfoDao extends GenericDao<Subscribeinfo,String> implements ISubscribeinfoDao {

	
	public SubscribeinfoDao() {
		super(Subscribeinfo.class);
	}
}

