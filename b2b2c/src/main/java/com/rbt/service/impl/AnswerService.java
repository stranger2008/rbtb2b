/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: AnswerService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAnswerDao;
import com.rbt.model.Answer;
import com.rbt.service.IAnswerService;

/**
 * @function 功能 答案信息Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:28:09 CST 2011
 */
@Service
public class AnswerService extends GenericService<Answer,String> implements IAnswerService {
    
	IAnswerDao answerDao;

	@Autowired
	public AnswerService(IAnswerDao answerDao) {
		super(answerDao);
		this.answerDao = answerDao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IAnswerService#auditAnswer(java.util.Map)
	 */
	public void auditAnswer(Map map) {
		this.answerDao.auditAnswer(map);
	}
	
	/**
	 * 方法描述：选择最佳答案的时候更改提问者和回答的积分值，必须在同一个事务里面
	 * 
	 * @return
	 * @throws Exception
	 */
	public void updateIntegral(String ask_id_s, String answer_id_s) {
		this.answerDao.updateIntegral(ask_id_s, answer_id_s);
	}

}

