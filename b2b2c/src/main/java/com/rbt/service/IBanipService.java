/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: IBan_IpService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Banip;;

/**
 * @function 功能  禁止IP管理业务层接口
 * @author  创建人 胡惜坤
 * @date  创建日期  July 5, 2011
 */
public interface IBanipService extends IGenericService<Banip,String> {
	
	/**
	 * 方法描述：批量地区排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateAllIp(List list);
	
}
