/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: InfoattrService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IInfoattrDao;
import com.rbt.model.Infoattr;
import com.rbt.service.IInfoattrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 信息属性Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Sat Mar 17 10:32:08 CST 2012
 */
@Service
public class InfoattrService extends GenericService<Infoattr,String> implements IInfoattrService {
	
	IInfoattrDao infoattrDao;

	@Autowired
	public InfoattrService(IInfoattrDao infoattrDao) {
		super(infoattrDao);
		this.infoattrDao = infoattrDao;
	}
	
	
	public List getDetailList(Map map){
	   return this.infoattrDao.getDetailList(map);
	}
	
	
}

