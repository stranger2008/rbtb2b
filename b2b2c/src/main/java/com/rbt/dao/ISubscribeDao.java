/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ISubscribeDao.java 
 */
package com.rbt.dao;

import java.util.List;
import com.rbt.model.Subscribe;

/**
 * @function 功能 记录会员商机订阅信息dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Thu Jul 21 08:53:30 CST 2011
 */

public interface ISubscribeDao extends IGenericDao<Subscribe,String>{
	
	
	/**
	 * 方法描述：批量是否可用
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateSubscribeState(List lists);
}

