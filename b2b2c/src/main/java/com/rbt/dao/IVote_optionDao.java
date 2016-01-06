/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IVote_optionDao.java 
 */

package com.rbt.dao;

import java.util.List;
import com.rbt.model.Vote_option;

/**
 * @function 功能 在线调查dao层接口
 * @author  创建人 蔡毅存
 * @date  创建日期  July 6, 2011
 */
public interface IVote_optionDao extends IGenericDao<Vote_option,String>{
	
	/**
	 * 方法描述：批量修改投票次数
	 * @param pk
	 * @return java.util.Map
	 */
	public void update_optioncount(List lists);
	
	
	
}
