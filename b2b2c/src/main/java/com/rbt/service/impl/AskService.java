/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: AskService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAskDao;
import com.rbt.model.Ask;
import com.rbt.service.IAskService;

/**
 * @function 功能 问题Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:25:38 CST 2011
 */
@Service
public class AskService extends GenericService<Ask,String> implements IAskService {

	IAskDao askDao;

	@Autowired
	public AskService(IAskDao askDao) {
		super(askDao);
		this.askDao = askDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IAskService#updateAskState(java.util.Map)
	 */
	public void updateAskState(Map map) {
		this.askDao.updateAskState(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IAskService#getWebAskCount(java.util.Map)
	 */
	public int getWebAskCount(Map map) {
		return this.askDao.getWebAskCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IAskService#getWebAskList(java.util.Map)
	 */
	public List getWebAskList(Map map) {
		return this.askDao.getWebAskList(map);
	}

	public void updateClickNum(String pk) {
		this.askDao.updateClickNum(pk);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IAskService#updateIsrecom(java.util.List)
	 */
	public void updateIsrecom(List list) {
		this.askDao.updateIsrecom(list);
	}

	public String insertGetPk(Ask t, List objList) {
		return this.askDao.insertGetPk(t, objList);
	}

	public void update(Ask t, List objList, String id) {
		this.askDao.update(t, objList, id);
	}
}
