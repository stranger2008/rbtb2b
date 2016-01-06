/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberfriendService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMemberfriendDao;
import com.rbt.model.Memberfriend;
import com.rbt.service.IMemberfriendService;

/**
 * @function 功能 会员商友表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Aug 04 13:41:00 CST 2011
 */
@Service
public class MemberfriendService  extends GenericService<Memberfriend,String> implements IMemberfriendService {

	/*
	 * 会员商友表Dao层接口
	 */

	IMemberfriendDao memberfriendDao;

	@Autowired
	public MemberfriendService(IMemberfriendDao memberfriendDao) {
		super(memberfriendDao);
		this.memberfriendDao = memberfriendDao;
	}
}

