/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: Vote_logDao.java 
 */
package com.rbt.dao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.rbt.dao.IVote_logDao;
import com.rbt.model.Vote_log;

/**
 * @function 功能  记录日志信息dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Jul 27 09:07:53 CST 2011
 */

@Repository
public class Vote_logDao extends GenericDao<Vote_log,String> implements IVote_logDao {
	
	public Vote_logDao() {
		super(Vote_log.class);
	}
	
	/**
	 * 方法描述：显示在线调查选项
	 * @param Nav
	 */
	public List Viewoption(Map map) {
		return this.getSqlMapClientTemplate().queryForList("vote_log.getoptionList",map);

	}

}
