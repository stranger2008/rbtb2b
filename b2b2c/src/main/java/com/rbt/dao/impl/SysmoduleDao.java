/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: SysmoduleDao.java 
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
import com.rbt.dao.ISysmoduleDao;
import com.rbt.model.Sysmodule;

/**
 * @function 功能  系统模块表dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Jan 13 12:48:48 CST 2012
 */
@Repository
public class SysmoduleDao extends GenericDao<Sysmodule,String> implements ISysmoduleDao {
	
	public SysmoduleDao() {
		super(Sysmodule.class);
	}

	public void updateSort(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("sysmodule.updateSort", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}	
	
	public void updateisuse(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("sysmodule.updateisuse", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});		
	}
}

