/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IShopconfigDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Shopconfig;

/**
 * @function 功能 商店设置dao层业务接口
 * @author  创建人hxk
 * @date  创建日期 Tue Feb 28 10:24:54 CST 2012
 */

public interface IShopconfigDao extends IGenericDao<Shopconfig,String>{
	
	public Shopconfig GetByCustId(String pk);
	//删除会员店铺信息
	public void delShopMes(String pk);
}

