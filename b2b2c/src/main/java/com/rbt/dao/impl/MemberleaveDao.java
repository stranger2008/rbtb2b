/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberleaveDao.java 
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
import com.rbt.dao.IMemberleaveDao;
import com.rbt.model.Memberleave;

/**
 * @function 功能  记录会员留言信息表dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Jul 25 08:40:47 CST 2011
 */
@Repository
public class MemberleaveDao extends GenericDao<Memberleave,String> implements IMemberleaveDao {

	public MemberleaveDao() {
		super(Memberleave.class);
	}
	
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateMemberleaveState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("memberleave.updateisdel", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});

	}

}

