/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IKeywordDao.java 
 */

package com.rbt.dao;
import com.rbt.model.Keyword;

/**
 * @function 功能 关键字dao层接口
 * @author  创建人 蔡毅存
 * @date  创建日期  July 6, 2011
 */
public interface IKeywordDao extends IGenericDao<Keyword,String>{
	/**
	 * @MethodDescribe 方法描述    更新关键字次数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 22, 2011 3:45:13 PM
	 */
	public void updateKeyNums(Keyword keyword);
	
}
