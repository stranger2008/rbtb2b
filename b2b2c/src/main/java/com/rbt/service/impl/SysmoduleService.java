/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: SysmoduleService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISysmoduleDao;
import com.rbt.model.Sysmodule;
import com.rbt.service.ISysmoduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 系统模块表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Jan 13 12:48:48 CST 2012
 */
@Service
public class SysmoduleService extends GenericService<Sysmodule,String> implements ISysmoduleService {
	
	ISysmoduleDao sysmoduleDao;

	@Autowired
	public SysmoduleService(ISysmoduleDao sysmoduleDao) {
		super(sysmoduleDao);
		this.sysmoduleDao = sysmoduleDao;
	}

	public void updateSort(List list) {
		this.sysmoduleDao.updateSort(list);		
	}

	public void updateisuse(List list) {
		this.sysmoduleDao.updateisuse(list);		
	}
}

