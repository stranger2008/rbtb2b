/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: IKeywordService.java 
 */

package com.rbt.service;

import com.rbt.model.Keyword;

/**
 * @function 功能  关键字业务层接口
 * @author  创建人 蔡毅存
 * @date  创建日期  July 6, 2011
 */
public interface IKeywordService extends IGenericService<Keyword,String>{
	
	/**
	 * @MethodDescribe 方法描述    更新关键字次数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 22, 2011 3:45:13 PM
	 */
	public void updateKeyNums(Keyword keyword);
}
