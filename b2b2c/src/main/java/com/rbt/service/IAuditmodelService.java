/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IAuditmodelService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Auditmodel;

/**
 * @function 功能 审核模型信息Service层业务接口实现类
 * @author  创建人 胡惜坤
 * @date  创建日期 Mon Aug 06 15:40:22 CST 2012
 */

public interface IAuditmodelService extends IGenericService<Auditmodel,String>{
	public List getModelList(Map map);
	public int getModelCount(Map map);
	/**
	 * 获取某一个用户需要审核的模块信息
	 * @param map
	 * @return
	 */
	public List getAuditList(Map map);
}

