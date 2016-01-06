/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: SubscribeinfoService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.ISubscribeinfoDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Subscribeinfo;
import com.rbt.service.IAboutusService;
import com.rbt.service.ISubscribeinfoService;

/**
 * @function 功能 记录会员商机订阅信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 21 14:04:42 CST 2011
 */
@Service
public class SubscribeinfoService extends GenericService<Subscribeinfo,String> implements ISubscribeinfoService {

	
	ISubscribeinfoDao subscribeinfoDao;

	@Autowired
	public SubscribeinfoService(ISubscribeinfoDao subscribeinfoDao) {
		super(subscribeinfoDao);
		this.subscribeinfoDao = subscribeinfoDao;
	}
}

