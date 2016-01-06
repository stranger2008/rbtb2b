/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IGoodsevalService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodseval;
import com.rbt.model.Sellerscore;

/**
 * @function 功能 记录商品评价表信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Tue Mar 27 09:32:47 CST 2012
 */

public interface IGoodsevalService extends IGenericService<Goodseval,String>{

	public int getevalCount(Map map);

	/**
	 * 方法描述：修改评论过期时间
	 * @param pk
	 * @return java.util.module
	 */
	public void updateGoodsevalIstwo(Goodseval goodseval);
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 25, 2012 9:40:33 PM
	 * @Method Description :批量添加评价
	 */
	public void insertGoodsevalrecord(List list, Sellerscore sellerscore, String order_id);

}

