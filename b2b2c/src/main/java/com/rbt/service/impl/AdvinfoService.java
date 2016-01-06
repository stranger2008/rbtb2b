/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: AdvinfoService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAdvinfoDao;
import com.rbt.model.Advinfo;
import com.rbt.service.IAdvinfoService;

/**
 * @function 功能 广告信息Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Jul 7, 2011 5:40:26 PM
 */
@Service
public class AdvinfoService extends GenericService<Advinfo,String> implements IAdvinfoService {

	IAdvinfoDao advinfoDao;

	@Autowired
	public AdvinfoService(IAdvinfoDao advinfoDao) {
		super(advinfoDao);
		this.advinfoDao = advinfoDao;
	}


	public List getKeywordAdList(Map map) {
		return advinfoDao.getKeywordAdList(map);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IAdvinfoService#getAdvinfoIntr(java.util.Map)
	 */
	public List getAdvinfoIntr(Map map) {
		return this.advinfoDao.getAdvinfoIntr(map);
	}
}
