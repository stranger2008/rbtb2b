/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: RolerightService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IRolerightDao;
import com.rbt.model.Roleright;
import com.rbt.service.IRolerightService;

/**
 * @function 功能 角色权限业务实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Jun 28, 2011 4:03:16 PM
 */
@Service
public class RolerightService extends GenericService<Roleright, String>
		implements IRolerightService {

	IRolerightDao rolerightDao;

	@Autowired
	public RolerightService(IRolerightDao rolerightDao) {
		super(rolerightDao);
		this.rolerightDao = rolerightDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IRolerightService#getRolerightMenuList(java.util.Map)
	 */
	public List getRolerightMenuList(Map map) {
		return this.rolerightDao.getRolerightMenuList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IRolerightService#getRolerightLogsList(java.util.Map)
	 */
	public List getRolerightLogsList(Map map) {
		return rolerightDao.getRolerightLogsList(map);
	}

}
