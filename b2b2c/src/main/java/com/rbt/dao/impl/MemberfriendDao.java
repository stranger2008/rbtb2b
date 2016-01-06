/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberfriendDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IMemberfriendDao;
import com.rbt.model.Memberfriend;

/**
 * @function 功能  会员商友表dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Aug 04 13:41:00 CST 2011
 */
@Repository
public class MemberfriendDao  extends GenericDao<Memberfriend,String>  implements IMemberfriendDao {

	public MemberfriendDao() {
		super(Memberfriend.class);
	}
}

