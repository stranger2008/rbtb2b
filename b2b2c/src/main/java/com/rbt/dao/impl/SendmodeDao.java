/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: SendmodeDao.java 
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
import com.rbt.dao.ISendmodeDao;
import com.rbt.model.Sendmode;

/**
 * @function 功能  配送方式表dao层业务接口实现类
 * @author 创建人 胡惜坤
 * @date 创建日期 Fri Mar 23 09:55:49 CST 2012
 */
@Repository
public class SendmodeDao extends GenericDao<Sendmode,String> implements ISendmodeDao {
	
	public SendmodeDao() {
		super(Sendmode.class);
	}
	/**
	 * 方法描述：批量导航排序
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateSendmode_sort(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("sendmode.updatesort_no", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
}

