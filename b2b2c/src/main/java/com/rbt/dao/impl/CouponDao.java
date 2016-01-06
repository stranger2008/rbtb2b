/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: CouponDao.java 
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
import com.rbt.dao.ICouponDao;
import com.rbt.model.Coupon;

/**
 * @function 功能  记录优惠卷表信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Mar 22 13:38:28 CST 2012
 */
@Repository
public class CouponDao extends GenericDao<Coupon,String> implements ICouponDao {
	
	public CouponDao() {
		super(Coupon.class);
	}
	
	/**
	 * @Method Description :批量修改推荐
	 * @author : 蔡毅存
	 * @date : Nov 28, 2012 9:55:16 AM
	 */
	/**
	 * 方法描述：批量修改推荐
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateCouponState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("coupon.updateisevery", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
}

