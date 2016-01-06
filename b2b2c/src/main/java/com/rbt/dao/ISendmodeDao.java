/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ISendmodeDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Sendmode;

/**
 * @function 功能 配送方式表dao层业务接口
 * @author  创建人胡惜坤
 * @date  创建日期 Fri Mar 23 09:55:49 CST 2012
 */

public interface ISendmodeDao extends IGenericDao<Sendmode,String>{
	/**
	 * 方法描述：批量修改排序
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateSendmode_sort(List lists);
}

