/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: AnswerDao.java 
 */
package com.rbt.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rbt.dao.IAnswerDao;
import com.rbt.dao.IAskDao;
import com.rbt.dao.IMemberinterDao;
import com.rbt.model.Answer;
import com.rbt.model.Ask;
import com.rbt.model.Memberinter;

/**
 * @function 功能  答案信息dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:28:09 CST 2011
 */
@Repository
public class AnswerDao extends GenericDao<Answer,String> implements IAnswerDao {

	public AnswerDao() {
		super(Answer.class);
	}
	
	@Autowired
	private IAskDao askDao;
	@Autowired
	private IMemberinterDao memberinterDao; 
	
	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.IAnswerDao#auditAnswer(java.util.Map)
	 */
	public void auditAnswer(Map map) {
		this.getSqlMapClientTemplate().update("answer.auditAnswer",map);
	}

	/**
	 * 方法描述：选择最佳答案的时候更改提问者和回答的积分值，必须在同一个事务里面
	 * 
	 * @return
	 * @throws Exception
	 */
	public void updateIntegral(String ask_id_s,String answer_id_s){
		// 取问题信息，减少相应积分
		Ask ask = this.askDao.get(ask_id_s);
		if (ask != null) {
			ask.setAnswer_state("1");//问题的回答状态 1：已解决
			this.askDao.update(ask);
			Integer integral = 0, fund_num = 0, totalfund_num = 0;
			integral = Integer.parseInt(ask.getIntegral());
			Memberinter memberinter = this.memberinterDao.get(ask.getCust_id());
			fund_num = Integer.parseInt(memberinter.getFund_num());
			totalfund_num = fund_num - integral;
			memberinter.setFund_num(totalfund_num.toString());
			this.memberinterDao.update(memberinter);

//			int a = 2/0;
			
			// 取最佳答案信息,增加相应积分
			Answer	answer = super.get(answer_id_s);
			if (answer != null) {
				answer.setIsselect("1"); //是否是最佳答案 1：最佳
				super.update(answer);
				Integer aintegral = 0, afund_num = 0, atotalfund_num = 0;
				aintegral = Integer.parseInt(ask.getIntegral());
				memberinter = this.memberinterDao.get(answer.getCust_id());
				afund_num = Integer.parseInt(memberinter.getFund_num());
				atotalfund_num = afund_num + aintegral;
				memberinter.setFund_num(atotalfund_num.toString());
				this.memberinterDao.update(memberinter);
			}
		}
	}
}

