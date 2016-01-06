/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: Ban_IpService.java 
 */

package com.rbt.service.impl;

import com.rbt.service.IBanipService;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.IBanipDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Banip;;
/**
 * @function 功能 禁止IP管理业务层接口实现
 * @author  创建人 胡惜坤
 * @date  创建日期  July 5, 2011
 */
@Service
public class BanipService extends GenericService<Banip,String> implements IBanipService {
	
	/**
	 * 禁止IPdao实现层
	 */
	 IBanipDao ban_ipDao;

	@Autowired
	public BanipService(IBanipDao ban_ipDao) {
		super(ban_ipDao);
		this.ban_ipDao = ban_ipDao;
	}
	
	public void updateAllIp(List list) {
		this.ban_ipDao.updateAllIp(list);
	}
}
