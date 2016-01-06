/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MembersendboxDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IMembersendboxDao;
import com.rbt.model.Membersendbox;

/**
 * @function 功能  记录发件箱的发送信息dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Sep 28 21:32:25 CST 2011
 */
@Repository
public class MembersendboxDao extends GenericDao<Membersendbox,String> implements IMembersendboxDao {

	public MembersendboxDao() {
		super(Membersendbox.class);
	}
}

