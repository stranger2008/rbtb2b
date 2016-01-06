/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: CommparaService.java 
 */

package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.ICommparaDao;
import com.rbt.model.Commpara;
import com.rbt.service.ICommparaService;
/**
 * @function 功能  系统参数务实现类
 * @author  创建人 胡惜坤
 * @date  创建日期  July 6, 2011
 */
@Service
public class CommparaService extends GenericService<Commpara,String> implements ICommparaService {
	
	/**
	 * 参数管理dao实现层
	 */
	ICommparaDao commparaDao;
	@Autowired
	public CommparaService(ICommparaDao commparaDao) {
		super(commparaDao);
		this.commparaDao = commparaDao;
	}
	/* (non-Javadoc)
	 * @see com.rbt.service.ICommparaService#getCommparaGroupList(java.util.Map)
	 */
	public List getCommparaGroupList(Map map) {
		return this.commparaDao.getCommparaGroupList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ICommparaService#getCommparaIndexList(java.util.Map)
	 */
	public List getCommparaIndexList(Map map) {
		return this.commparaDao.getCommparaIndexList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ICommparaService#getWebCommparaList(java.util.Map)
	 */
	public List getWebCommparaList(Map map) {
		return this.commparaDao.getWebCommparaList(map);
	}
	
	/**
	 * 批量更新Nav是否显示
	 */
	public void updateenabled(List list) {
		this.commparaDao.updateCommparaState(list);

	}
	/**
	 * 批量更新sort_no排序字段
	 */
	public void updatesort_no(List list) {
		this.commparaDao.updateCommpara_sort(list);
	}



}
