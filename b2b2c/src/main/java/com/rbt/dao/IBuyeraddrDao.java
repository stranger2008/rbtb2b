/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IBuyeraddrDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Buy;
import com.rbt.model.Buyeraddr;

/**
 * @function 功能 买家收货地址表dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Fri Oct 21 10:48:38 CST 2011
 */

public interface IBuyeraddrDao extends IGenericDao<Buyeraddr,String> {
	/**
	 * 方法描述：插入买家收货地址表
	 * @param buyeraddr
	 */
	public void insertBuyeraddr(Buyeraddr buyeraddr);

	/**
	 * 方法描述：根据主键删除买家收货地址表
	 * @param pk 主键
	 */
	public void deleteBuyeraddr(String pk);

	/**
	 * 方法描述：修改买家收货地址表
	 * @param buyeraddr
	 */
	public void updateBuyeraddr(Buyeraddr buyeraddr);

	/**
	 * 方法描述：按照map中的条件找出买家收货地址表信息列表
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getBuyeraddrList(Map map);

	/**
	 * 方法描述：按照map中的条件找出买家收货地址表的数量
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int getBuyeraddrCount(Map map);

	/**
	 * 方法描述：根据买家收货地址表主键找出买家收货地址表
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public Buyeraddr getBuyeraddrByPk(String pk);
}

