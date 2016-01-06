/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ISendmodeService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Sendmode;

/**
 * @function 功能 配送方式表Service层业务接口实现类
 * @author  创建人 胡惜坤
 * @date  创建日期 Fri Mar 23 09:55:49 CST 2012
 */

public interface ISendmodeService extends IGenericService<Sendmode,String>{
	/**
	 * 方法描述：批量修改排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updatesort_no(List list);
}

