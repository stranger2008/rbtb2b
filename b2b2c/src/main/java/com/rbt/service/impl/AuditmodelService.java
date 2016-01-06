/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: AuditmodelService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IAuditmodelDao;
import com.rbt.model.Auditmodel;
import com.rbt.service.IAuditmodelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 审核模型信息Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 06 15:40:22 CST 2012
 */
@Service
public class AuditmodelService extends GenericService<Auditmodel,String> implements IAuditmodelService {
	
	IAuditmodelDao auditmodelDao;

	@Autowired
	public AuditmodelService(IAuditmodelDao auditmodelDao) {
		super(auditmodelDao);
		this.auditmodelDao = auditmodelDao;
	}
	public List getModelList(Map map){
		return this.auditmodelDao.getModelList(map);
	}
	public int getModelCount(Map map){
		return this.auditmodelDao.getModelCount(map);
	}
	/**
	 * 获取某一个用户需要审核的模块信息
	 * @param map
	 * @return
	 */
	public List getAuditList(Map map){
		return this.auditmodelDao.getAuditList(map);
	}
	
}

