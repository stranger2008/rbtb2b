/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: AboutusDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IAboutusDao;
import com.rbt.model.Aboutus;

/**
 * @function 功能 关于我们dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Jul 11 12:15:32 CST 2011
 */

@Repository
public class AboutusDao extends GenericDao<Aboutus,String> implements IAboutusDao {
	
	public AboutusDao() {
		super(Aboutus.class);
	}
	
}
