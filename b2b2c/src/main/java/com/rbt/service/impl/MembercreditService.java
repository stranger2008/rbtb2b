/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MembercreditService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IMembercreditDao;
import com.rbt.model.Membercredit;
import com.rbt.service.IMembercreditService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录会员信用指数Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Nov 30 13:37:20 CST 2011
 */
@Service
public class MembercreditService extends GenericService<Membercredit,String> implements IMembercreditService {
	
	IMembercreditDao membercreditDao;

	@Autowired
	public MembercreditService(IMembercreditDao membercreditDao) {
		super(membercreditDao);
		this.membercreditDao = membercreditDao;
	}
	
}

