/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MembercertService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.IMembercertDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Membercert;
import com.rbt.service.IMembercertService;

/**
 * @function 功能 会员荣誉资质信息Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:11:13 CST 2011
 */
@Service
public class MembercertService extends GenericService<Membercert, String>
		implements IMembercertService {

	/*
	 * 会员荣誉资质信息Dao层接口
	 */
	IMembercertDao membercertDao;

	@Autowired
	public MembercertService(IMembercertDao membercertDao) {
		super(membercertDao);
		this.membercertDao = membercertDao;
	}

	public void auditMembercert(Map map) {
		this.membercertDao.auditMembercert(map);
	}

}
