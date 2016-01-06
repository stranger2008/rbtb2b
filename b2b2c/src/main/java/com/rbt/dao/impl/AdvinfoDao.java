/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: AdvinfoDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IAdvinfoDao;
import com.rbt.model.Advinfo;

/**
 * @function 功能 广告信息dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Jul 7, 2011 4:57:11 PM
 */
@Repository
public class AdvinfoDao extends GenericDao<Advinfo,String> implements IAdvinfoDao {

	public AdvinfoDao() {
		super(Advinfo.class);
	}
	
	public List getKeywordAdList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("advinfo.getKeywordAdsList",
				map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IAdvinfoDao#getAdvinfoIntr(java.util.Map)
	 */
	public List getAdvinfoIntr(Map map) {
		return this.getSqlMapClientTemplate().queryForList("advinfo.getinfoList",
				map);
	}

}
