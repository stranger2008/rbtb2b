/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IMemberleaveService.java 
 */
package com.rbt.service;

import java.util.List;
import com.rbt.model.Memberleave;

/**
 * @function 功能 记录会员留言信息表Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Mon Jul 25 08:40:47 CST 2011
 */

public interface IMemberleaveService extends IGenericService<Memberleave,String>{
	
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateisdel(List list);
}

