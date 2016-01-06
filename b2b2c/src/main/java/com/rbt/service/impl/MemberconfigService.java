/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberconfigService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMemberconfigDao;
import com.rbt.model.Memberconfig;
import com.rbt.service.IMemberconfigService;

/**
 * @function 功能 记录会员企业站设置信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Aug 29 16:12:02 CST 2011
 */
@Service
public class MemberconfigService extends GenericService<Memberconfig,String> implements IMemberconfigService {

	
	IMemberconfigDao memberconfigDao;

	@Autowired
	public MemberconfigService(IMemberconfigDao memberconfigDao) {
		super(memberconfigDao);
		this.memberconfigDao = memberconfigDao;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberconfigService#updateMemberconfig(com.rbt.model.Memberconfig)
	 */
	public void updatelognum(String cust_id) {
		this.memberconfigDao.updateLognum(cust_id);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberconfigService#updateMemberconfig(com.rbt.model.Memberconfig)
	 */
	public void updatetempcode(Memberconfig memberconfig) {
		this.memberconfigDao.updatetempcode(memberconfig);
	}

}

