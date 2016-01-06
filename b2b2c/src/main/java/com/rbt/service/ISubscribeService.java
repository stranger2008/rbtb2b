/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ISubscribeService.java 
 */
package com.rbt.service;

import java.util.List;
import com.rbt.model.Subscribe;

/**
 * @function 功能 记录会员商机订阅信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Thu Jul 21 08:53:30 CST 2011
 */

public interface ISubscribeService extends IGenericService<Subscribe,String>{
	/**
	 * 方法描述：批量修改是否可用
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateenabled(List list);
}

