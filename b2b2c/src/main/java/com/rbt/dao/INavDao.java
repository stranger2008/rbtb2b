/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: INavDao.java 
 */

package com.rbt.dao;

import java.util.List;
import com.rbt.model.Nav;

/**
 * @function 功能 导航链接dao层接口
 * @author  创建人 蔡毅存
 * @date  创建日期  July 5, 2011
 */
public interface INavDao extends IGenericDao<Nav,String>{
	
	/**
	 * 方法描述：批量修改导航是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateNavState(List lists);
	/**
	 * 方法描述：批量修改导航排序
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updatenav_sort(List lists);
}
