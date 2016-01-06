/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: AreasetService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IAreasetDao;
import com.rbt.model.Areaset;
import com.rbt.service.IAreasetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录区域设置信息Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Wed Mar 28 13:22:27 CST 2012
 */
@Service
public class AreasetService extends GenericService<Areaset,String> implements IAreasetService {
	
	IAreasetDao areasetDao;

	@Autowired
	public AreasetService(IAreasetDao areasetDao) {
		super(areasetDao);
		this.areasetDao = areasetDao;
	}
	public void deleteByShopid(String id){
		this.areasetDao.deleteByShopid(id);
	}
}

