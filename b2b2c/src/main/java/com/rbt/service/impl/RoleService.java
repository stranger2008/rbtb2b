/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.Impl
 * FileName: RoleService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.IRoleDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Role;
import com.rbt.service.IRoleService;

/**
 * @function 功能 添加角色业务层类
 * @author 创建人 林俊钦
 * @date 创建日期 Jun 28, 2011 3:25:23 PM
 */
@Service
public class RoleService extends GenericService<Role,String> implements IRoleService {
	/*
	 * 角色管理实现层接口
	 */
	IRoleDao roleDao;
	@Autowired
	public RoleService(IRoleDao roleDao) {
		super(roleDao);
		this.roleDao = roleDao;
	}
	
}
