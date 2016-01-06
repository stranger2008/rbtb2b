/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: OrganizeService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.IOrganizeDao;
import com.rbt.model.Organize;
import com.rbt.service.IOrganizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录组织部门Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Mon Nov 07 13:37:36 CST 2011
 */
@Service
public class OrganizeService extends GenericService<Organize,String> implements IOrganizeService {

	/*
	 * 记录组织部门Dao层接口
	 */
	IOrganizeDao organizeDao;

	@Autowired
	public OrganizeService(IOrganizeDao organizeDao) {
		super(organizeDao);
		this.organizeDao = organizeDao;
	}


}

