/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: InfoattrDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IInfoattrDao;
import com.rbt.model.Infoattr;

/**
 * @function 功能  信息属性dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Sat Mar 17 10:32:08 CST 2012
 */
@Repository
public class InfoattrDao extends GenericDao<Infoattr,String> implements IInfoattrDao {
	
	public InfoattrDao() {
		super(Infoattr.class);
	}
	
	public List getDetailList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getdetailList",map);
	}
	
}

