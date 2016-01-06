/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: SubscribeService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.ISubscribeDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Subscribe;
import com.rbt.service.IAboutusService;
import com.rbt.service.ISubscribeService;

/**
 * @function 功能 记录会员商机订阅信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 21 08:53:30 CST 2011
 */
@Service
public class SubscribeService extends GenericService<Subscribe,String> implements ISubscribeService {
	

	ISubscribeDao subscribeDao;

	@Autowired
	public SubscribeService(ISubscribeDao subscribeDao) {
		super(subscribeDao);
		this.subscribeDao = subscribeDao;
	}

	
	/**
	 * 批量更新Nav是否显示
	 */
	public void updateenabled(List list) {
		this.subscribeDao.updateSubscribeState(list);

	}

}

