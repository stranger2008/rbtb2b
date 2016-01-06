/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IGoodsevalDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodseval;

/**
 * @function 功能 记录商品评价表信息dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Tue Mar 27 09:32:47 CST 2012
 */

public interface IGoodsevalDao extends IGenericDao<Goodseval,String>{

	public int getevalCount(Map map);
	/**
	 * 方法描述：修改评论过期时间
	 * @param pk
	 * @return java.util.module
	 */
	public void updateGoodsevalIstwo(Goodseval goodseval);
}

