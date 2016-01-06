/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IGoodsService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goods;

/**
 * @function 功能 商品表Service层业务接口实现类
 * @author  创建人 林俊钦
 * @date  创建日期 Mon Feb 27 11:28:48 CST 2012
 */

public interface IGoodsService extends IGenericService<Goods,String>{
	public List getRelateList(Map map) ;	
	
	public void updateShelves(final List list);
	
	public void updatedownshelves(final List list);
	
    public String insertGetPk(Goods t, List objList);
    
    public void update(Goods t, List objList, String id);
	
}

