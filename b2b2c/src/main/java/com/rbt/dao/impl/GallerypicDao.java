/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: GallerypicDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IGallerypicDao;
import com.rbt.model.Gallerypic;

/**
 * @function 功能  记录图库图片信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 26 10:31:40 CST 2011
 */
@Repository
public class GallerypicDao extends GenericDao<Gallerypic,String> implements IGallerypicDao {

	public GallerypicDao() {
		super(Gallerypic.class);
	}
}

