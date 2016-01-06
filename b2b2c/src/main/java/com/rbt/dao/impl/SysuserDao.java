/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: SysuserDao.java 
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
import com.rbt.dao.ISysuserDao;
import com.rbt.model.Sysuser;

/**
 * @function 功能 系统管理员dao层接口实现
 * @author  创建人 李良林
 * @date  创建日期  Jun 8, 2011
 */
@Repository
public class SysuserDao extends GenericDao<Sysuser, String> implements ISysuserDao {
	
	public SysuserDao() {
		super(Sysuser.class);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ISysuserDao#updateSysuserState(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void updateSysuserState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("sysuser.updateState", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ISysuserDao#getMsgCount(java.util.Map)
	 */
	public List getMsgCount(Map map) {
		return this.getSqlMapClientTemplate().queryForList("sysuser.getMsgList", map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ISysuserDao#updatelaststate(com.rbt.model.Sysuser)
	 */
	public void updatelaststate(Sysuser sysuser) {
		this.getSqlMapClientTemplate().update("sysuser.updatelaststate", sysuser);		
	}

}
