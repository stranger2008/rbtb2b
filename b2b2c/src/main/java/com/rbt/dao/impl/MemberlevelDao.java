/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberlevelDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMemberlevelDao;
import com.rbt.model.Memberlevel;

/**
 * @function 功能 会员级别配置dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 13 15:08:05 CST 2011
 */
@Repository
public class MemberlevelDao extends GenericDao<Memberlevel, String> implements
		IMemberlevelDao {

	public MemberlevelDao() {
		super(Memberlevel.class);
	}

}
