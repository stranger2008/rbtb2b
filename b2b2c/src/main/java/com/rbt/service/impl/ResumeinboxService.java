/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: ResumeinboxService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IMembercatDao;
import com.rbt.dao.IResumeinboxDao;
import com.rbt.model.Membercat;
import com.rbt.model.Resumeinbox;
import com.rbt.service.IResumeinboxService;

/**
 * @function 功能 简历收件箱表Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 01 09:17:11 CST 2011
 */
@Service
public class ResumeinboxService extends GenericService<Resumeinbox,String> implements IResumeinboxService {

	/*
	 * 简历收件箱表Dao层接口
	 */
	IResumeinboxDao resumeinboxDao;
	@Autowired
	public ResumeinboxService(IResumeinboxDao resumeinboxDao) {
		super(resumeinboxDao);
		this.resumeinboxDao = resumeinboxDao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IResumeinboxService#updateResumeinboxState(java.util.Map)
	 */
	public void updateResumeinboxState(Map map)
	{
		this.resumeinboxDao.updateResumeinboxState(map);
	}

}

