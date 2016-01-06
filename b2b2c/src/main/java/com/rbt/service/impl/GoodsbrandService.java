/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: GoodsbrandService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IGoodsbrandDao;
import com.rbt.model.Goodsbrand;
import com.rbt.service.IGoodsbrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 商品品牌表Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Feb 27 12:42:32 CST 2012
 */
@Service
public class GoodsbrandService extends GenericService<Goodsbrand,String> implements IGoodsbrandService {
	
	IGoodsbrandDao goodsbrandDao;

	@Autowired
	public GoodsbrandService(IGoodsbrandDao goodsbrandDao) {
		super(goodsbrandDao);
		this.goodsbrandDao = goodsbrandDao;
	}
	/**
	 * 批量更新sort_no排序字段
	 */
	public void updatesort_no(List list) {
		this.goodsbrandDao.updateGoodsbrand_sort(list);
	}
}

