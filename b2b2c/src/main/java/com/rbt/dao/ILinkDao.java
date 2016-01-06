/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: ILinkDao.java 
 */

package com.rbt.dao;

import java.util.List;
import com.rbt.model.Link;

/**
 * @function 功能 友情链接dao层接口
 * @author  创建人 蔡毅存
 * @date  创建日期  Jun 28, 2011
 */
public interface ILinkDao extends IGenericDao<Link,String>{

	/**
	 * 方法描述：批量修改友情链接状态
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateLinkState(List lists);
}
