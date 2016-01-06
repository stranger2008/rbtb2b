/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: AttrvalueService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IAttrvalueDao;
import com.rbt.model.Attrvalue;
import com.rbt.service.IAttrvalueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录分类属性的值Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Tue Aug 21 15:25:18 CST 2012
 */
@Service
public class AttrvalueService extends GenericService<Attrvalue,String> implements IAttrvalueService {
	
	IAttrvalueDao attrvalueDao;

	@Autowired
	public AttrvalueService(IAttrvalueDao attrvalueDao) {
		super(attrvalueDao);
		this.attrvalueDao = attrvalueDao;
	}
	public void deleteByattrid(String id) {
		this.attrvalueDao.deleteByattrid(id);
	}
}

