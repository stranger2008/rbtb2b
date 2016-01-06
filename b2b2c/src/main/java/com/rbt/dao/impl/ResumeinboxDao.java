/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: ResumeinboxDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IResumeinboxDao;
import com.rbt.model.Resumeinbox;

/**
 * @function 功能  简历收件箱表dao层业务接口实现类
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 01 09:17:11 CST 2011
 */
@Repository
public class ResumeinboxDao extends GenericDao<Resumeinbox,String> implements IResumeinboxDao {

	
	public ResumeinboxDao() {
		super(Resumeinbox.class);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.IResumeinboxDao#updateResumeinboxState(java.util.Map)
	 */
	public void updateResumeinboxState(Map map)
	{
		this.getSqlMapClientTemplate().update("resumeinbox.udpatestate", map);
	}
}

