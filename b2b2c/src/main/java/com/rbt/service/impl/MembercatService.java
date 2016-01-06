/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MembercatService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IJobtalentDao;
import com.rbt.dao.IMembercatDao;
import com.rbt.model.Jobtalent;
import com.rbt.model.Membercat;
import com.rbt.service.IMembercatService;

/**
 * @function 功能 会员自定义分类表Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Jul 25 11:13:52 CST 2011
 */
@Service
public class MembercatService extends GenericService<Membercat,String> implements IMembercatService {

	/*
	 * 会员自定义分类表Dao层接口
	 */
	IMembercatDao membercatDao;
	@Autowired
	public MembercatService(IMembercatDao membercatDao) {
		super(membercatDao);
		this.membercatDao = membercatDao;
	}
	
	public void updateAllMemberCat(List list) {
		this.membercatDao.updateAllMemberCat(list);
	}
	public void updateSortNo(List list) {
		this.membercatDao.updateSortNo(list);
	}
}

