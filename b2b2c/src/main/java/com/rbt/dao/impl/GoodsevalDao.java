/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: GoodsevalDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IGoodsevalDao;
import com.rbt.model.Goodseval;

/**
 * @function 功能  记录商品评价表信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Mar 27 09:32:47 CST 2012
 */
@Repository
public class GoodsevalDao extends GenericDao<Goodseval,String> implements IGoodsevalDao {
	
	public GoodsevalDao() {
		super(Goodseval.class);
	}
	/**
	 * 方法描述：修改评论过期时间
	 * @param pk
	 * @return java.util.module
	 */
	public void updateGoodsevalIstwo(Goodseval goodseval) {
		this.getSqlMapClientTemplate().update("goodseval.updateistwo",goodseval);
	}
	
	public int getevalCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getevalCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

}

