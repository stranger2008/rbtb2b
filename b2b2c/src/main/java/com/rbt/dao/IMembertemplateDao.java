/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMembertemplateDao.java 
 */
package com.rbt.dao;

import java.util.List;

import com.rbt.model.Membertemplate;

/**
 * @function 功能 记录企业站模板信息dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Thu Aug 25 14:37:44 CST 2011
 */

public interface IMembertemplateDao extends IGenericDao<Membertemplate,String>{
	
	/**
	 * 方法描述：批量修改模板排序
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updatetemp_sort(List lists);
}

