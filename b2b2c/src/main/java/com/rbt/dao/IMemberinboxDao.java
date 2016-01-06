/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMemberinboxDao.java 
 */
package com.rbt.dao;

import java.util.List;
import com.rbt.model.Memberinbox;

/**
 * @function 功能 会员收件信息表dao层业务接口
 * @author  创建人林俊钦
 * @date  创建日期 Fri Aug 05 14:23:14 CST 2011
 */

public interface IMemberinboxDao extends IGenericDao<Memberinbox,String>{
	
	/**
	 * @MethodDescribe 方法描述    用于对信件批量删除到回收站
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 5, 2011 5:06:16 PM
	 */
	@SuppressWarnings("unchecked")
	public void updateIsdelete(List list);
}

