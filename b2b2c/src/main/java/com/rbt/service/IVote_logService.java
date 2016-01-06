/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: ISysuserService.java 
 */

package com.rbt.service;

import java.util.List;
import java.util.Map;
import com.rbt.model.Vote_log;

/**
 * @function 功能  在线调查业务层接口
 * @author  创建人 蔡毅存
 * @date  创建日期  July 7, 2011
 */
public interface IVote_logService extends IGenericService<Vote_log,String>{
	
	/**
	 * 方法描述：显示在线调查选项
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public List getVoteoption(Map map);
	
}
