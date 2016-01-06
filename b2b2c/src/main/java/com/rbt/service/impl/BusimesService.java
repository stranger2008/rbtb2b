/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: BusimesService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IBusimesDao;
import com.rbt.model.Busimes;
import com.rbt.service.IBusimesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商家留言信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Mar 30 12:29:33 CST 2012
 */
@Service
public class BusimesService extends GenericService<Busimes,String> implements IBusimesService {
	
	IBusimesDao busimesDao;

	@Autowired
	public BusimesService(IBusimesDao busimesDao) {
		super(busimesDao);
		this.busimesDao = busimesDao;
	}
	
}

