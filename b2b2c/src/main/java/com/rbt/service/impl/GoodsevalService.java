/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: GoodsevalService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IGoodsevalDao;
import com.rbt.dao.ISellerscoreDao;
import com.rbt.model.Goodseval;
import com.rbt.model.Sellerscore;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsorderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商品评价表信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Mar 27 09:32:47 CST 2012
 */
@Service
public class GoodsevalService extends GenericService<Goodseval,String> implements IGoodsevalService {
	
	@Autowired
	private IGoodsorderService goodsorderService;
	@Autowired
	private ISellerscoreDao sellerscoreDao;
	
	IGoodsevalDao goodsevalDao;
	@Autowired
	public GoodsevalService(IGoodsevalDao goodsevalDao) {
		super(goodsevalDao);
		this.goodsevalDao = goodsevalDao;
	}
	

	public int getevalCount(Map map){
	  return this.goodsevalDao.getevalCount(map);
	}

	/**
	 * 方法描述：修改评论过期时间
	 * @param pk
	 * @return java.util.module
	 */
	public void updateGoodsevalIstwo(Goodseval goodseval){
		goodsevalDao.updateGoodsevalIstwo(goodseval);
	}


	public void insertGoodsevalrecord(List list,Sellerscore sellerscore,String order_id) {
		//插入商品评价表
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Goodseval goodseval=(Goodseval)list.get(i);
				this.goodsevalDao.insert(goodseval);
			}
		}
		//插入店铺动态评分表
		this.sellerscoreDao.insert(sellerscore);
		//更新确认收货成功，交易成功操作
		Map map=new HashMap();
		map.put("order_state","6");
		map.put("order_id", order_id);
		this.goodsorderService.update(map);
	}
}

