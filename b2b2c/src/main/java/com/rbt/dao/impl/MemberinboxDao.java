/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberinboxDao.java 
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
import com.rbt.dao.IMemberinboxDao;
import com.rbt.model.Memberinbox;

/**
 * @function 功能  会员收件信息表dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Aug 05 14:23:14 CST 2011
 */
@Repository
public class MemberinboxDao extends GenericDao<Memberinbox,String> implements IMemberinboxDao {

	public MemberinboxDao() {
		super(Memberinbox.class);
	}
		
	@SuppressWarnings("unchecked")
	public void updateIsdelete(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("memberinbox.updateIsdelete", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
}

