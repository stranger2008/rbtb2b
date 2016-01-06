/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: INavService.java 
 */

package com.rbt.service;

import java.util.List;
import com.rbt.model.Nav;

/**
 * @function 功能  导航链接业务层接口
 * @author  创建人 蔡毅存
 * @date  创建日期  July 5, 2011
 */
public interface INavService extends IGenericService<Nav,String>{
	
	/**
	 * 方法描述：批量修改导航是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateisshow(List list);
	
	/**
	 * 方法描述：批量修改导航排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updatesort_no(List list);
}
