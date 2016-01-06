/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: TradecommentDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.rbt.dao.ITradecommentDao;
import com.rbt.model.Tradecomment;

/**
 * @function 功能  记录交易评价dao层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Sat Nov 26 13:03:51 CST 2011
 */
@Repository
public class TradecommentDao extends GenericDao<Tradecomment,String> implements ITradecommentDao {

	public TradecommentDao() {
		super(Tradecomment.class);
	}
}

