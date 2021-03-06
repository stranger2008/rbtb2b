/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ICertificationDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Certification;

/**
 * @function 功能 记录企业身份认证信息dao层业务接口
 * @author  创建人林俊钦
 * @date  创建日期 Wed Nov 30 13:34:38 CST 2011
 */

public interface ICertificationDao extends IGenericDao<Certification,String>{
	public void auditState(Certification t);
}

