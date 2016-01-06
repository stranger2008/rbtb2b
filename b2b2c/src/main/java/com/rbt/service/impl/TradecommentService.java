/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: TradecommentService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.ITradecommentDao;
import com.rbt.model.Tradecomment;
import com.rbt.service.ITradecommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录交易评价Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Sat Nov 26 13:03:51 CST 2011
 */
@Service
public class TradecommentService extends GenericService<Tradecomment,String> implements ITradecommentService {
	
	/*
	 * 记录交易评价Dao层接口
	 */
	ITradecommentDao tradecommentDao;

	@Autowired
	public TradecommentService(ITradecommentDao tradecommentDao) {
		super(tradecommentDao);
		this.tradecommentDao = tradecommentDao;
	}
}

