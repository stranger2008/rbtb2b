/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMemberinboxService.java 
 */
package com.rbt.service;

import java.util.List;
import com.rbt.model.Memberinbox;

/**
 * @function 功能 会员收件信息表Service层业务接口实现类
 * @author  创建人 林俊钦
 * @date  创建日期 Fri Aug 05 14:23:14 CST 2011
 */

public interface IMemberinboxService  extends IGenericService<Memberinbox,String>{
		
	/**
	 * @MethodDescribe 方法描述    用于对信件批量删除到回收站
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 5, 2011 5:06:16 PM
	 */
	@SuppressWarnings("unchecked")
	public void updateIsdelete(List list);
}

