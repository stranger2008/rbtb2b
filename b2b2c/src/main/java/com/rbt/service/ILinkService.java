/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: ILinkService.java 
 */

package com.rbt.service;

import java.util.List;
import com.rbt.model.Link;

/**
 * @function 功能  友情链接业务层接口
 * @author  创建人 蔡毅存
 * @date  创建日期  Jun 28, 2011
 */
public interface ILinkService extends IGenericService<Link,String>{
	
	/**
	 * 方法描述：批量修改友情链接状态
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateLinkState(List list);
}
