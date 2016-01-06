/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IAnswerService.java 
 */
package com.rbt.service;

import java.util.Map;

import com.rbt.model.Answer;

/**
 * @function 功能 答案信息Service层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:28:09 CST 2011
 */

public interface IAnswerService extends IGenericService<Answer, String> {

	/**
	 * 方法描述：审核会员荣誉资质信息
	 * 
	 * @param member
	 */
	public void auditAnswer(Map map);
	/**
	 * 方法描述：选择最佳答案的时候更改提问者和回答的积分值，必须在同一个事务里面
	 * 
	 * @return
	 * @throws Exception
	 */
	public void updateIntegral(String ask_id_s, String answer_id_s);

}
