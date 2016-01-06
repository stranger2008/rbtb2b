/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: SysmenuService.java 
 */

package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IRoleDao;
import com.rbt.dao.ISysmenuDao;
import com.rbt.model.Role;
import com.rbt.model.Sysmenu;
import com.rbt.service.ISysmenuService;

/**
 * @function 功能 系统菜单业务层接口实现
 * @author  创建人 李良林
 * @date  创建日期  Jun 25, 2011
 */
@Service
public class SysmenuService extends GenericService<Sysmenu,String> implements ISysmenuService {
	
	ISysmenuDao sysmenuDao;
	@Autowired
	public SysmenuService(ISysmenuDao sysmenuDao) {
		super(sysmenuDao);
		this.sysmenuDao = sysmenuDao;
	}
	public void updateEnable(List list) {
		this.sysmenuDao.updateEnable(list);
	}
}
