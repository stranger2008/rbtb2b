/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MalllevelsetService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IMalllevelsetDao;
import com.rbt.model.Malllevelset;
import com.rbt.service.IMalllevelsetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 会员等级设置表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Feb 29 10:28:35 CST 2012
 */
@Service
public class MalllevelsetService extends GenericService<Malllevelset,String> implements IMalllevelsetService {
	
	IMalllevelsetDao malllevelsetDao;

	@Autowired
	public MalllevelsetService(IMalllevelsetDao malllevelsetDao) {
		super(malllevelsetDao);
		this.malllevelsetDao = malllevelsetDao;
	}
	
}

