/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: LevelinfoDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ILevelinfoDao;
import com.rbt.model.Levelinfo;

/**
 * @function 功能 会员级别信息dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Jul 19 14:31:17 CST 2011
 */
@Repository
public class LevelinfoDao extends GenericDao<Levelinfo, String> implements
		ILevelinfoDao {

	public LevelinfoDao() {
		super(Levelinfo.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.ILevelinfoDao#getLevelinfoByTime(java.lang.String)
	 */
	public List getLevelinfoByTime(String pk) {
		return this.getSqlMapClientTemplate().queryForList(
				"levelinfo.getByTime", pk);
	}

}
