/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: SupplyService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.ISupplyDao;
import com.rbt.model.Supply;
import com.rbt.service.ISupplyService;

/**
 * @function 功能 供应表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Jul 21 17:15:43 CST 2011
 */
@Service
public class SupplyService  extends GenericService<Supply,String>implements ISupplyService {

	/*
	 * 供应表Dao层接口
	 */
	ISupplyDao supplyDao;
	@Autowired
	public SupplyService(ISupplyDao supplyDao) {
		super(supplyDao);
		this.supplyDao = supplyDao;
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ISupplyService#getIndexList(java.util.Map)
	 */
	public List getSupplyIndexList(Map map) {
		return this.supplyDao.getSupplyIndexList(map);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.ISupplyService#updateClickNum(java.lang.String)
	 */
	public void updateClickNum(String pk)
	{
	  this.supplyDao.updateClickNum(pk);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ISupplyService#getWebSupplyList(java.util.Map)
	 */
	public List getWebSupplyList(Map map) {
		return this.supplyDao.getWebSupplyList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ISupplyService#getWebSupplyCount(java.util.Map)
	 */
	public int getWebSupplyCount(Map map) {
		return this.supplyDao.getWebSupplyCount(map);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.service.ISupplyService#getCatSupplyList(java.util.Map)
	 */
	public List getCatSupplyList(Map map) {
		return this.supplyDao.getCatSupplyList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ISupplyService#updateSupplyState(java.util.List)
	 */
	public void updateSupplyState(List list) {
		this.supplyDao.updateSupplyState(list);
	}

	public List getWebSupplyAdsList(Map map) {
		return this.supplyDao.getWebSupplyAdsList(map);
	}
	
	public String insertGetPk(Supply t,List objList){
		return this.supplyDao.insertGetPk(t, objList);
	}
	
	public void update(Supply t,List objList,String id){
		this.supplyDao.update(t,objList,id);
	}

}

