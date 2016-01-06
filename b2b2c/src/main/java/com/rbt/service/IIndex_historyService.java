/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IIndex_historyService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Index_history;

/**
 * @function 功能 记录已经索引过的信息Service层业务接口实现类
 * @author  创建人 林俊钦
 * @date  创建日期 Fri Aug 12 10:12:10 CST 2011
 */

public interface IIndex_historyService extends IGenericService<Index_history,String>{
	
	/**
	 * @MethodDescribe 方法描述    用户的批量添加
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 12, 2011 1:47:47 PM
	 */
	public void insertIndex(final List list);
	
	/**
	 * @Method Description :用户批量删除
	 * @author : 林俊钦
	 * @date : Nov 28, 2011 12:52:25 PM
	 */
	public void deleteIndex_historyList(final List list);	
}

