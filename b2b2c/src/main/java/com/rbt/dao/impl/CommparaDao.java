/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: CommparaDao.java 
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
import com.rbt.dao.ICommparaDao;
import com.rbt.model.Commpara;
/**
 * @function 功能 系统参数dao层接口实现
 * @author  创建人 胡惜坤
 * @date  创建日期  July 6, 2011
 */
@Repository
public class CommparaDao extends GenericDao<Commpara,String> implements ICommparaDao {

	public CommparaDao() {
		super(Commpara.class);
	}
	/* (non-Javadoc)
	 * @see com.rbt.dao.ICommparaDao#getWebCommparaList(java.util.Map)
	 */
	public List getWebCommparaList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("commpara.getWebCommparaList", map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ICommparaDao#getCommparaGroupList(java.util.Map)
	 */
	public List getCommparaGroupList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("commpara.getGroupList", map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ICommparaDao#getCommparaIndexList(java.util.Map)
	 */
	public List getCommparaIndexList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("commpara.getCommparaIndexList",map);
	}
	/**
	 * 方法描述：批量修改导航是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateCommparaState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("commpara.updateisshow", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});

	}
	/**
	 * 方法描述：批量导航排序
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateCommpara_sort(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("commpara.updatesort_no", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
}
