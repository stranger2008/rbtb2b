/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MembertrashService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMembertrashDao;
import com.rbt.model.Membertrash;
import com.rbt.service.IMembertrashService;

/**
 * @function 功能 记录回收站中信息Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Sep 28 21:36:10 CST 2011
 */
@Service
public class MembertrashService extends GenericService<Membertrash,String> implements IMembertrashService {

	/*
	 * 记录回收站中信息Dao层接口
	 */
	IMembertrashDao membertrashDao;

	@Autowired
	public MembertrashService(IMembertrashDao membertrashDao) {
		super(membertrashDao);
		this.membertrashDao = membertrashDao;
	}


}

