/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: SysmenuDao.java 
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
import com.rbt.dao.ISysmenuDao;
import com.rbt.model.Sysmenu;

/**
 * @function 功能 系统菜单dao层接口实现
 * @author 创建人 李良林
 * @date 创建日期 Jun 25, 2011
 */
@Repository
public class SysmenuDao extends GenericDao<Sysmenu, String> implements ISysmenuDao {
	public SysmenuDao() {
		super(Sysmenu.class);
	}

	public void updateEnable(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("sysmenu.updateEnable", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});		
	}	
}
