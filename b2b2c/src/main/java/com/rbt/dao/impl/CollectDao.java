/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: CollectDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.ICollectDao;
import com.rbt.model.Collect;

/**
 * @function 功能  记录会员商机收藏信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 20 14:27:09 CST 2011
 */
@Repository
public class CollectDao extends GenericDao<Collect,String> implements ICollectDao {

	public CollectDao() {
		super(Collect.class);
	}
}

