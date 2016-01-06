/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: JobtalentService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.IJobtalentDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Jobtalent;
import com.rbt.service.IJobtalentService;

/**
 * @function 功能 人才库表Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 01 10:39:25 CST 2011
 */
@Service
public class JobtalentService extends GenericService<Jobtalent,String> implements IJobtalentService {

	/*
	 * 人才库表Dao层接口
	 */
	IJobtalentDao jobtalentDao;

	@Autowired
	public JobtalentService(IJobtalentDao jobtalentDao) {
		super(jobtalentDao);
		this.jobtalentDao = jobtalentDao;
	}

}

