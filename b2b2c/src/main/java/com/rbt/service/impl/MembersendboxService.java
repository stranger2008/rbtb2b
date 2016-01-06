/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MembersendboxService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMembersendboxDao;
import com.rbt.model.Membersendbox;
import com.rbt.service.IMembersendboxService;

/**
 * @function 功能 记录发件箱的发送信息Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Sep 28 21:32:25 CST 2011
 */
@SuppressWarnings("unchecked")
@Service
public class MembersendboxService extends GenericService<Membersendbox,String> implements IMembersendboxService {

	/*
	 * 记录发件箱的发送信息Dao层接口
	 */
	IMembersendboxDao membersendboxDao;

	@Autowired
	public MembersendboxService(IMembersendboxDao membersendboxDao) {
		super(membersendboxDao);
		this.membersendboxDao = membersendboxDao;
	}

}

