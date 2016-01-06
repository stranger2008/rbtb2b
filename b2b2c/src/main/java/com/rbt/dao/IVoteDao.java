/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IVoteDao.java 
 */

package com.rbt.dao;

import java.util.List;
import com.rbt.model.Vote;

/**
 * @function 功能 在线调查dao层接口
 * @author  创建人 蔡毅存
 * @date  创建日期  July 6, 2011
 */
public interface IVoteDao extends IGenericDao<Vote,String>{
	
	/**
	 * 方法描述：批量修改单选多选
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateis_multiState(List lists);
	/**
	 * 更新投票总数
	 * @param pk
	 */
	public void updateVoteCountNum(String pk);

}
