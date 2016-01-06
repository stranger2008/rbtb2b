/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service
 * FileName: VoteService.java 
 */

package com.rbt.service;

import java.util.List;
import com.rbt.model.Vote;

/**
 * @function 功能  在线调查业务层接口
 * @author  创建人 蔡毅存
 * @date  创建日期  July 7, 2011
 */
public interface IVoteService extends IGenericService<Vote,String>{
	
	/**
	 * 方法描述：批量修改单选多选
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateis_multiState(List list);
	/**
	 * 更新投票次数
	 */
	public void updateVoteCountNum(String pk);
}
