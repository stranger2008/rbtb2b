/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MemberchconfigDao.java 
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
import com.rbt.dao.IMemberchconfigDao;
import com.rbt.model.Memberchconfig;

/**
 * @function 功能  记录会员企业站栏目配置信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Aug 26 13:24:50 CST 2011
 */
@Repository
public class MemberchconfigDao extends GenericDao<Memberchconfig,String> implements IMemberchconfigDao {

	
	public MemberchconfigDao() {
		super(Memberchconfig.class);
	}
	
	/**
	 * 方法描述：批量修改是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("memberchconfig.updateisdis", temp);
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
	public void update_sort(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("memberchconfig.updatesort_no", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}


}

