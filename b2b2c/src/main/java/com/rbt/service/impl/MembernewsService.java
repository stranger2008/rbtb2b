/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MembernewsService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IMembernewsDao;
import com.rbt.model.Membernews;
import com.rbt.service.IMembernewsService;

/**
 * @function 功能 企业新闻信息Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:14:34 CST 2011
 */
@Service
public class MembernewsService extends GenericService<Membernews, String>
		implements IMembernewsService {

	/*
	 * 企业新闻信息Dao层接口
	 */
	IMembernewsDao membernewsDao;

	@Autowired
	public MembernewsService(IMembernewsDao membernewsDao) {
		super(membernewsDao);
		this.membernewsDao = membernewsDao;
	}

	public int getWebMembernewsCount(Map map) {
		return this.membernewsDao.getWebMembernewsCount(map);
	}

	public List getWebMembernewsList(Map map) {
		return this.membernewsDao.getWebMembernewsList(map);
	}

	public void auditMembernews(Map map) {
		this.membernewsDao.auditMembernews(map);
	}

}
