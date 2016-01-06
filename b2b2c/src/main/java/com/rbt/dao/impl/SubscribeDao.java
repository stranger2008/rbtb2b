/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: SubscribeDao.java 
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
import com.rbt.dao.IAboutusDao;
import com.rbt.dao.ISubscribeDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Subscribe;

/**
 * @function 功能  记录会员商机订阅信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 21 08:53:30 CST 2011
 */
@Repository
public class SubscribeDao extends GenericDao<Subscribe,String> implements ISubscribeDao {

	
	public SubscribeDao() {
		super(Subscribe.class);
	}

	/**
	 * 方法描述：批量修改导航是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateSubscribeState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("subscribe.updateenabled", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});

	}

}

