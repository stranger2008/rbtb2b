/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: IBackupService.java 
 */

package com.rbt.service;

import java.util.List;
import java.util.Map;
import com.rbt.model.Backup;

/**
 * @function 功能  数据库备份业务层接口层
 * @author 创建人 林俊钦
 * @date 创建日期 Jun 28, 2011 3:25:23 PM
 */
public interface IBackupService extends IGenericService<Backup,String>{
	/**
	 * @MethodDescribe 方法描述 获取数据库中所有的表的名称
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 7, 2011 4:14:46 PM
	 */
	@SuppressWarnings("unchecked")
	public List getTableList(Map map);

	/**
	 * @MethodDescribe 方法描述 获取数据库中有多少表
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 7, 2011 4:46:22 PM
	 */
	@SuppressWarnings("unchecked")
	public int getTableCount(Map map);

	/**
	 * @MethodDescribe 方法描述    获取某个表的表结构
	 * @author  创建人  林俊钦
	 * @date  创建日期  Jul 11, 2011 2:41:54 PM
	 */
	@SuppressWarnings("unchecked")
	public List getTablestructure(Map map);

	/**
	 * @function 功能 获取数据库版本信息
	 * @author  创建人 邱景岩
	 * @date  创建日期 Aug 26, 2011 5:46:55 PM
	 */
	public String getDatabaseversion();
	
	/**
	 * @function 功能 修改信息的点击数并取出点击数
	 * @author  创建人 李良林
	 * @date  创建日期 2011-10-28
	 */
	
	public List updateGetClickNum(Map map);

}
