/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MembercatDao.java 
 */
package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IMembercatDao;
import com.rbt.model.Membercat;

/**
 * @function 功能  会员自定义分类表dao层业务接口实现类
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Jul 25 11:13:52 CST 2011
 */
@Repository
public class MembercatDao extends GenericDao<Membercat,String> implements IMembercatDao {
	public MembercatDao() {
		super(Membercat.class);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.dao.IAreaDao#updateAreaSortNo(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void updateSortNo(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("membercat.updatesort_no", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	/* (non-Javadoc)
	 * @see com.rbt.dao.IAreaDao#updateAreaSortNo(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void updateAllMemberCat(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("membercat.updateall", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

}

