/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: OrderinfoDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.rbt.dao.IOrderinfoDao;
import com.rbt.model.Orderinfo;

/**
 * @function 功能  记录平台支付方式信息dao层业务接口实现类
 * @author 创建人 订单管理
 * @date 创建日期 Tue Oct 25 17:06:38 CST 2011
 */
@Repository
public class OrderinfoDao extends GenericDao<Orderinfo,String> implements IOrderinfoDao {

	
	public OrderinfoDao() {
		super(Orderinfo.class);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IOrderinfoDao#updateOrderinfo(java.util.Map)
	 */
	public void updateOrderState(Map map) {
		this.getSqlMapClientTemplate().update("orderinfo.updateorder_state", map);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IOrderinfoDao#getEndOrderList(java.lang.String)
	 */
	public List getEndOrderList(String endtime) {
		return this.getSqlMapClientTemplate().queryForList("orderinfo.getEndDatePk",
				endtime);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IOrderinfoDao#getOrderinfoList(java.util.String)
	 */
	public List getpayOrderinfoList(String paypk) {
		return this.getSqlMapClientTemplate().queryForList("orderinfo.getpayPk",
				paypk);
	}

}

