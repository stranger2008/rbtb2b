/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IResumeinboxService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Resumeinbox;

/**
 * @function 功能 简历收件箱表Service层业务接口实现类
 * @author  创建人 胡惜坤
 * @date  创建日期 Mon Aug 01 09:17:11 CST 2011
 */

public interface IResumeinboxService extends IGenericService<Resumeinbox,String> {
	
	/**
	 * 方法描述：更新简历收件箱查看的状态
	 * @param map
	 */
	public void updateResumeinboxState(Map map);
}

