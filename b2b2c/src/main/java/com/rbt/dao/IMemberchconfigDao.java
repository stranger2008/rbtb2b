/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IMemberchconfigDao.java 
 */
package com.rbt.dao;

import java.util.List;
import com.rbt.model.Memberchconfig;

/**
 * @function 功能 记录会员企业站栏目配置信息dao层业务接口
 * @author  创建人蔡毅存
 * @date  创建日期 Fri Aug 26 13:24:50 CST 2011
 */

public interface IMemberchconfigDao extends IGenericDao<Memberchconfig,String>{
	
	
	/**
	 * 方法描述：批量修改是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateState(List lists);
	
	/**
	 * 方法描述：批量修改排序
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void update_sort(List lists);
}

