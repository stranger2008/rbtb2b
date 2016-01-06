/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMemberleaveDao.java 
 */
package com.rbt.dao;

import java.util.List;
import com.rbt.model.Memberleave;

/**
 * @function 功能 记录会员留言信息表dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Mon Jul 25 08:40:47 CST 2011
 */

public interface IMemberleaveDao extends IGenericDao<Memberleave,String>{
	
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateMemberleaveState(List lists);
}

