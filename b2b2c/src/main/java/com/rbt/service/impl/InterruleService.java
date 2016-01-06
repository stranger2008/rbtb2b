/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: InterruleService.java 
 */
package com.rbt.service.impl;

import java.util.List;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.IInterruleDao;
import com.rbt.model.Interrule;
import com.rbt.service.IInterruleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 积分规则表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Nov 10 14:26:30 CST 2011
 */
@Service
public class InterruleService  extends GenericService<Interrule,String> implements IInterruleService {

	/*
	 * 积分规则表Dao层接口
	 */
	IInterruleDao interruleDao;

	@Autowired
	public InterruleService(IInterruleDao interruleDao) {
		super(interruleDao);
		this.interruleDao = interruleDao;
	}

    /**
	 * @Method Description :批量更新规则
	 * @author : 林俊钦
	 * @date : Nov 28, 2011 12:48:47 PM
	 */
	public void updateInterruleList(List list) {
		this.interruleDao.updateInterruleList(list);
	}
}

