/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberuserDao.java 
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
import com.rbt.dao.IMemberuserDao;
import com.rbt.model.Memberuser;

/**
 * @function 功能 用户账号dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Jul 19 09:37:16 CST 2011
 */
@Repository
public class MemberuserDao extends GenericDao<Memberuser, String> implements
		IMemberuserDao {

	public MemberuserDao() {
		super(Memberuser.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberuserDao#getMemberuserByPk(java.lang.String)
	 */
	public Memberuser getMemberuserByname(String username) {
		return (Memberuser) this.getSqlMapClientTemplate().queryForObject(
				"memberuser.getByusername", username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberuserDao#insertMemberuser(com.rbt.model.Memberuser)
	 */
	public String insertMemberuser(Memberuser memberuser) {
		return (String) this.getSqlMapClientTemplate().insert(
				"memberuser.insert", memberuser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberuserDao#updatePassword(com.rbt.model.Memberuser)
	 */
	public void updatePassword(Memberuser memberuser) {
		this.getSqlMapClientTemplate().update("memberuser.updatepassword",
				memberuser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IAdvposDao#updateAdvposBatch(java.util.List)
	 */
	public void updatePasswdBatch(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if (list != null && list.size() > 0) {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap) iter.next();
						executor.update("memberuser.updatePasswdBatch", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
	public void deleteByCustId(String cust_id){
		this.getSqlMapClientTemplate().update("memberuser.deleteByCustId",cust_id);
	}

}
